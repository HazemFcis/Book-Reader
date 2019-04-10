package com.example.hazem.draduation;

import android.provider.BaseColumns;

/**
 * Created by Hazem on 8/3/2018.
 */

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TableName = "FavouriteTable";
        public static final String Table_ID = "id";
        public static final String Book_ID = "idd";
        public static final String Book_Image = "imageposter";
        public static final String Book_Title = "title";
        public static final String Book_Rate = "rate";
        public static final String Book_Year = "year";
        public static final String Book_publisher = "publisher";
        public static final String Book_Overview = "overview";
    }
}
