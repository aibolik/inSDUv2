package tk.aibolik.app.insdu.data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;

import tk.aibolik.app.insdu.R;

/**
 * Created by Aibol Kussain on Jun 03, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public final class InfoItem {

    private String mTitle;
    private String mDescription;
    private int mImageResource;

    public InfoItem(String title, String description, int imageResource) {
        mTitle = title;
        mDescription = description;
        mImageResource = imageResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public static ArrayList<InfoItem> getInfoItemsList(Context context) {
        Resources resources = context.getResources();
        String[] titles = resources.getStringArray(R.array.info_titles);
        String[] descriptions = resources.getStringArray(R.array.info_descriptions);
        TypedArray images = resources.obtainTypedArray(R.array.info_pictures);

        ArrayList<InfoItem> items = new ArrayList<>();

        for(int i = 0; i < images.length(); i++) {
            items.add(new InfoItem(titles[i], descriptions[i], images.getResourceId(i, R.drawable.a)));
        }
        images.recycle();

        return items;
    }
}
