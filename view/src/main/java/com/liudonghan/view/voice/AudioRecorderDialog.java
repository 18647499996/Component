package com.liudonghan.view.voice;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADTextView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/7/23
 */
public class AudioRecorderDialog {

    private ImageView adDialogVoiceImgIng;
    private ADTextView adDialogVoiceTvHint;
    private ImageView adDialogVoiceImgCancel;

    private static volatile AudioRecorderDialog instance = null;
    private Dialog mDialog;

    private AudioRecorderDialog() {
    }

    public static AudioRecorderDialog getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (AudioRecorderDialog.class) {
                // double checkout
                if (null == instance) {
                    instance = new AudioRecorderDialog();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public void init(Context context) {
        mDialog = new Dialog(context, R.style.ADDialog);
        mDialog.setContentView(R.layout.ad_dialog_voice);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        adDialogVoiceImgIng = (ImageView) mDialog.findViewById(R.id.ad_dialog_voice_img_ing);
        adDialogVoiceTvHint = (ADTextView) mDialog.findViewById(R.id.ad_dialog_voice_tv_hint);
        adDialogVoiceImgCancel = mDialog.findViewById(R.id.ad_dialog_voice_img_cancel);
        adDialogVoiceImgIng.setBackgroundResource(R.drawable.ad_voice);
        AnimationDrawable animationDrawable = (AnimationDrawable) adDialogVoiceImgIng.getBackground();
        animationDrawable.start();
    }

    /**
     * 显示弹窗
     */
    public void showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 关闭弹窗
     */
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 正在录音时，Dialog的显示
     */
    public void start() {
        if (mDialog != null && mDialog.isShowing()) {
            adDialogVoiceImgCancel.setVisibility(View.INVISIBLE);
            adDialogVoiceImgIng.setVisibility(View.VISIBLE);
            adDialogVoiceTvHint.setBackgroundColor(Color.parseColor("#00000000"));
            adDialogVoiceTvHint.setText("手指上划，取消发送");
        }
    }

    /**
     * 取消录音提示对话框
     */
    public void cancel() {
        if (mDialog != null && mDialog.isShowing()) {
            adDialogVoiceImgCancel.setVisibility(View.VISIBLE);
            adDialogVoiceImgIng.setVisibility(View.INVISIBLE);
            adDialogVoiceTvHint.setBackgroundColor(Color.parseColor("#AF2831"));
            adDialogVoiceTvHint.setText("松开手指，取消发送");
        }
    }
}
