package tk.aibolik.app.insdu.fragments.publics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import tk.aibolik.app.insdu.R;

/**
 * Created by Aibol Kussain on Jun 09, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PublicFragment extends MvpFragment<PublicView, PublicPresenter> {

    public PublicFragment() {}

    @Override
    public PublicPresenter createPresenter() {
        return new PublicPresenter();
    }

    public static PublicFragment newInstance() {
        return new PublicFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_public, container, false);
        return view;
    }
}
