package tk.aibolik.app.insdu.fragments.map.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.fragments.map.MapChangeListener;
import tk.aibolik.app.insdu.models.places.Category;
import tk.aibolik.app.insdu.models.places.Pin;
import tk.aibolik.app.insdu.models.places.Place;

/**
 * Created by Aibol Kussain on Jun 18, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PlacesFragment
        extends MvpFragment<PlacesView, PlacesPresenter>
        implements PlacesView, PlacesAdapter.PlacesClickListener {

    private static final String TAG = PlacesFragment.class.getSimpleName();

    private MapChangeListener mListener;

    @Bind(R.id.places)
    RecyclerView mPlaces;

    public PlacesFragment() {
    }

    @Override
    public PlacesPresenter createPresenter() {
        return new PlacesPresenter();
    }

    public static PlacesFragment newInstance() {
        return new PlacesFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        ButterKnife.bind(this, view);

        Log.d(TAG, "parentFragment" + getParentFragment());

        mListener = (MapChangeListener) getParentFragment();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.getPlaces(getContext());
    }

    @Override
    public void setPlaces(List<Place> places) {
        Category.setPlaces(places);
        PlacesAdapter adapter = new PlacesAdapter(
                getContext(),
                Category.createCategories(),
                this
        );

        mPlaces.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlaces.setHasFixedSize(true);
        mPlaces.setAdapter(adapter);
    }

    @Override
    public void onCategoryClick(int categoryId) {
        List<Pin> pins = new ArrayList<>();
        for(Place place : Category.sPlaces) {
            if(place.categoryId == categoryId) {
                pins.add(new Pin(place.name, place.lon, place.lat));
            }
        }
        mListener.addPins(pins);
    }

    @Override
    public void onPlaceClick(int placeId) {
        Pin res = null;
        for(Place place : Category.sPlaces) {
            if(place.id == placeId) {
                res = new Pin(place.name, place.lon, place.lat);
                break;
            }
        }
        mListener.showPlace(res);
    }

}
