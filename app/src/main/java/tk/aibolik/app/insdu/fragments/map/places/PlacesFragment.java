package tk.aibolik.app.insdu.fragments.map.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.models.places.Category;

/**
 * Created by Aibol Kussain on Jun 18, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PlacesFragment
        extends MvpFragment<PlacesView, PlacesPresenter>
        implements PlacesView {

    private static final String TAG = PlacesFragment.class.getSimpleName();

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

        PlacesAdapter adapter = new PlacesAdapter(
                getContext(),
                Category.createCategories()
        );
        mPlaces.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlaces.setHasFixedSize(true);
        mPlaces.setAdapter(adapter);

        return view;
    }

}
