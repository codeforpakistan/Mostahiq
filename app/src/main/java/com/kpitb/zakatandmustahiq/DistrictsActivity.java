package com.kpitb.zakatandmustahiq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
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
import com.kpitb.zakatandmustahiq.Adapter.DistrictAdapter;
import com.kpitb.zakatandmustahiq.Modal.DistrictModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DistrictsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{
    //private DistrictModel districtModel;
    private RecyclerView mRecyclerView;
    DistrictAdapter myAdapter;
    private ArrayList<DistrictModel> districtModelArrayList = new ArrayList<>();

    TextView txt_eng, txt_urdu;
    LinearLayout top_linear;
    MediaPlayer mediaPlayer;

    private ProgressBar progressBar;
    private TextView loadingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_districts);

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
                txt_urdu.setTextColor(ContextCompat.getColor(DistrictsActivity.this, R.color.colorPrimaryDark));

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
                txt_eng.setTextColor(ContextCompat.getColor(DistrictsActivity.this,R.color.colorPrimaryDark));

                textView_urdu.setVisibility(View.GONE);
                textView_eng.setVisibility(View.VISIBLE);
            }
        });

        //setUpRescyclerView();
        mRecyclerView = findViewById(R.id.resyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        isInternetOn(this);
    }

    private void loadAPIData() {
        //String url = "http://mustahiq.mawaiskhan.com/API/districtOffices";
        String url = "http://zmis.swkpk.gov.pk/mustahiq/API/districtOffices";

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

                        String d_id = jsonObjectNew.getString("dist_id");
                        String d_nam = jsonObjectNew.getString("dist_name");
                        String d_nam_urd = jsonObjectNew.getString("dist_name_urdu");
                        String d_nam_psht = jsonObjectNew.getString("dist_name_pashto");
                        String d_offcr_name = jsonObjectNew.getString("dist_officer_name");
                        String d_offcr_name_urd = jsonObjectNew.getString("dist_officer_name_urdu");
                        String d_offcr_name_psht = jsonObjectNew.getString("dist_officer_name_pashto");
                        String dst_lzc_numbers = jsonObjectNew.getString("dist_no_lzc");
                        String dst_phone = jsonObjectNew.getString("dist_phone");
                        String dst_chairmanName = jsonObjectNew.getString("dist_chairman_name");
                        String dst_chairmanNameUrdu = jsonObjectNew.getString("dist_chairman_name_urdu");
                        String dst_chairmanNamePashto = jsonObjectNew.getString("dist_chairman_name_pashto");
                        String dst_chairmanPhone = jsonObjectNew.getString("dist_chairman_phone");
                        String dst_lat = jsonObjectNew.getString("dist_latitude");
                        String dst_long = jsonObjectNew.getString("dist_longitude");

                        Log.e("HOOLLAA",dst_lat + dst_long);

                        //SET DATA TO MODEL CLASS
                        DistrictModel model = new DistrictModel(d_id,d_nam,d_nam_urd,d_nam_psht,d_offcr_name,
                                d_offcr_name_urd,d_offcr_name_psht,dst_lzc_numbers,dst_phone,dst_chairmanName,
                                dst_chairmanNameUrdu,dst_chairmanNamePashto,dst_chairmanPhone,dst_lat,
                                dst_long);
                        //ADD MODEL INTO ARRAY LIST
                        districtModelArrayList.add(model);
                        Log.e("MODEL",districtModelArrayList.get(0).getD_name());
                    }

                    Collections.sort(districtModelArrayList, new Comparator<DistrictModel>() {
                        @Override
                        public int compare(DistrictModel oneModel, DistrictModel twModel) {
                            return oneModel.getD_name().compareToIgnoreCase(twModel.getD_name());
                        }
                    });

                    myAdapter = new DistrictAdapter(DistrictsActivity.this,districtModelArrayList);
                    mRecyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    if (myAdapter.getItemCount() == 0){
                        Toast.makeText(DistrictsActivity.this, "EMPTY", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(DistrictsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("ERROR",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(DistrictsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Errror",error.getMessage());
                }
                else {
                    Log.e("Errror",error.getMessage());
                    Toast.makeText(DistrictsActivity.this, "Server error, try again", Toast.LENGTH_SHORT).show();
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

   /* private void setUpRescyclerView() {

            arrayList = new ArrayList<>();
            districtPageData = new DistrictPageModel("Abbottabad","Azghar","194" ,"0992-9310261",R.color.white,
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

            districtPageData = new DistrictPageModel("Charsadda", "Shah Hassan","268","091-9220152",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Chitral", "Naseerullah","92","0943-412577",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("D. I. Khan","Sibghat Ullah ","187 " ,"0966-9280286",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Dir (Lower)", "Majid","154","0945-9250151",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Dir (Upper)", "Raheem Gul","122","0944-880091",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Hangu","Rehman Ullah","85" ,"0925-623095",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Haripur", "Naeem Akhtar","142","0995-615189",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Karak", "Aman Ullah","128","0927-290513",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Kohat","Badshah Khan","177" ,"0922-9230231",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Kohistan", "Sadri Alam","201","0998-407113",R.color.grey,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Lakki Marwat", "Shaukat Ali Assistant","106","0969-510362",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Malakand","Syed Jalal Ud Din ","56" ,"0932-411397",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Mansehra", "Masal Khan","189","0997-920034",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Mardan", "Noor Jamal","222","0937-9230162",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Nowshera","Gulzar","236" ,"0923-9220061",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Peshawar", "MubasharRaza","509 ","091-2619550",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Shangla", "Rehim Gul ","44","0996-850687",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Swabi","GulzarHussain","191" ,"0938-280146",R.color.grey,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Swat", "BehreKaram","192","0946-710183",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Tank", "Saqib Khan","62","0963-511392",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Tor Ghar", "AmjadPerviz","65","03339142119",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Bajaur","Shah Hussain","94" ,"0347-9698590",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Khyber", "Rizwan","86","0335-4600051",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Kurram", "AdilShahab","71","0300-9593289",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Mohmand", "Imtiaz Khan","53","0345-9680290",R.color.white,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("North Waziristan","Abid-ur-Rehman","57" ,"0928-312612",R.color.white,
                    R.drawable.ic_address);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("Orakzai", "Muhammad Iqbal","35","0333-9618309",R.color.white,
                    R.drawable.ic_health);
            arrayList.add(districtPageData);

            districtPageData = new DistrictPageModel("South Waziristan", "Aftab","68","0349-9158244",R.color.grey,
                    R.drawable.ic_provider);
            arrayList.add(districtPageData);

            mRecyclerView = findViewById(R.id.resyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            myAdapter = new DistrictAdapter(DistrictsActivity.this, arrayList);
            mRecyclerView.setAdapter(myAdapter);

    }*/

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
