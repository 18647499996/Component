package com.liudonghan.view.input;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liudonghan.view.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Description：密码输入框
 *
 * @author Created by: Li_Min
 * Time:1/5/23
 */
public class ADInputCodeView extends RelativeLayout {
    private Context context;
    private TextView tv_code1;
    private TextView tv_code2;
    private TextView tv_code3;
    private TextView tv_code4;
    private TextView tv_code5;
    private TextView tv_code6;

    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private EditText et_code;
    private List<String> codes = new ArrayList<>();

    public ADInputCodeView(Context context) {
        super(context);
        this.context = context;
        loadView();
    }

    public ADInputCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        loadView();
    }

    private void loadView() {
        View view = LayoutInflater.from(context).inflate(R.layout.ad_input_code, this);
        initView(view);
        initEvent();
        initListener();
    }

    private void initListener() {
        //屏蔽双击： 好多手机双击会出现 选择 剪切 粘贴 的选项卡，
        new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return true;
            }
        });

    }

    private void initView(View view) {
        tv_code1 = (TextView) view.findViewById(R.id.tv_code1);
        tv_code2 = (TextView) view.findViewById(R.id.tv_code2);
        tv_code3 = (TextView) view.findViewById(R.id.tv_code3);
        tv_code4 = (TextView) view.findViewById(R.id.tv_code4);
        tv_code5 = (TextView) view.findViewById(R.id.tv_code5);
        tv_code6 = (TextView) view.findViewById(R.id.tv_code6);
        et_code = (EditText) view.findViewById(R.id.et_code);
        v1 = view.findViewById(R.id.v1);
        v2 = view.findViewById(R.id.v2);
        v3 = view.findViewById(R.id.v3);
        v4 = view.findViewById(R.id.v4);
    }

    private void initEvent() {
        //验证码输入
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    et_code.setHint("");
                    et_code.setText("");
                    if (codes.size() < 6) {
                        if (editable.length() > 1) {
                            clearTextViewCode();
                            char[] chars = editable.toString().toCharArray();
                            for (int i = 0; i < chars.length; i++) {
                                codes.add(chars[i] + "");
                            }
                        } else {
                            codes.add(editable.toString());
                        }
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        et_code.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && codes.size() > 0) {
                    if (codes.size() == 1) {
                        et_code.setHint("");
                    }
                    codes.remove(codes.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        String code1 = "";
        String code2 = "";
        String code3 = "";
        String code4 = "";
        String code5 = "";
        String code6 = "";

        if (codes.size() >= 1) {
            code1 = codes.get(0);
        }
        if (codes.size() >= 2) {
            code2 = codes.get(1);
        }
        if (codes.size() >= 3) {
            code3 = codes.get(2);
        }
        if (codes.size() >= 4) {
            code4 = codes.get(3);
        }
        if (codes.size() >= 5) {
            code5 = codes.get(4);
        }
        if (codes.size() >= 6) {
            code6 = codes.get(5);
        }
        tv_code1.setText(code1);
        tv_code2.setText(code2);
        tv_code3.setText(code3);
        tv_code4.setText(code4);
        tv_code5.setText(code5);
        tv_code6.setText(code6);


//        setColor();//设置高亮颜色
        callBack();//回调
    }

    /**
     * 设置高亮颜色
     */
    private void setColor() {
        int color_default = Color.parseColor("#999999");
        int color_focus = Color.parseColor("#EA2000");
        v1.setBackgroundColor(color_default);
        v2.setBackgroundColor(color_default);
        v3.setBackgroundColor(color_default);
        v4.setBackgroundColor(color_default);
        if (codes.size() == 0) {
            v1.setBackgroundColor(color_focus);
        }
        if (codes.size() == 1) {
            v2.setBackgroundColor(color_focus);
        }
        if (codes.size() == 2) {
            v3.setBackgroundColor(color_focus);
        }
        if (codes.size() >= 3) {
            v4.setBackgroundColor(color_focus);
        }
    }


    /**
     * 回调
     */
    private void callBack() {
        if (onInputListener == null) {
            return;
        }
        if (codes.size() == 6) {
            onInputListener.onSucceed(getPhoneCode());
        } else {
            onInputListener.onInput();
        }
    }

    //定义回调
    public interface OnInputListener {
        void onSucceed(String code);

        void onInput();
    }

    private OnInputListener onInputListener;

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    public void clearTextViewCode() {
        tv_code1.setText("");
        tv_code2.setText("");
        tv_code3.setText("");
        tv_code4.setText("");
        tv_code5.setText("");
        tv_code6.setText("");
        et_code.setText("");
        codes.clear();
        callBack();//回调
    }

    /**
     * 获得手机号验证码
     *
     * @return 验证码
     */
    public String getPhoneCode() {
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }

}
