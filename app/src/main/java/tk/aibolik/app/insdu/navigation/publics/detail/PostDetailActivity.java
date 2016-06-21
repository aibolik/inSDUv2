package tk.aibolik.app.insdu.navigation.publics.detail;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.models.post.Attachment;
import tk.aibolik.app.insdu.models.post.Story;
import tk.aibolik.app.insdu.navigation.publics.PublicsFragment;

public class PostDetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private static final String TAG = PostDetailActivity.class.getSimpleName();
    public static final String EXTRA_STORY = "story";

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.slider)
    SliderLayout mSlider;
    @Bind(R.id.item_detail)
    TextView mItemDetail;
    @Bind(R.id.toolbar_shadow)
    View mToolbarShadow;

    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarShadow.setVisibility(View.GONE);
        }
        mCollapsingToolbar.setTitle("");
        toolbar.setTitle("");

        Story story = (Story) getIntent().getSerializableExtra(EXTRA_STORY);
        List<Attachment> attachments = story.getAttachments();
        mTitle = getIntent().getStringExtra(PublicsFragment.KEY_TITLE);

        mItemDetail.setText(story.getText());


        if (attachments.size() > 0) {

            mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        mCollapsingToolbar.setTitle(mTitle);
                        isShow = true;
                    } else if (isShow) {
                        mCollapsingToolbar.setTitle("");
                        isShow = false;
                    }

                }
            });

            for (Attachment attachment : attachments) {
                TextSliderView textSliderView = new TextSliderView(this);
                // initialize a SliderLayout
                textSliderView
                        .description(mTitle)
                        .image(attachment.getCover_url())
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(this);
                mSlider.addSlider(textSliderView);
            }
            mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mSlider.setCustomAnimation(new DescriptionAnimation());
            mSlider.addOnPageChangeListener(this);
        }
        else {
            mCollapsingToolbar.setTitle(mTitle);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
