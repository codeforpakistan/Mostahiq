package com.kpitb.mustahiq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.mustahiq.Adapter.HospitalListAdapter;
import com.kpitb.mustahiq.Modal.SourceList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProvincialActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{
    //private SourceList sourceLists;
    private RecyclerView mRecyclerView;
    HospitalListAdapter myAdapter;
    private ArrayList<SourceList> sourceListArrayList = new ArrayList<>();

    TextView txt_eng, txt_urdu;
    LinearLayout top_linear;

    //private ArrayList<SourceList> sourceLists;

    private MediaPlayer mediaPlayer;

    String id;

    private ProgressBar progressBar;
    private TextView loadingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincial);

        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);
        progressBar = findViewById(R.id.Progress);
        loadingTxt = findViewById(R.id.loadingTxt);

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
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(txt_urdu);
                txt_eng.setBackgroundResource(0);
                txt_eng.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                txt_urdu.setBackgroundResource(R.drawable.language_bg);
                txt_urdu.setTextColor(ContextCompat.getColor(ProvincialActivity.this, R.color.colorPrimaryDark));

                textView_eng.setVisibility(View.GONE);
                textView_urdu.setVisibility(View.VISIBLE);
            }
        });

        txt_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(txt_eng);
                txt_urdu.setBackgroundResource(0);
                txt_urdu.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                txt_eng.setBackgroundResource(R.drawable.language_bg);
                txt_eng.setTextColor(ContextCompat.getColor(ProvincialActivity.this,R.color.colorPrimaryDark));

                textView_urdu.setVisibility(View.GONE);
                textView_eng.setVisibility(View.VISIBLE);
            }
        });

        mRecyclerView = findViewById(R.id.resyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setUpRescyclerView();
        isInternetOn(this);
    }

    private void loadAPIData() {
        //String url = "http://mustahiq.mawaiskhan.com/API/districtHospitals";
        String url = "http://zmis.swkpk.gov.pk/mustahiq/API/districtHospitals";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //CONVERT STRING INTO JSON OBJECT
                    JSONObject jsonObject = new JSONObject(response);
                    //GET SOURCE ARRAY FROM THAT OBJECT
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.e("KEYZZZ",jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectNew = jsonArray.getJSONObject(i);

                        String hos_name = jsonObjectNew.getString("dh_name");
                        String hos_name_urdu = jsonObjectNew.getString("dh_name_urdu");
                        String hos_name_pashto = jsonObjectNew.getString("dh_name_pushto");
                        String f_name = jsonObjectNew.getString("dh_focal_person");
                        String f_name_urdu = jsonObjectNew.getString("dh_focal_person_urdu");
                        String f_name_pashto = jsonObjectNew.getString("dh_focal_person_pushto");
                        String f_phone = jsonObjectNew.getString("dh_phone");
                        String h_district_id = jsonObjectNew.getString("dist_id");
                        String h_district_name = jsonObjectNew.getString("dist_name");
                        String h_lat = jsonObjectNew.getString("dh_latitude");
                        String h_long = jsonObjectNew.getString("dh_longitude");

                        Log.e("BANGGG",hos_name);

                        //SET DATA TO MODEL CLASS
                        SourceList model = new SourceList(hos_name,hos_name_urdu,hos_name_pashto,f_name,
                                f_name_urdu,f_name_pashto,f_phone,h_district_id,
                                h_district_name,h_lat,h_long);
                        //ADD MODEL INTO ARRAY LIST
                        sourceListArrayList.add(model);
                        Log.e("MODEL",sourceListArrayList.get(0).getHos_name());
                    }
                    Collections.sort(sourceListArrayList, new Comparator<SourceList>() {
                        @Override
                        public int compare(SourceList o1, SourceList o2) {
                            return o1.getHos_name().compareToIgnoreCase(o2.getHos_name());
                        }
                    });
                    myAdapter = new HospitalListAdapter(ProvincialActivity.this,sourceListArrayList);
                    myAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(myAdapter);
                    if (myAdapter.getItemCount() == 0){
                        Toast.makeText(ProvincialActivity.this, "EMPTY", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(ProvincialActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("ERROR",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(ProvincialActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Errror",error.getMessage());
                }
                else {
                    Log.e("Errror",error.getMessage());
                    Toast.makeText(ProvincialActivity.this, "Server error, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    /*    {
            @Override
            protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("dist_id", id);
            return params;
        }
        };*/
        //ADD REQUEST TO QUEUE
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //add progress
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
            @Override
            public void onRequestFinished(Request<String> request) {
                progressBar.setVisibility(View.GONE);
                loadingTxt.setVisibility(View.GONE);
            }
        });
    }

    public boolean isInternetOn(Context context){
        if (isMobileOrWifiConnectivityAvailable(context))
        {
            loadAPIData();
        }
        else {
            progressBar.setVisibility(View.GONE);
            loadingTxt.setVisibility(View.GONE);
            Toast.makeText(context, "No internet, please connect to internet", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public static boolean isMobileOrWifiConnectivityAvailable(Context ctx) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected()) {
                        haveConnectedWifi = true;
                    }
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected()) {
                        haveConnectedMobile = true;
                    }
            }
        } catch (Exception e) {
            String error = e.toString();
            Log.e("Internet Exception",error);
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.start();
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

    /*private void setUpRescyclerView() {

        arrayList = new ArrayList<>();
        hospitalPageModel = new HospitalPageModel("IRNUM Hospital, Peshawar","SherBahadar","" ,"09192221548",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Lady Reading Hospital (General), Peshawar", "Jahangir","","0919213513",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Lady Reading Hospital (Cardiology), Peshawar", "Tahir","","03339006939",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Khyber Teaching Hospital Peshawar","Maqadar","","03349145271",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Hayatabad Medical Complex Peshawar", "Raiz","","03339108414",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Cath Lab HMC (Cardiology)  Peshawar", "Salman ","","03005907286",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Institute of Nuclear Medicine Oncology & Radiotherapy (INOR), Abbottabad","Shoaib ","" ,"03217652683",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Institute Of Kidney Diseases, Hayatabad", "Waqas","","0919217461",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Sarhad Jail Hospital for Psychiatric Diseases, Peshawar", "Farid","","0919210460",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Khyber Eye Foundation Hospital Peshawar","Liaqat","" ,"03469198048",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Bannu Institute of Nuclear Medicine Oncology & Radiotherapy (BINOR)", "Kalim","","03339042119",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Dera Ismail Khan Institute of Nuclear  Medicine & Radiotherapy (DINAR)", "","","",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("ShoukatKhanam Hospital, Peshawar","Zar Ali","" ,"03139178765",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Swat Institute of Nuclear Medicine, Oncology & Radiology Swat (SINOR)", "Dr. Basir","","0946713037",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Al-Khidmat Hospital Nishtarabad Peshawar", "Sami Ullah Turabi","","0912612008",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Ayub Teaching Hospital, Abbottabad","Pehlawan Shah","" ,"03018706649",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("AIMS Diabetes Hospital & Research Center,Hayatabad Peshawar", "Dawood","","091582728",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Frontier Foundation and Blood Transfusion, Peshawar", "Zubir","","03333330903",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Fatimid Foundation Blood Bank Peshawar","Ijaz","" ,"0915830106",R.color.orange);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Hamza Foundation and Blood Transfusion, Peshawar", "Hassan","","0915845553",R.color.yellow50);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Kuwait Teaching Hospital Peshawar", "Shahid","","03429210341",R.color.blue700);
        arrayList.add(hospitalPageModel);

        hospitalPageModel = new HospitalPageModel("Pakistan Kidney Hospital Abbottabad","Shaoor Iqbal","" ,"0992402220",R.color.orange);
        arrayList.add(hospitalPageModel);


        mRecyclerView = findViewById(R.id.resyclerView);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new HospitalListAdapter(ProvincialActivity.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);

    }*/

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


