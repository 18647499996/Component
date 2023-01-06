package com.liudonghan.view.snackbar.listener;

import com.liudonghan.view.snackbar.SnackBar;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public interface EventListener {
    /**
     * Called when a {@link com.nispok.snackbar.Snackbar} is about to enter the screen
     *
     * @param snackbar the {@link com.nispok.snackbar.Snackbar} that's being shown
     */
    public void onShow(SnackBar snackbar);

    /**
     * Called when a {@link com.nispok.snackbar.Snackbar} is about to enter the screen while
     * a {@link com.nispok.snackbar.Snackbar} is about to exit the screen by replacement.
     *
     * @param snackbar the {@link com.nispok.snackbar.Snackbar} that's being shown
     */
    public void onShowByReplace(SnackBar snackbar);

    /**
     * Called when a {@link com.nispok.snackbar.Snackbar} is fully shown
     *
     * @param snackbar the {@link com.nispok.snackbar.Snackbar} that's being shown
     */
    public void onShown(SnackBar snackbar);

    /**
     * Called when a {@link com.nispok.snackbar.Snackbar} is about to exit the screen
     *
     * @param snackbar the {@link com.nispok.snackbar.Snackbar} that's being dismissed
     */
    public void onDismiss(SnackBar snackbar);

    /**
     * Called when a {@link com.nispok.snackbar.Snackbar} is about to exit the screen
     * when a new {@link com.nispok.snackbar.Snackbar} is about to enter the screen.
     *
     * @param snackbar the {@link com.nispok.snackbar.Snackbar} that's being dismissed
     */
    public void onDismissByReplace(SnackBar snackbar);

    /**
     * Called when a {@link com.nispok.snackbar.Snackbar} had just been dismissed
     *
     * @param snackbar the {@link com.nispok.snackbar.Snackbar} that's being dismissed
     */
    public void onDismissed(SnackBar snackbar);
}