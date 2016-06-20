package tk.aibolik.app.insdu.fragments.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.fragments.map.places.PlacesFragment;
import tk.aibolik.app.insdu.models.places.Pin;

/**
 * Created by Aibol Kussain on Jun 18, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class MapHolderFragment
        extends Fragment
        implements OnMapReadyCallback, MapChangeListener {

    private static final String TAG = MapHolderFragment.class.getSimpleName();

    public static final CameraPosition KASKELEN =
            new CameraPosition.Builder().target(new LatLng(43.20123718072794, 76.63677100092173))
                    .zoom(12.7f)
                    .build();

    public static final CameraPosition SDU =
            new CameraPosition.Builder().target(new LatLng(43.208292135715816, 76.66898366063833))
                    .zoom(15.5f)
                    .build();

    private GoogleMap mMap;

    @Bind(R.id.viewpager)
    ViewPager mPager;

    public MapHolderFragment() {}

    public static MapHolderFragment newInstance() {
        return new MapHolderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publics, container, false);
        ButterKnife.bind(this, view);

        setupViewPager();

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setTabTextColors(
                getActivity().getResources().getColor(R.color.text_primary),
                getActivity().getResources().getColor(R.color.colorAccent)
        );
        tabs.setSelectedTabIndicatorColor(
                getActivity().getResources().getColor(R.color.colorAccent)
        );
        tabs.setupWithViewPager(mPager);

        return view;
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        PlacesFragment placesFragment = PlacesFragment.newInstance();
        mapFragment.getMapAsync(this);

        adapter.addFragment(placesFragment, "Places");
        adapter.addFragment(mapFragment, "Map");
        mPager.setAdapter(adapter);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        changeCamera(CameraUpdateFactory.newCameraPosition(SDU));
    }

    @Override
    public void addPins(List<Pin> pins) {
        mMap.clear();
        for(Pin pin : pins) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(pin.lon, pin.lat)).title(pin.name));
        }
        changeCamera(CameraUpdateFactory.newCameraPosition(KASKELEN));
        mPager.setCurrentItem(1);
    }

    @Override
    public void showPlace(Pin pin) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng(pin.lon, pin.lat)).title(pin.name).visible(true));
        changeCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(pin.lon, pin.lat), 15.5f));
        mPager.setCurrentItem(1);
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void changeCamera(CameraUpdate update) {
        mMap.animateCamera(update, 1, null);
    }
}
