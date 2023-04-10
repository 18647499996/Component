package com.liudonghan.view.voice;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.util.Date;

/**
 * Description：语音录制管理器
 *
 * @author Created by: Li_Min
 * Time:4/7/23
 */
public class AudioRecorderManager {

    /**
     * MediaRecorder可以实现录音和录像。需要严格遵守API说明中的函数调用先后顺序.
     */
    private MediaRecorder mMediaRecorder;

    /**
     * 录音文件路径
     */
    private File voiceFile;

    private static volatile AudioRecorderManager instance = null;

    private AudioRecorderManager() {
    }

    public static AudioRecorderManager getInstance(Context context) {
        //single checkout
        if (null == instance) {
            synchronized (AudioRecorderManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new AudioRecorderManager();
                }
            }
        }
        return instance;
    }

    /**
     * 准备录音
     */
    public void prepareAudio() {
        try {
            mMediaRecorder = new MediaRecorder();
            // 设置MediaRecorder的音频源为麦克风
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置音频的格式
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            // 设置音频的编码为AMR_NB
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 设置音频保存目录
            voiceFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), new Date().getTime() + ".amr");
            // 设置输出文件
            mMediaRecorder.setOutputFile(voiceFile.getAbsolutePath());
            // 准备录制
            mMediaRecorder.prepare();
            // 开始录制
            mMediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        try {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        } catch (Exception e) {
            mMediaRecorder = null;
            e.printStackTrace();
        }
    }

    /**
     * 取消录音（ 释放资源+删除文件 ）
     */
    public void cancel() {
        if (null != voiceFile) {
            // 删除录音文件
            voiceFile.delete();
            voiceFile = null;
        }
        release();
    }

    /**
     * 当前音频文件
     *
     * @return String
     */
    public String getCurrentFilePath() {
        return null != voiceFile ? voiceFile.getAbsolutePath() : "";
    }
}
