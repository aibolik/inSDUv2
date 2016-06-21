package tk.aibolik.app.insdu.navigation.portal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;

/**
 * Created by Aibol Kussain on Jun 21, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PortalFragment extends Fragment {

    @Bind(R.id.web_view)
    WebView mWebView;

    public static PortalFragment newInstance() {
        return new PortalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portal, container, false);

        ButterKnife.bind(this, view);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://my.sdu.edu.kz");

        return view;
    }
}
