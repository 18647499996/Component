package com.liudonghan.component.recyclerview;

import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.liudonghan.component.R;
import com.liudonghan.component.adapter.ADRecyclerViewPageAdapter;
import com.liudonghan.component.utils.TikTokRenderViewFactory;
import com.liudonghan.kit.ijk.ADVideoPlayManager;
import com.liudonghan.mvp.ADBaseSubscription;

import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videoplayer.player.VideoView;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADRecyeclerViewPresenter extends ADBaseSubscription<ADRecyeclerViewContract.View> implements ADRecyeclerViewContract.Presenter {


    private boolean isf;

    ADRecyeclerViewPresenter(ADRecyeclerViewContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public VideoView getVideoView() {
        VideoView mVideoView = new VideoView(getContext());
        //以下只能二选一，看你的需求
        mVideoView.setLooping(true);
//        mController = new TikTokController(this);
//        mVideoView.setVideoController(mController);

        return mVideoView;
    }

    @Override
    public void removeVideoView(VideoView v) {
        if (v == null) return;
        ViewParent parent = v.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(v);
        }
    }

    @Override
    public void startPlay(int position, View view, ADRecyclerViewPageAdapter adapter, VideoView videoView, boolean isBottom) {
        FrameLayout frameLayout = view.findViewById(R.id.item_ijk);
        videoView.release();
        removeVideoView(videoView);
        frameLayout.addView(videoView);
        videoView.setUrl(ADVideoPlayManager.getInstance().getHttpProxyCacheServer().getProxyUrl(adapter.getData().get(position)));
        videoView.start();
        if (isBottom) {
            //到最后一个加载第二页
            if (!isf) {
                isf = true;
                adapter.getData().add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=oibeqyX228riaCwo9STVsGLIBn9G5YG8ZnwibmmkpZq6REY7gwsVWuvUMS9Jz1la5A4ANd6MPz4SbyGY9PGwtTibEicUNAYCtGmjRVySjBoIGVdNWz6Yqz37rAczAcE1LILQdvcZa6NgFcB0&a=1&bizid=1023&dotrans=0&hy=SZ&idx=1&m=a48c8ce4b06021bc9601ed8931a0cce4&upid=500170&token=ic1n0xDG6awicunEETWKJic5JzNDfsyxKA0WwDz1BZkh42rYIAfMibObppfgiaviaEm4LKyLkjW4fsmEE&X-snsvideoflag=xV1");
                adapter.getData().add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=6xykWLEnztKcKCJZcV0rWCM8ua7DibZkibPSZaIgeFjxFG5yECKWZw70psuJQjGh38VxiasRLIDTzXdpa2ppYnOiavQFvxvMMLcllrmW7rhakLwFeuoXtmGowichib1eWZE0uowshmf6n49r1HqibLu5vtaPAGJNghHohd0nKkOqtm4DdE&a=1&bizid=1023&dotrans=0&extg=27252e&ftype=8&hy=SZ&idx=1&m=7230d28ee5f6019756d2e733d08955bc&svrbypass=AAuL%2FQsFAAABAAAAAAAN%2B%2FYSJSity3A%2FJ78kZRAAAADnaHZTnGbFfAj9RgZXfw6Vyimn9Tb17AX%2Bn9xOgthwCg6J%2FO7lQgVyhOvCoj6St%2BmU&svrnonce=1696907047&upid=290150&token=AxricY7RBHdUHjyocZ3JbVa1AIMG9YfLePnSaGoPoXydeobu67wdlbklZmZF1Os8FrPYjhGqEmO4&X-snsvideoflag=xV1");
                adapter.notifyDataSetChanged();
            }
        }
    }
}