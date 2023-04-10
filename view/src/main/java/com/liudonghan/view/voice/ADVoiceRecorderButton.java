package com.liudonghan.view.voice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.liudonghan.view.helper.ViewHelper;

/**
 * Description：语音组件
 *
 * @author Created by: Li_Min
 * Time:4/7/23
 */
public class ADVoiceRecorderButton extends AppCompatButton {

    private static final String TAG = "Mac_Liu";
    private ViewHelper viewHelper;
    private AudioRecorderManager audioRecorderManager;
    private AudioRecorderDialog audioRecorderDialog;
    private Context context;
    private long downTime;
    private boolean isTranscend = false;
    private OnADVoiceRecorderButtonListener onADVoiceRecorderButtonListener;

    public ADVoiceRecorderButton(@NonNull Context context) {
        super(context, null);
    }

    public ADVoiceRecorderButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewHelper = new ViewHelper();
        TypedArray typedArray = viewHelper.initAttrs(context, attrs);
        typedArray.recycle();
        init(context).builder();
    }

    /**
     * 设置音频
     *
     * @param context 上下文
     * @return ADVoiceRecorderButton
     */
    public ADVoiceRecorderButton init(Context context) {
        this.context = context;
        this.audioRecorderManager = AudioRecorderManager.getInstance(context);
        this.audioRecorderDialog = AudioRecorderDialog.getInstance();
        return this;
    }

    /**
     * 设置语音录制弹窗
     */
    public void builder() {
        this.audioRecorderDialog.init(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取当前Action
        int action = event.getAction();
        //获取当前的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                setAudioStatus(AudioStatus.start);
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                if (isMoveOutScope(x, y)) {
                    setAudioStatus(AudioStatus.cancel);
                } else {
                    setAudioStatus(AudioStatus.progress);
                }
                break;
            case MotionEvent.ACTION_UP:
                // 抬起
                setAudioStatus(AudioStatus.shutdown);
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 设置
     *
     * @param audioStatus 录音状态
     */
    private void setAudioStatus(AudioStatus audioStatus) {
        switch (audioStatus) {
            case normal:
                setText("按住 说话");
                downTime = 0;
                isTranscend = false;
                break;
            case start:
                setText("松开 结束");
                downTime = System.currentTimeMillis();
                audioRecorderDialog.showDialog();
                audioRecorderDialog.start();
                audioRecorderManager.prepareAudio();
                break;
            case progress:
                isTranscend = false;
                setText("松开 结束");
                audioRecorderDialog.start();
                break;
            case cancel:
                isTranscend = true;
                audioRecorderDialog.cancel();
                setText("取消 发送");
                break;
            case shutdown:
                setText("按住 说话");
                long duration = System.currentTimeMillis() - downTime;
                Log.i(TAG, "up time ：" + duration);
                if (duration < 800) {
                    // 语音时长太短
                    Log.i(TAG, "voice recorder short please reset recorder");
                    audioRecorderManager.cancel();
                    if (null != onADVoiceRecorderButtonListener) {
                        onADVoiceRecorderButtonListener.onShortLimit();
                    }
                } else if (isTranscend) {
                    // 移动取消发送
                    Log.i(TAG, "move range transcend limit");
                    audioRecorderManager.cancel();
                    if (null != onADVoiceRecorderButtonListener) {
                        onADVoiceRecorderButtonListener.onRangeLimit();
                    }
                } else {
                    Log.i(TAG, "voice recorder succeed");
                    audioRecorderManager.release();
                    if (null != onADVoiceRecorderButtonListener) {
                        onADVoiceRecorderButtonListener.onAudioSucceed(audioRecorderManager.getCurrentFilePath(), duration);
                    }
                }
                audioRecorderDialog.dismissDialog();

                break;
        }
    }

    public void setOnADVoiceRecorderButtonListener(OnADVoiceRecorderButtonListener onADVoiceRecorderButtonListener) {
        this.onADVoiceRecorderButtonListener = onADVoiceRecorderButtonListener;
    }

    /**
     * 是否移动超出范围
     *
     * @param x X轴坐标
     * @param y Y轴坐标
     * @return boolean
     */
    private boolean isMoveOutScope(int x, int y) {
        // 判断手指的滑动是否超出范围
        if (x < 0 || x > getWidth()) {
            return true;
        }
        return y < -50 || y > getHeight() + 50;
    }

    public interface OnADVoiceRecorderButtonListener {

        /**
         * 时长限制
         */
        void onShortLimit();

        /**
         * 范围限制
         */
        void onRangeLimit();

        /**
         * 录音完成
         *
         * @param filePath 文件路径
         * @param duration 时长
         */
        void onAudioSucceed(String filePath, long duration);
    }

    public enum AudioStatus {
        start,
        progress,
        shutdown,
        normal,
        cancel,
    }

    @Override
    public void invalidate() {
        if (null != viewHelper) {
            viewHelper.refreshRegion(this);
        }
        super.invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewHelper.onSizeChanged(this, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        if (viewHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(viewHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(viewHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        viewHelper.onClipDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN && !viewHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
            refreshDrawableState();
        } else if (action == MotionEvent.ACTION_CANCEL) {
            setPressed(false);
            refreshDrawableState();
        }
        return super.dispatchTouchEvent(ev);
    }
}
