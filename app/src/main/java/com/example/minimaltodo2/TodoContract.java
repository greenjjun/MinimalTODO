package com.example.minimaltodo2;

import android.provider.BaseColumns;

public final class TodoContract {

    public  TodoContract(){

    }

    public static class TodoEntry implements BaseColumns{
        public static final String TABLE_NAME= "todos";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}
