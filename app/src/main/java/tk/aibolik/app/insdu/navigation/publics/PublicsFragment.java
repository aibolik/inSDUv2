package tk.aibolik.app.insdu.navigation.publics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.net.SocketException;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.Utility;
import tk.aibolik.app.insdu.custom.EndlessRecyclerViewScrollListener;
import tk.aibolik.app.insdu.models.post.Attachment;
import tk.aibolik.app.insdu.models.post.Story;
import tk.aibolik.app.insdu.navigation.publics.detail.PostDetailActivity;

/**
 * Created by Aibol Kussain on Jun 09, 2016.
 * Working on "inSDUv2". Mars Studio
 * You can contact me at: aibolikdev@gmail.com
 */
public class PublicsFragment
        extends MvpFragment<PublicsView, PublicPresenter>
        implements PublicsView, StoryAdapter.ViewHolderClickListener {

    private static final String TAG = PublicsFragment.class.getSimpleName();

    public static final String KEY_TITLE = "title";
    public static final String KEY_PAGE_ID = "pageId";

    public PublicsFragment() {
    }

    CoordinatorLayout mRootView;

    @Bind(R.id.empty_view)
    TextView mEmptyView;
    @Bind(R.id.progress)
    CircleProgressBar mProgressBar;
    @Bind(R.id.posts)
    RecyclerView mPosts;

    StoryAdapter mAdapter;

    EndlessRecyclerViewScrollListener mScrollListener;

    private String mPageTitle;
    private String mPageId;

    @Override
    public PublicPresenter createPresenter() {
        return new PublicPresenter();
    }

    public static PublicsFragment newInstance(Bundle params) {
        Log.d(TAG, "newInstance: ");
        PublicsFragment fragment = new PublicsFragment();
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            mPageTitle = args.getString(KEY_TITLE);
            mPageId = args.getString(KEY_PAGE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        mRootView = (CoordinatorLayout) getActivity().findViewById(R.id.main_content);
        View view = inflater.inflate(R.layout.fragment_public, container, false);

        ButterKnife.bind(this, view);
        mAdapter = new StoryAdapter(mPageTitle, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d(TAG, "onLoadMore: ");
                loadMore(page);
            }
        };
        mPosts.setHasFixedSize(true);
        mPosts.setLayoutManager(layoutManager);
        mPosts.setAdapter(mAdapter);
        mPosts.addOnScrollListener(mScrollListener);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            Utility.checkInternetConnection(getActivity());
            presenter.getPosts(mPageId, 0, false);
        } catch (SocketException e) {
            showMessage(e.getMessage());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadMore(int page) {
        try {
            Utility.checkInternetConnection(getActivity());
            presenter.getPosts(mPageId, page * 10, true);
        } catch (SocketException e) {
            showMessage(e.getMessage());
        }
    }

    @Override
    public void setStories(List<Story> storiesList, LinkedHashMap<Integer, List<Attachment>> map, boolean loadMore) {
        mEmptyView.setVisibility(View.GONE);
        mPosts.setVisibility(View.VISIBLE);
        if (loadMore) {
            mAdapter.addStories(storiesList, map);
        } else {
            mAdapter.setStories(storiesList, map);
        }
    }

    @Override
    public void setProgress(boolean active) {
        if (active) {
            mPosts.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorText(String text) {
        mPosts.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mEmptyView.setText(text);
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mRootView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Story story, List<Attachment> attachments) {
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        story.setAttachments(attachments);
        intent.putExtra(KEY_TITLE, mPageTitle);
        intent.putExtra(PostDetailActivity.EXTRA_STORY, story);
        startActivity(intent);
    }
}
