package com.liudonghan.view.snackbar;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public enum SnackBarType {

    /**
     * Snackbar with a single line
     */
    SINGLE_LINE(48, 48, 1),
    /**
     * Snackbar with two lines
     */
    MULTI_LINE(48, 80, 2);

    private int minHeight;
    private int maxHeight;
    private int maxLines;

    SnackBarType(int minHeight, int maxHeight, int maxLines) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.maxLines = maxLines;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxLines() {
        return maxLines;
    }
}
