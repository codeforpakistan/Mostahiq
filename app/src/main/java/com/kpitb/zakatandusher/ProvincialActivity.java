package com.kpitb.zakatandusher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.kpitb.zakatandusher.Adapter.DistrictAdapter;
import com.kpitb.zakatandusher.Adapter.HospitalListAdapter;
import com.kpitb.zakatandusher.Modal.DistrictPageModel;

import java.util.ArrayList;

public class ProvincialActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{
    private DistrictPageModel districtPageData;
    private RecyclerView mRecyclerView;
    HospitalListAdapter myAdapter;
    private ArrayList<DistrictPageModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincial);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        setUpRescyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){

        }else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpRescyclerView() {

        arrayList = new ArrayList<>();
        districtPageData = new DistrictPageModel("IRNUM Hospital, Peshawar","SherBahadar","" ,"09192221548",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Lady Reading Hospital (General), Peshawar", "Jahangir","","0919213513",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Lady Reading Hospital (Cardiology), Peshawar", "Tahir","","03339006939",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Khyber Teaching Hospital Peshawar","Maqadar","","03349145271",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Hayatabad Medical Complex Peshawar", "Raiz","","03339108414",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Cath Lab HMC (Cardiology)  Peshawar", "Salman ","","03005907286",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Institute of Nuclear Medicine Oncology & Radiotherapy (INOR), Abbottabad","Shoaib ","" ,"03217652683",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Institute Of Kidney Diseases, Hayatabad", "Waqas","","0919217461",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Sarhad Jail Hospital for Psychiatric Diseases, Peshawar", "Farid","","0919210460",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Khyber Eye Foundation Hospital Peshawar","Liaqat","" ,"03469198048",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Bannu Institute of Nuclear Medicine Oncology & Radiotherapy (BINOR)", "Kalim","","03339042119",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Dera Ismail Khan Institute of Nuclear  Medicine & Radiotherapy (DINAR)", "","","",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("ShoukatKhanam Hospital, Peshawar","Zar Ali","" ,"03139178765",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Swat Institute of Nuclear Medicine, Oncology & Radiology Swat (SINOR)", "Dr. Basir","","0946713037",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Al-Khidmat Hospital Nishtarabad Peshawar", "Sami Ullah Turabi","","0912612008",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Ayub Teaching Hospital, Abbottabad.","Pehlawan Shah","" ,"03018706649",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("AIMS Diabetes Hospital & Research Center,Hayatabad Peshawar", "Dawood","","091582728",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Frontier Foundation and Blood Transfusion, Peshawar.", "Zubir","","03333330903",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Fatimid Foundation Blood Bank Peshawar.","Ijaz","" ,"0915830106",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Hamza Foundation and Blood Transfusion, Peshawar. ", "Hassan","","0915845553",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Kuwait Teaching Hospital Peshawar", "Shahid","","03429210341",R.color.blue700,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new DistrictPageModel("Pakistan Kidney Hospital Abbottabad","Shaoor Iqbal","" ,"0992402220",R.color.orange,
                R.drawable.ic_health);
        arrayList.add(districtPageData);



        mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new HospitalListAdapter(ProvincialActivity.this, arrayList);
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
        searchView.setOnQueryTextListener(ProvincialActivity.this);

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
