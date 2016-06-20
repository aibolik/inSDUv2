package tk.aibolik.app.insdu.data;

import android.provider.BaseColumns;

/**
 * Created by Aibol Kussain on Jun 21, 2016.
 * Working on "inSDUv2". Smart Digital Solutions
 * You can contact me at: aibolikdev@gmail.com
 */
public class Contracts {

    public static final class PlacesEntry implements BaseColumns {
        public static final String TABLE_NAME = "places";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_LON = "lon";
        public static final String COLUMN_LAT = "lat";
    }

}
