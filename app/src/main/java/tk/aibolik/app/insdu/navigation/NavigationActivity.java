package tk.aibolik.app.insdu.navigation;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import tk.aibolik.app.insdu.R;
import tk.aibolik.app.insdu.navigation.information.InfoFragment;
import tk.aibolik.app.insdu.navigation.map.MapHolderFragment;
import tk.aibolik.app.insdu.navigation.portal.PortalFragment;
import tk.aibolik.app.insdu.navigation.publics.PublicsHolderFragment;
import tk.aibolik.app.insdu.sync.AppSyncAdapter;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar_shadow)
    View mToolbarShadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarShadow.setVisibility(View.GONE);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView != null) {
            setupDrawerContent(navigationView);
        }

        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        AppSyncAdapter.initializeSyncAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d("inSDU", "onNavigationItemSelected: ");
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_info:
                fragment = InfoFragment.newInstance();
                break;
            case R.id.nav_map:
                fragment = MapHolderFragment.newInstance();
                break;
            case R.id.nav_portal:
                fragment = PortalFragment.newInstance();
                break;
            case R.id.nav_publics:
                fragment = PublicsHolderFragment.newInstance();
                break;
            default:
                break;
        }
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .commit();
        }
        // Close the navigation drawer when an item is selected.
        setTitle(item.getTitle());
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }
}
