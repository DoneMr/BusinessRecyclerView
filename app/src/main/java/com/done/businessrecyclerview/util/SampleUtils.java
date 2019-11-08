package com.done.businessrecyclerview.util;

import androidx.annotation.Nullable;

import com.done.bizrecyclerviewlib.cell.IBizCell;
import com.done.businessrecyclerview.cell.CardTextCell;
import com.done.businessrecyclerview.cell.DeleteCell;
import com.done.businessrecyclerview.cell.SendMessageCell;
import com.done.businessrecyclerview.constant.SampleContants;
import com.done.businessrecyclerview.model.CellTextInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * File:com.done.businessrecyclerview.util.SampleUtils
 * Description:工具类
 *
 * @author maruilong
 * @date 2019-11-07
 */
public final class SampleUtils {

    public static List<IBizCell> getCardTextCells(String title, String color, boolean showReceive) {
        List<IBizCell> cells = new ArrayList<>();
        for (int i = 0; i < SampleContants.LIST_PAGE_SIZE; i++) {
            CellTextInfo data = new CellTextInfo();
            data.setText(title);
            data.setTextColor(color);
            data.setTextSize("20");
            data.isShowReceive = showReceive;
            cells.add(new CardTextCell(data));
        }
        return cells;
    }

    public static List<IBizCell> getRandomReceiveCardTextCells(String title, String color) {
        List<IBizCell> cells = new ArrayList<>();
        for (int i = 0; i < SampleContants.LIST_PAGE_SIZE; i++) {
            CellTextInfo data = new CellTextInfo();
            data.setText(title);
            data.setTextColor(color);
            data.setTextSize("20");
            Random random = new Random();
            data.isShowReceive = random.nextBoolean();
            cells.add(new CardTextCell(data));
        }
        return cells;
    }

    public static IBizCell getSendMessageCell(String title, String color) {
        CellTextInfo data = new CellTextInfo();
        data.setText(title);
        data.setTextColor(color);
        data.setTextSize("20");
        return new SendMessageCell(data);
    }

    public static String getStringMessage(int sourcePos, @Nullable Object data) {
        return ("接收到来自:" + sourcePos + "_Cell 的消息：\n" + (data == null ? "" : data.toString()));
    }

    public static String getCurTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static List<IBizCell> getDeleteCells() {
        List<IBizCell> cells = new ArrayList<>();
        for (int i = 0; i < SampleContants.LIST_PAGE_SIZE; i++) {
            CellTextInfo data = new CellTextInfo();
            data.setText("");
            data.setTextColor("");
            data.setTextSize("20");
            cells.add(new DeleteCell(data));
        }
        return cells;
    }

    public static boolean isListEmpty(List list) {
        return (list == null || list.size() == 0);
    }
}
