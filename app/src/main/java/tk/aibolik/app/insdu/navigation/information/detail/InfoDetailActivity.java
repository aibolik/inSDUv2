package tk.aibolik.app.insdu.navigation.information.detail;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.models.info.InfoItem;

public class InfoDetailActivity extends AppCompatActivity {

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.item_detail)
    TextView mItemDetail;
    @Bind(R.id.toolbar_shadow)
    View mToolbarShadow;

    public static final String EXTRA_INFO_ITEM = "info_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarShadow.setVisibility(View.GONE);
        }
        InfoItem item = (InfoItem) getIntent().getSerializableExtra(EXTRA_INFO_ITEM);
        mCollapsingToolbar.setTitle(item.getTitle());

        mItemDetail.setText(item.getDescription());

        mImage.setImageResource(item.getImageResource());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
