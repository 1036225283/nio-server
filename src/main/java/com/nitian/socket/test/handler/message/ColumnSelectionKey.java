package com.nitian.socket.test.handler.message;

import com.nitian.util.column.array.Column;

import java.nio.channels.SelectionKey;

/**
 * 存放<strId-Socket>的结构
 * Created by 1036225283 on 2017/3/26.
 */
public class ColumnSelectionKey {

    private static ColumnSelectionKey instance = new ColumnSelectionKey();

    private Column<String, SelectionKey> column;


    public static ColumnSelectionKey getInstance() {
        return instance;
    }

    private ColumnSelectionKey() {
        column = new Column<>(5000);
    }

}
