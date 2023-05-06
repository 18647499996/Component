package com.liudonghan.component;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.liudonghan.pdf.ADPdfView;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {

    private ADPdfView adPdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        adPdfView = findViewById(R.id.pdf);
//        adPdfView.loadLocalPDF("/android_asset/demo.pdf");
//        adPdfView.loadOnlineUrl("https://pageoffice-1307611133.cos.ap-beijing.myqcloud.com/fx_authorization.pdf");
        File file = new File("/storage/emulated/0/Download/WeiXin/dianzi.pdf");
        Uri uri = Uri.fromFile(file);
        Log.i("Mac_Liu：", uri.toString());
        adPdfView.loadFilePath(uri.toString());
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");

                //指定类型
//            var types = arrayOf(pdf)
//            intent.putExtra(Intent.EXTRA_MIME_TYPES,types)
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 10:
                if (null == data.getData()) {
                    Log.e(MainActivity.class.getSimpleName(), "onActivityResult: 没有加载到 文件");
                    return;
                }
                Log.i("文件Uri：",data.getData().toString());
                Uri returnUri = data.getData();
                adPdfView.loadFilePath(returnUri.toString());
                break;
        }
    }
}