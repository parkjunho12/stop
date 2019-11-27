package com.timeline.stopwatch;

import android.provider.BaseColumns;

public final class Database {
    public static final class CreateDB implements BaseColumns {
        public static final String watch = "구독";
        public static final String _TABLENAME0 = "my_watch";
        public static final String PHONE = "PHONE";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +PHONE+" varchar(50) not null , "
                +watch+ " varchar(50) not null  "+
                " );";
    }
}
