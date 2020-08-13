package com.kpitb.zakatandusher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.zakatandusher.Adapter.DistrictAdapter;
import com.kpitb.zakatandusher.Adapter.MainAdapter;
import com.kpitb.zakatandusher.Modal.DistrictPageModel;
import com.kpitb.zakatandusher.Modal.HomePageModel;

import java.util.ArrayList;

public class DistrictsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{
    private DistrictPageModel districtPageData;
    private RecyclerView mRecyclerView;
    DistrictAdapter myAdapter;
    private ArrayList<DistrictPageModel> arrayList;

    TextView txt_eng, txt_urdu;
    LinearLayout top_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_districts);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final TextView textView_urdu = (TextView)findViewById(R.id.txt2);
        final TextView textView_eng = (TextView)findViewById(R.id.txt);
        YoYo.with(Techniques.ZoomIn)
                .duration(1500)
                .repeat(0)
                .playOn(textView_eng);

        top_linear = findViewById(R.id.top_linear);
        txt_eng = findViewById(R.id.eng);
        txt_urdu = findViewById(R.id.urdu);

        YoYo.with(Techniques.ZoomIn)
                .duration(1500)
                .repeat(0)
                .playOn(top_linear);

        txt_urdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_eng.setBackgroundResource(0);
                txt_eng.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                txt_urdu.setBackgroundResource(R.drawable.language_bg);
                txt_urdu.setTextColor(ContextCompat.getColor(DistrictsActivity.this, R.color.colorPrimaryDark));

                textView_eng.setVisibility(View.GONE);
                textView_urdu.setVisibility(View.VISIBLE);
            }
        });

        txt_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_urdu.setBackgroundResource(0);
                txt_urdu.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                txt_eng.setBackgroundResource(R.drawable.language_bg);
                txt_eng.setTextColor(ContextCompat.getColor(DistrictsActivity.this,R.color.colorPrimaryDark));

                textView_urdu.setVisibility(View.GONE);
                textView_eng.setVisibility(View.VISIBLE);
            }
        });

        setUpRescyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpRescyclerView() {

            arrayList = new ArrayList<>();
            districtPageData = new DistrictPageModel("Abbottabad","Azghar","194" ,"0992-9310261",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Bannu", "Hameed Ullah","144","0928-9270050",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Battagram", "Wisal Shah","103","0997-310368",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Buner","Nisar Muhammad","63" ,"0939-510370",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Charsadda", "Shah Hassan","268","091-9220152",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Chitral", "Naseerullah","92","0943-412577",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("D. I. Khan","Sibghat Ullah ","187 " ,"0966-9280286",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Dir (Lower)", "Majid","154","0945-9250151",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Dir (Upper)", "Raheem Gul","122","0944-880091",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Hangu","Rehman Ullah","85" ,"0925-623095",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Haripur", "Naeem Akhtar","142","0995-615189",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Karak", "Aman Ullah","128","0927-290513",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Kohat","Badshah Khan","177" ,"0922-9230231",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Kohistan", "Sadri Alam","201","0998-407113",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Lakki Marwat", "Shaukat Ali Assistant","106","0969-510362",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Malakand","Syed Jalal Ud Din ","56" ,"0932-411397",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Mansehra", "Masal Khan","189","0997-920034",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Mardan", "Noor Jamal","222","0937-9230162",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Nowshera","Gulzar","236" ,"0923-9220061",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Peshawar", "MubasharRaza","509 ","091-2619550",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Shangla", "Rehim Gul ","44","0996-850687",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Swabi","GulzarHussain","191" ,"0938-280146",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Swat", "BehreKaram","192","0946-710183",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Tank", "Saqib Khan","62","0963-511392",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Tor Ghar", "AmjadPerviz","65","03339142119",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Bajaur","Shah Hussain","94" ,"0347-9698590",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Khyber", "Rizwan","86","0335-4600051",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Kurram", "AdilShahab","71","0300-9593289",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Mohmand", "Imtiaz Khan","53","0345-9680290",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("North Waziristan","Abid-ur-Rehman","57" ,"0928-312612",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Orakzai", "Muhammad Iqbal","35","0333-9618309",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("South Waziristan", "Aftab","68","0349-9158244",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            myAdapter = new DistrictAdapter(DistrictsActivity.this, arrayList);
            mRecyclerView.setAdapter(myAdapter);

//        mRecyclerView = findViewById(R.id.resyclerView);
////        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
////        mRecyclerView.setLayoutManager(mGridLayoutManager);
////
////        MainPageAdapter myAdapter = new MainPageAdapter(MainActivity.this, arrayList);
////        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(DistrictsActivity.this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        myAdapter.filter(newText);
        return false;
    }

    @Override
    public void onRefresh() {

    }
}
