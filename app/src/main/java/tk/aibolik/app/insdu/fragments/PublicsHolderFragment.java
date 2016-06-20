package tk.aibolik.app.insdu.fragments;

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

import java.util.ArrayList;
import java.util.List;

import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.fragments.publics.PublicFragment;

/**
 * Created by Aibol Kussain on Jun 09, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PublicsHolderFragment extends Fragment {

    private static final String TAG = PublicsHolderFragment.class.getSimpleName();

    public PublicsHolderFragment() {}

    public static PublicsHolderFragment newInstance() {
        return new PublicsHolderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publics, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setTabTextColors(
                getActivity().getResources().getColor(R.color.text_primary),
                getActivity().getResources().getColor(R.color.colorAccent)
                );
        tabs.setSelectedTabIndicatorColor(
                getActivity().getResources().getColor(R.color.colorAccent)
        );
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);

        Bundle sdu = new Bundle();
        sdu.putString(PublicFragment.KEY_TITLE, "SDU");
        sdu.putString(PublicFragment.KEY_PAGE_ID, "-23192814");
        adapter.addFragment(PublicFragment.newInstance(sdu), "SDU");

        Bundle sduLife = new Bundle();
        sduLife.putString(PublicFragment.KEY_TITLE, "SDU Life");
        sduLife.putString(PublicFragment.KEY_PAGE_ID, "-53746469");
        adapter.addFragment(PublicFragment.newInstance(sduLife), "SDU Life");

        Bundle sduLove = new Bundle();
        sduLove.putString(PublicFragment.KEY_TITLE, "SDU Love");
        sduLove.putString(PublicFragment.KEY_PAGE_ID, "-61889571");
        adapter.addFragment(PublicFragment.newInstance(sduLove), "SDU Love");
        viewPager.setAdapter(adapter);
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

}
