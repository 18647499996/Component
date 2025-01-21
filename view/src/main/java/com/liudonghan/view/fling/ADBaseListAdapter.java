package com.liudonghan.view.fling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class ADBaseListAdapter<T> extends BaseAdapter {

    private final List<T> mInfos;
    private final LayoutInflater mInflater;
    protected Context context;
    private OnItemChildClickAdapterListener<T> onItemChildClickAdapterListener;
    private OnItemClickAdapterListener<T> onItemClickAdapterListener;

    public ADBaseListAdapter(Context context) {
        super();
        this.context = context;
        this.mInfos = new ArrayList<>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ADBaseListAdapter(Context context, List<T> data) {
        super();
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mInfos = data;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public T getItem(int position) {
        return mInfos.get(position);
    }

    public T getItemByPosition(int position) {
        return mInfos.get(position);
    }

    public void setList(ArrayList<T> list) {
        mInfos.clear();
        if (list == null) return;
        mInfos.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mInfos;
    }

    public int getPositionByValue(T t) {
        return mInfos.indexOf(t);
    }

    public void add(T t) {
        mInfos.add(t);
        notifyDataSetChanged();
    }

    public void add(int position, T t) {
        mInfos.add(position, t);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        mInfos.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(int position, List<T> list) {
        mInfos.addAll(position, list);
        notifyDataSetChanged();
    }

    public void addAll(int position, Collection<T> list) {
        mInfos.addAll(position, list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mInfos.clear();
        notifyDataSetChanged();
    }

    public void remove(T t) {
        mInfos.remove(t);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mInfos.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<T> viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(getItemLayout(), parent, false);
            viewHolder = new BaseViewHolder<>(convertView, this);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BaseViewHolder<T>) convertView.getTag();
        }
        if (null != onItemClickAdapterListener) {
            convertView.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickAdapterListener.onItemClick(ADBaseListAdapter.this, position, getItem(position));
                }
            });
        }

        return getView(position, convertView, viewHolder, getItem(position));
    }

    public abstract int getItemLayout();

    public abstract View getView(int position, View convertView, BaseViewHolder<T> viewHolder, T item);

    /**
     * viewHolder
     */
    public static class BaseViewHolder<T> {

        private final SparseArray<View> views;
        private final LinkedHashSet<Integer> childClickViewIds;
        private final View convertView;
        private final ADBaseListAdapter<T> adapter;

        public BaseViewHolder(View convertView, ADBaseListAdapter<T> adapter) {
            this.views = new SparseArray<>();
            this.convertView = convertView;
            this.childClickViewIds = new LinkedHashSet<>();
            this.adapter = adapter;
        }

        /**
         * 色值文本
         *
         * @param viewId 控件Id
         * @param strId  资源文本
         * @return ViewHolder
         */
        public BaseViewHolder<T> setText(@IdRes int viewId, @StringRes int strId) {
            TextView textView = findViewById(viewId);
            textView.setText(strId);
            return this;
        }

        /**
         * 设置文本
         *
         * @param viewId 控件Id
         * @param text   文本
         * @return ViewHolder
         */
        public BaseViewHolder<T> setText(@IdRes int viewId, CharSequence text) {
            TextView textView = findViewById(viewId);
            textView.setText(text);
            return this;
        }

        /**
         * 设置图片资源
         *
         * @param viewId     控件Id
         * @param imageResId 图片资源
         * @return ViewHolder
         */
        public BaseViewHolder<T> setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
            ImageView view = findViewById(viewId);
            view.setImageResource(imageResId);
            return this;
        }

        /**
         * 设置背景颜色
         *
         * @param viewId 控件ID
         * @param color  颜色值
         * @return ViewHolder
         */
        public BaseViewHolder<T> setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
            View view = findViewById(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        /**
         * 设置背景资源
         *
         * @param viewId        控件Id
         * @param backgroundRes 背景资源
         * @return ViewHolder
         */
        public BaseViewHolder<T> setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
            View view = findViewById(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        /**
         * 设置文本颜色
         *
         * @param viewId    控件Id
         * @param textColor 文本颜色
         * @return ViewHolder
         */
        public BaseViewHolder<T> setTextColor(@IdRes int viewId, @ColorInt int textColor) {
            TextView view = findViewById(viewId);
            view.setTextColor(textColor);
            return this;
        }

        /**
         * 设置图片资源
         *
         * @param viewId   控件Id
         * @param drawable 资源
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setImageDrawable(@IdRes int viewId, Drawable drawable) {
            ImageView view = findViewById(viewId);
            view.setImageDrawable(drawable);
            return this;
        }

        /**
         * 设置bitmap图片
         *
         * @param viewId 控件Id
         * @param bitmap bitmap实体
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
            ImageView view = findViewById(viewId);
            view.setImageBitmap(bitmap);
            return this;
        }

        /**
         * 设置透明度
         *
         * @param viewId 控件Id
         * @param value  透明值 0 - 10
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setAlpha(@IdRes int viewId, float value) {
            findViewById(viewId).setAlpha(value);
            return this;
        }

        /**
         * 设置隐藏属性
         *
         * @param viewId  控件Id
         * @param visible 是否隐藏
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setGone(@IdRes int viewId, boolean visible) {
            View view = findViewById(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        /**
         * 设置隐藏属性（ 占位 ）
         *
         * @param viewId  控件Id
         * @param visible 是否隐藏
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setVisible(@IdRes int viewId, boolean visible) {
            View view = findViewById(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            return this;
        }

        /**
         * 设置文本字体样式
         *
         * @param viewId 控件Id
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> linkify(@IdRes int viewId) {
            TextView view = findViewById(viewId);
            Linkify.addLinks(view, Linkify.ALL);
            return this;
        }

        /**
         * 设置TextView样式
         *
         * @param viewIds  控件Id
         * @param typeface Typeface实例
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setTypeface(Typeface typeface, int... viewIds) {
            for (int viewId : viewIds) {
                TextView view = findViewById(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
            return this;
        }

        /**
         * 设置进度条
         *
         * @param viewId   控件Id
         * @param progress 进度值
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setProgress(@IdRes int viewId, int progress) {
            ProgressBar view = findViewById(viewId);
            view.setProgress(progress);
            return this;
        }

        /**
         * 设置进度条
         *
         * @param viewId   控件Id
         * @param progress 进度值
         * @param max      最大值
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setProgress(@IdRes int viewId, int progress, int max) {
            ProgressBar view = findViewById(viewId);
            view.setMax(max);
            view.setProgress(progress);
            return this;
        }

        /**
         * 设置进度条
         *
         * @param viewId 控件Id
         * @param max    最大值
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> setMax(@IdRes int viewId, int max) {
            ProgressBar view = findViewById(viewId);
            view.setMax(max);
            return this;
        }

        /**
         * 设置点击事件
         *
         * @param viewId 控件Id
         * @return BaseViewHolder
         */
        public BaseViewHolder<T> addOnClickListener(int position, @IdRes final int viewId) {
            childClickViewIds.add(viewId);
            final View view = findViewById(viewId);
            if (view != null) {
                if (!view.isClickable()) {
                    view.setClickable(true);
                }
                view.setOnClickListener(v -> {
                    if (adapter.getOnItemChildClickListener() != null) {
                        adapter.getOnItemChildClickListener().onItemChildClick(adapter, v, position);
                    }
                });
            }
            return this;
        }

        public <V extends View> V findViewById(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = convertView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (V) view;
        }


    }

    public OnItemChildClickAdapterListener<T> getOnItemChildClickListener() {
        return onItemChildClickAdapterListener;
    }

    public void setOnItemChildClickAdapterListener(OnItemChildClickAdapterListener<T> onItemChildClickAdapterListener) {
        this.onItemChildClickAdapterListener = onItemChildClickAdapterListener;
    }

    public void setOnItemClickAdapterListener(OnItemClickAdapterListener<T> onItemClickAdapterListener) {
        this.onItemClickAdapterListener = onItemClickAdapterListener;
    }

    public OnItemClickAdapterListener<T> getOnItemClickAdapterListener() {
        return onItemClickAdapterListener;
    }

    public interface OnItemChildClickAdapterListener<T> {

        void onItemChildClick(ADBaseListAdapter<T> baseListAdapter, View v, int position);
    }

    public interface OnItemClickAdapterListener<T> {

        void onItemClick(ADBaseListAdapter<T> baseListAdapter, int position, T item);
    }
}
