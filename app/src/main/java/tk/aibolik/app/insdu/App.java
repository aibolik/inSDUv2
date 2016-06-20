package tk.aibolik.app.insdu;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.vk.sdk.VKSdk;

/**
 * Created by Aibol Kussain on Jun 14, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(getApplicationContext());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//        DatabaseReference database = FirebaseDatabase.getInstance().getReference("web/data/places");
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("FireBase", "getValue: " + dataSnapshot.getValue());
//                Log.d("FireBase", "getKey: " + dataSnapshot.getKey());
//                Log.d("FireBase", "getChildrenCount: " + dataSnapshot.getChildrenCount());
//                Iterable<DataSnapshot> items = dataSnapshot.getChildren();
//                for(DataSnapshot item : items) {
//                    Log.d("FireBase", "item: " + item);
//                    Log.d("FireBase", "item.getValue: " + item.getValue());
//                    Log.d("FireBase", "item.getKey: " + item.getKey());
//                    Place place = item.getValue(Place.class);
//                    Log.d("Place", "place.id: " + place.id);
//                    Log.d("Place", "place.name: " + place.name);
//                    Log.d("Place", "place.categoryId: " + place.categoryId);
//                    Log.d("Place", "place.desc: " + place.desc);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        database.addValueEventListener(listener);

    }
}
