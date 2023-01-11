package com.liudonghan.view.photo;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/11/23
 */
public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

    private PhotoViewAttacher photoViewAttr;

    /**
     * Default constructor
     *
     * @param photoViewAttacher PhotoViewAttacher to bind to
     */
    public DefaultOnDoubleTapListener(PhotoViewAttacher photoViewAttacher) {
        setPhotoViewAttr(photoViewAttacher);
    }

    /**
     * Allows to change PhotoViewAttacher within range of single instance
     *
     * @param newPhotoViewAttacher PhotoViewAttacher to bind to
     */
    public void setPhotoViewAttr(PhotoViewAttacher newPhotoViewAttacher) {
        this.photoViewAttr = newPhotoViewAttacher;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (this.photoViewAttr == null)
            return false;

        ImageView imageView = photoViewAttr.getImageView();

        if (null != photoViewAttr.getOnPhotoTapListener()) {
            final RectF displayRect = photoViewAttr.getDisplayRect();

            if (null != displayRect) {
                final float x = e.getX(), y = e.getY();

                // Check to see if the user tapped on the photo
                if (displayRect.contains(x, y)) {

                    float xResult = (x - displayRect.left)
                            / displayRect.width();
                    float yResult = (y - displayRect.top)
                            / displayRect.height();

                    photoViewAttr.getOnPhotoTapListener().onPhotoTap(imageView, xResult, yResult);
                    return true;
                }
            }
        }
        if (null != photoViewAttr.getOnViewTapListener()) {
            photoViewAttr.getOnViewTapListener().onViewTap(imageView, e.getX(), e.getY());
        }

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent ev) {
        if (photoViewAttr == null)
            return false;

        try {
            float scale = photoViewAttr.getScale();
            float x = ev.getX();
            float y = ev.getY();

            if (scale < photoViewAttr.getMediumScale()) {
                photoViewAttr.setScale(photoViewAttr.getMediumScale(), x, y, true);
            } else if (scale >= photoViewAttr.getMediumScale() && scale < photoViewAttr.getMaximumScale()) {
                photoViewAttr.setScale(photoViewAttr.getMaximumScale(), x, y, true);
            } else {
                photoViewAttr.setScale(photoViewAttr.getMinimumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Can sometimes happen when getX() and getY() is called
        }

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // Wait for the confirmed onDoubleTap() instead
        return false;
    }

}
