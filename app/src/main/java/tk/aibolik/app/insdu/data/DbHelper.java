package tk.aibolik.app.insdu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tk.aibolik.app.insdu.data.Contracts.PlacesEntry;
import tk.aibolik.app.insdu.models.places.Place;

/**
 * Created by Aibol Kussain on Jun 21, 2016.
 * Working on "inSDUv2". Smart Digital Solutions
 * You can contact me at: aibolikdev@gmail.com
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "insdu.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PLACES_TABLE = "CREATE TABLE " + PlacesEntry.TABLE_NAME + "(" +
                PlacesEntry._ID + " INTEGER PRIMARY KEY," +
                PlacesEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL," +
                PlacesEntry.COLUMN_NAME + " TEXT NOT NULL," +
                PlacesEntry.COLUMN_DESC + " TEXT NOT NULL," +
                PlacesEntry.COLUMN_LON + " REAL NOT NULL," +
                PlacesEntry.COLUMN_LAT + " REAL NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_PLACES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PlacesEntry.TABLE_NAME);
        onCreate(db);
    }

    public void addPlace(Place place) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlacesEntry._ID, place.id);
        values.put(PlacesEntry.COLUMN_CATEGORY_ID, place.categoryId);
        values.put(PlacesEntry.COLUMN_NAME, place.name);
        values.put(PlacesEntry.COLUMN_DESC, place.desc);
        values.put(PlacesEntry.COLUMN_LON, place.lon);
        values.put(PlacesEntry.COLUMN_LAT, place.lat);
        db.insert(
          PlacesEntry.TABLE_NAME,
                null,
                values
        );
        Log.d("db", "addPlace: " + place.name);
    }

    public void clearAll() {
        Log.d("db", "clearAll: ");
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PlacesEntry.TABLE_NAME, null, null);
    }

    public List<Place> getAllPlaces() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                PlacesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Place> places = new ArrayList<>();

        while(cursor.moveToNext()) {
            places.add(new Place(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getDouble(5)
            ));
        }

        return places;
    }
}
