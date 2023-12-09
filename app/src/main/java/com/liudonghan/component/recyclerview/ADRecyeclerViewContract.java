package com.liudonghan.component.recyclerview;

import com.liudonghan.component.adapter.ADRecyclerViewPageAdapter;
import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseView;

import xyz.doikki.videoplayer.player.VideoView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public interface ADRecyeclerViewContract {

    interface View extends ADBaseView<Presenter> {

    }

    interface Presenter extends ADBasePresenter {

        /**
         * 获取VideoView
         * @return
         */
        VideoView getVideoView();

        /**
         * 移除VideoView组件
         * @param videoView 播放器组件
         */
        void removeVideoView(VideoView videoView);

        void startPlay(int position, android.view.View view, ADRecyclerViewPageAdapter adapter, VideoView videoView, boolean isBottom);
    }
}