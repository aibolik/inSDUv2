package tk.aibolik.app.insdu.fragments.map.places;

import android.content.Context;
import android.os.AsyncTask;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import tk.aibolik.app.insdu.data.DbHelper;
import tk.aibolik.app.insdu.models.places.Place;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PlacesPresenter extends MvpBasePresenter<PlacesView> {

    public void getPlaces(final Context context) {
        new AsyncTask<Void, Void, List<Place>>() {

            @Override
            protected List<Place> doInBackground(Void... params) {
                DbHelper dbHelper = new DbHelper(context);

                return dbHelper.getAllPlaces();
            }

            @Override
            protected void onPostExecute(List<Place> places) {
                super.onPostExecute(places);
                if(isViewAttached()) {
                    getView().setPlaces(places);
                }
            }
        }.execute();
    }

}
