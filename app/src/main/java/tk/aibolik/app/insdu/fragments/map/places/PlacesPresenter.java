package tk.aibolik.app.insdu.fragments.map.places;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import tk.aibolik.app.insdu.models.places.Place;

/**
 * Created by Aibol Kussain on Jun 19, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PlacesPresenter extends MvpBasePresenter<PlacesView> {

    public void getPlaces() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("web/data/places");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Firebase", "getPlaces.onDataChange: ");
                Iterable<DataSnapshot> items = dataSnapshot.getChildren();
                List<Place> places = new ArrayList<Place>();
                for(DataSnapshot item : items) {
                    Place place = item.getValue(Place.class);
                    places.add(place);
                }
                Log.d("Firebase", "size: " + places.size());
                if(isViewAttached()) {
                    getView().setPlaces(places);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
