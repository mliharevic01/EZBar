package nyc.ezbar.ezbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;



import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;






import android.os.Handler;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;





import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import java.io.File;
import java.util.ArrayList;

//For Textview changes


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ImageLoader imageLoader;
    private DisplayImageOptions displayImageOptions;
    private SearchView searchView;
    //private HighlightRenderer highlightRenderer;
    private Context context;
    private Boolean isChanged =false;
    private ViewGroup cell;


    private Handler handler = new Handler();
    //Chrome Custom Tab
    CustomTabsServiceConnection mCustomTabsServiceConnection;
    CustomTabsClient mClient;
    CustomTabsSession mCustomTabsSession;

    //Constants
    private static final int HITS_PER_PAGE = 10;

    /** Number of items before the end of the list past which we start loading more content. */
    private static final int LOAD_MORE_THRESHOLD = 5;


    private static final String LOG_TAG = "main activity";

    //This is for the google chrome custom tabs
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";  // Change when in stable



    //  private static final String SHOWCASE_ID3 = "a";

    //for the jasper card
    private NavigationView navigationView;
    private Menu menu;
    private MenuItem card;
    private File downloadDir;
    private ArrayList<String> paths = new ArrayList<String>();
    private File[] files;

 /*   private FilenameFilter filter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String filename) {
            return filename.matches("jaspercard.pkpass");
        }
    };
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.drawer_main);

         /*This overides the defualt transition for the activity
        * and set in out custom transition for the XML files*/
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


        //Merges Headers, directory and portal adapter to one adapter to be assigned to a list view



        //Sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        Log.e(MainActivity.LOG_TAG, " it is null ");

        //Sets up the Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // In the onStart lifecylce function

    /*You will find the client connection for the google chrome custum tabs
    *   -This works along side the  */
    @Override
    protected void onStart() {
        super.onStart();


        mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                mClient = customTabsClient;
                mClient.warmup(0L);
                mCustomTabsSession = mClient.newSession(null);
            }



            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
                context.unbindService(mCustomTabsServiceConnection);
            }
        };

        CustomTabsClient.bindCustomTabsService(MainActivity.this, CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection);
    }








    /*This allows the icons to change to the color white
        * it is then  in the onNavigationItemSelected() function*/
    ColorStateList myColorStateList = new ColorStateList(
            new int[][]{
                    new int[]{android.R.attr.state_pressed}, //1
                    new int[]{android.R.attr.state_checked}, //2
                    new int[]{}
            },
            new int[]{
                    Color.WHITE, //1
                    Color.WHITE, //2
                    Color.rgb(102, 102, 102)
            }
    );




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        int id = item.getItemId();
       /* MenuItem item1 = item;*/
        navigationView.setItemIconTintList(myColorStateList);

        //TODO find a way to color the icons differently (completed)
        /*below  the item background is set to its own drawable folder where the color can be found*/

        if (id == R.id.nav_home) {


            navigationView.setItemBackgroundResource(R.drawable.nav_item1_highlight_color);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

