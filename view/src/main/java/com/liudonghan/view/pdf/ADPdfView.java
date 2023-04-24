package com.liudonghan.view.pdf;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Description：PDF Browse
 *
 * @author Created by: Li_Min
 * Time:4/24/23
 */

public class ADPdfView extends WebView {

    public static final String TAG = "Mac_Liu";

    private final static String PDF_JS = "file:///android_asset/viewer.html?file=";

    public ADPdfView(Context context) {
        super(context);
        init();
    }

    public ADPdfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ADPdfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    private void init() {
        WebSettings webSettings = getSettings();
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        //自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        //允许js 并读取文件
        webSettings.setJavaScriptEnabled(true);
        // api 29 使用
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return super.startActionMode(callback);
    }

    /**
     * 加载本地Assets的pdf
     *
     * @param assetsPath 资源文件
     */
    public void loadLocalPDF(String assetsPath) {
        loadUrl(PDF_JS + "file://" + assetsPath);
    }

    /**
     * 加载url的pdf
     *
     * @param url url路径
     */
    public void loadOnlineUrl(String url) {
        loadUrl(PDF_JS + url);
    }

    /**
     * 加载本地文件
     *
     * @param filePath 文件路径
     */
    public void loadFilePath(String filePath) {
        loadUrl(PDF_JS + filePath);
    }
}