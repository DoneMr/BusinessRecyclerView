package com.done.businessrecyclerview.model;

import android.content.Intent;

/**
 * File:com.done.businessrecyclerview.model.CellTextInfo
 * Description:xxx
 *
 * @author maruilong
 * @date 2019-11-05
 */
public class CellTextInfo {

    private String text;
    private String textSize;
    private String textColor;

    private Intent jumpIntent;

    public Intent getJumpIntent() {
        return jumpIntent;
    }

    public void setJumpIntent(Intent jumpIntent) {
        this.jumpIntent = jumpIntent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
