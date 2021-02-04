package com.kpitb.mustahiq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.kpitb.mustahiq.Adapter.LZCAdapterUrdu;
import com.kpitb.mustahiq.Modal.LZCModel;
import com.kpitb.mustahiq.utility.CustomTextView;
import com.kpitb.mustahiq.utility.CustomTextViewForMainPage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DistrictZakatComitteeUrdu extends AppCompatActivity implements
        SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener{

    //private LZCPageModel districtPageData;
    private RecyclerView mRecyclerView;
    private ArrayList<LZCModel> lzcModels = new ArrayList<>();
    String DistrictName,DZO,Id;
    CustomTextView dzo_Name;
    CustomTextViewForMainPage district_Title;
    LZCAdapterUrdu myAdapter;
    String DZOphoneNumber;
    ImageView callDZO;
    private Dialog clickToCall;
    private MediaPlayer mediaPlayer;

    private ProgressBar progressBar;
    private TextView loadingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_zakat_comittee_urdu);

        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);
        progressBar = findViewById(R.id.Progress);
        loadingTxt = findViewById(R.id.loadingTxt);
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();

        Id = getIntent().getStringExtra("ID");
        DistrictName = getIntent().getStringExtra("District");
        DZO = getIntent().getStringExtra("DZO");
        DZOphoneNumber = getIntent().getStringExtra("DZO_PHONE_NUMBER");

        district_Title.setText(DistrictName);
        dzo_Name.setText(DZO);
        //Toast.makeText(this, ""+DistrictName+"  \n"+DZO, Toast.LENGTH_SHORT).show();

        callDZO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                showClickToCallDialog(DZO,DZOphoneNumber);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).playOn(callDZO);
            }
        });

        //setUpRescyclerView();
        mRecyclerView = findViewById(R.id.lzc_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        isInternetOn(this);
    }

    private void loadAPIData() {
        String url = "http://zmis.swkpk.gov.pk/mustahiq/API/localZakatCommitte/" + "?dist_id=" +Id;

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

                        String tehsilid = jsonObjectNew.getString("tehsil_id");
                        String tehsilname = jsonObjectNew.getString("tehsil_name");
                        String tehsilnameUrd = jsonObjectNew.getString("tehsil_name_urdu");
                        String tehsilnamePsh = jsonObjectNew.getString("tehsil_name_pashto");
                        String lzc_chair_person = jsonObjectNew.getString("lzc_chairman");
                        String lzc_chair_person_urd = jsonObjectNew.getString("lzc_chairman_urdu");
                        String lzc_chair_person_psh = jsonObjectNew.getString("lzc_chairman_pashto");
                        String lzc_nam = jsonObjectNew.getString("lzc_name");
                        String lzc_nam_urd = jsonObjectNew.getString("lzc_name_urdu");
                        String lzc_nam_psh = jsonObjectNew.getString("lzc_name_pashto");
                        String lzc_no = jsonObjectNew.getString("lzc_phone");

                        Log.e("BANGGG",tehsilname);

                        //SET DATA TO MODEL CLASS
                        LZCModel model = new LZCModel(tehsilid,tehsilname,tehsilnameUrd,tehsilnamePsh,
                                lzc_chair_person,lzc_chair_person_urd,lzc_chair_person_psh,lzc_nam,
                                lzc_nam_urd,lzc_nam_psh,lzc_no);
                        //ADD MODEL INTO ARRAY LIST
                        lzcModels.add(model);
                        Log.e("MODEL",lzcModels.get(0).getLzcName());
                    }
                    Collections.sort(lzcModels, new Comparator<LZCModel>() {
                        @Override
                        public int compare(LZCModel o1, LZCModel o2) {
                            return o1.getLzcName().compareToIgnoreCase(o2.getLzcName());
                        }
                    });
                    myAdapter = new LZCAdapterUrdu(DistrictZakatComitteeUrdu.this,lzcModels);
                    myAdapter.notifyDataSetChanged();
                    mRecyclerView.setAdapter(myAdapter);
                    if (myAdapter.getItemCount() == 0){
                        Toast.makeText(DistrictZakatComitteeUrdu.this, "EMPTY", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(DistrictZakatComitteeUrdu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("ERROR",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Toast.makeText(DistrictZakatComitteeUrdu.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Errror",error.getMessage());
                }
                else {
                    Log.e("Errror",error.getMessage());
                    Toast.makeText(DistrictZakatComitteeUrdu.this, "Server error, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void showClickToCallDialog(String fperson, final String phoneNo) {
        clickToCall = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        clickToCall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100,
                0, 0, 0)));
        clickToCall.setContentView(R.layout.click_to_call_dialog_urdu);
        clickToCall.setCancelable(true);
        clickToCall.show();

        ImageView closedialog = clickToCall.findViewById(R.id.dialog_close);
        CustomTextView focalPersonName = clickToCall.findViewById(R.id.person_name);
        final CustomTextView contactNo = clickToCall.findViewById(R.id.person_no);
        RelativeLayout call = clickToCall.findViewById(R.id.btn_yes);
        RelativeLayout dont_Call = clickToCall.findViewById(R.id.btn_no);

        focalPersonName.setText(fperson);
        contactNo.setText(phoneNo);

        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                clickToCall.dismiss();
            }
        });

        dont_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                clickToCall.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();

                if (Build.VERSION.SDK_INT < 23) {
                    phoneCall(phoneNo);
                }else {

                    if (ActivityCompat.checkSelfPermission(DistrictZakatComitteeUrdu.this,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall(phoneNo);
                    }else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions(DistrictZakatComitteeUrdu.this,
                                PERMISSIONS_STORAGE, 9);
                    }
                }

            }
        });
    }

    private void phoneCall(String phoneNo) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+phoneNo));
            this.startActivity(callIntent);
        }else{
            Toast.makeText(this, "آپ اجازت تفویض نہیں کرتے ۔", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.start();
        onBackPressed();
        return true;
    }

    private void initViews() {
        district_Title = findViewById(R.id.districtName);
        dzo_Name = findViewById(R.id.dzoName);
        callDZO = findViewById(R.id.call_dzo_phone);
    }

    /*private void setUpRescyclerView() {

        arrayList = new ArrayList<>();
        if(DistrictName.contains("Buner")){
            populateWithBuner();
        }else if(DistrictName.contains("Mansehra")){
            populateWithMansehra();
        }else if(DistrictName.contains("Malakand")){
            populateWithMalakanad();
        }
        else if (DistrictName.contains("Abbottabad"))
        {
            populateWithAbbottabad();
        }

        mRecyclerView = findViewById(R.id.lzc_rv);
//         mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new LZCAdapter(DistrictZakatComittee.this, arrayList);
        mRecyclerView.setAdapter(myAdapter);

    }*/

  /*  private void populateWithAbbottabad() {
        districtPageData = new LZCPageModel("Bagh","Togh Sarai" ,"Sardar Mubarak Khan","03025773564",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bagh", "Hayatabad","Abdul Razaq","03142152163",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);
    }

    private void populateWithMalakanad() {

        districtPageData = new LZCPageModel("Agra",
                "Batkhela" ,
                "Farman Ud Din",
                "03458345559",
                R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Allahdand", "Batkhela","Pasand Khan","03459338953",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Amandara", "Batkhela","Amir Zeb","03445188559",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Baba Khail","Batkhela" ,"Ashfaq Ahmed","03459271431",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bakhta Cham", "Batkhela","Mohammad Ibrar","03333596501",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Batkhela No-1", "Batkhela","Mohammad Aziz","03469477909",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Batkhela No.2","Batkhela" ,"Qazi Farman Ud Din","03009056428",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Batkhela No-3", "Batkhela","Saad Khan","03459280838",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Batkhela No-4", "Batkhela","Abdullah","03455725344",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bazdara","Batkhela" ,"Gul Muhammad khan","0932505032",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bogarcham", "Batkhela","Fazali Khuda","03459329717",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dheri Allahdand", "Batkhela","Jamshaid Manan","03449041051",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dheri Julagram-I","Batkhela" ,"Adham Khan","03459281746",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dheri Jualagram-II", "Batkhela","Tauseef ","03404205390",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hisar Baba", "Batkhela","Said Amin","03468849112",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jalala","Batkhela" ,"Suhrab Shehzad","03469304231",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jarbat", "Batkhela","Tariq Aziz","03449635950",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khanori", "Batkhela","Rizwan Ullah","03325470130",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khar Gharbi","Batkhela" ,"Yasir Ahmad ","03459090246",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khar Sherqi","Batkhela" ,"Noor Muhammad","03459899634",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kot", "Batkhela","Nizam Ud Din","03109763717",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Malakand", "Batkhela","Muhammad Idress","03429720779",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Matkani","Batkhela" ,"Said Masroor","03139651076",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nagawal", "Batkhela","Shahab Ud Din","03339189125",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nal", "Batkhela","Iftikhar Ud Din","03459374456",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Palai","Batkhela" ,"Abdullah","03467769825",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Pir khail", "Batkhela","Imran Said","03479004002",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Qalangai", "Batkhela","Falak Naz","03462500568",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Said Abad ","Batkhela" ,"Sohrab Khan","03442373831",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sher Khana", "Batkhela","Ali Gul","03432386893",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Totai", "Batkhela","Muhammad israr","0932501323",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Totakan","Batkhela" ,"Muhammad Sajid","03419556606",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Zoor Mandi", "Batkhela","Sardar Hussain","03449276421",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Zulam Kot", "Batkhela","Zakir UIlah","03429836960",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ashakai","Dargai" ,"Shabir Ahmed","03484637113",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Badraga", "Dargai","Manzoor Rahman","03149647852",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dargai-1", "Dargai","Munjawar khan","03474464434",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dargai-2","Dargai" ,"Muhammad Naeem","03329499538",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dawa Khan Korona","Dargai" ,"Atta ur Rahman","03044050504",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghari Usman Khail", "Dargai","Saleem Gul","03349319989",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghani Dheri", "Dargai","Fazal Wahab","03451941964",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghundo","Dargai" ,"Irfan Ali","03459368644",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Gul Muqam","Dargai" ,"Aurang Zeb","03349303658",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Haryan Kot", "Dargai","Muhammad Ayub Khan","03349309527",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hero Shah", "Dargai","Ihsan Ur Rahman","03018368713",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khan Gheri","Dargai" ,"Muhammad Dawood","03451115247",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kharki","Dargai" ,"Momin Shah","03339476925",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kharki Dheri", "Dargai","Hayat Muhammad","03338282583",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Koper", "Dargai","Anwar Ali","03449163634",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mehardi","Dargai" ,"Kashif","03329592233",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);


        districtPageData = new LZCPageModel("Qadam Khela", "Dargai","Muhammad Khalil","03419407274",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Qaldara","Dargai" ,"Wakim Shah","03352534008",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sakhakot Ghabi","Dargai" ,"Zamir Shah","03018806306",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sakhakot Sherqi", "Dargai","Ubaid Ur Rahman","03018525255",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Warteer", "Dargai","Fazal Qadir","03428147994",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Wazir Abad","Dargai" ,"Umer Farooq","03459332894",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);


    }

    private void populateWithMansehra() {
        districtPageData = new LZCPageModel("Ahl","Mansehra " ,"Banaras Khan","03219641034",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Attarsheesha", "Mansehra","Basharat Shah","03340050806",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Baffa Gharbi", "Mansehra","Juma Khan","03016457476",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Baffa Rural","Mansehra" ,"Zabita Khan","03459620018",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Baffa Sharqi", "Mansehra","Maroof Mastar","03129274545",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bahi Bohal", "Mansehra","Muhammad Saqab","03497903634",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Baidra","Mansehra" ,"Abdul Hanan","03489595093",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bajna", "Mansehra","Khalid Pervez","03125301438",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bakki", "Mansehra","Shafiqur Rehman","03165209926",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Balhag Bala","Mansehra" ,"M Sadique Awan","03459462555",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bali Mang", "Mansehra","Dost Rehman","03151518079",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Banda Geesuch", "Mansehra","Muhammad Jameel ","03453298000",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bandi Kenth","Mansehra" ,"Muhammad Zahoor","03465126209",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bandi Khan Khel", "Mansehra","Mohammad Khurshid","03345952503",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Battal Janoobi", "Mansehra","Asad ul Haq","03335050708",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Battal Shamali","Mansehra" ,"Muhammad Ayaz Khan","03115323677",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bedadi", "Mansehra","Tahir Anwar","03332604827",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Behali", "Mansehra","Muhammad Khalid","03422585202",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bherkund","Mansehra" ,"Bnaras Khan","03439694863",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Boozbaila", "Mansehra","Qaisar","03455402225",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bugharmang", "Mansehra","Noor Alam","03478201040",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chair","Mansehra" ,"Muammad Javed","03479432174",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chikia", "Mansehra","Muhammad Aslam","03459615855",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chandoor ", "Mansehra","Muhammad Akhtar","03435091763",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chattarplain", "Mansehra","Shoukat Khan","03135771966",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chinarkot","Mansehra" ,"Rashad Hussain","03169162627",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dadar", "Mansehra","Muhammad Aslam","03449593424",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Datta", "Mansehra","Syed Shabir Shah","03015718174",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dewli", "Mansehra","Ibrar Hussan Shah","0997251250",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dhair","Mansehra" ,"Muhammad Miskeen","03005069925",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dharyal", "Mansehra","Sadaqat  Hussain","03439450228",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dhodial Arghoshal", "Mansehra","Ikhsan Nisar","03459553532",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dhodial Malkal","Mansehra" ,"Chan Zeb","03009114717",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Gandian", "Mansehra","Muhammad Sabir","03125142044",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Garwal", "Mansehra","","",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghazi Kot-1","Mansehra" ,"Muhammad Asif","03439515241",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghazi Kot-2", "Mansehra","Muhammad Jehangir","03155589686",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Giar Sucha", "Mansehra","Ghulam Nabi","03465994033",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Giderpur (J)","Mansehra" ,"Sardar Ahmed","03319289125",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Giderpur (S)", "Mansehra","Abdullah","03469700310",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Granthali", "Mansehra","Ishfaq","03439536026",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Gulibagh","Mansehra" ,"Nisar Ahmed ","03469653676",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hamsheerian", "Mansehra","Ashiq Hussain","03459621054",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hari Maira", "Mansehra","Nisar Ahmed","03005613141",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hardo  Tarangri","Mansehra" ,"Nisar Ahmed","03154638048",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Harori Khakhu", "Mansehra","Gul Badshah","03121206324",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hathi Maira ", "Mansehra","Muhammad Hafeez Khan","03129105674",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hilkot","Mansehra" ,"Muhammad Sultan","03135355504",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ichrian", "Mansehra","Qari Mohammad Ishtiaq","03429527920",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Inayat Abad", "Mansehra","Ahmed Khan","03131525736",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jaba","Mansehra" ,"Syed Nazir Shah","03413529398",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jabar Gharbi", "Mansehra","Noor Hassan","0997259216",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jabar Sharqi", "Mansehra","Ghulam Hussain","0997254482",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jabori","Mansehra" ,"Iftikhar ","03439116487",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jalgali", "Mansehra","Musasdaq Hussain","03452280436",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jaloo", "Mansehra","Muhammad Bashir Khan","03333593686",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Janghi", "Mansehra","Muhammad Asif","03455100113",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kala Maira","Mansehra" ,"Muhammad Zafran","03335099283",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Karmang Bala", "Mansehra","Dildar Ahmed","03142020637",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kery Threda ", "Mansehra","Asad Khan","0997209191",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khaki", "Mansehra","Muhammad Afzal Khan","03438664456",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khamian","Mansehra" ,"Muhammad Tanveer ","03411215907",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khowajgan", "Mansehra","Muhammad Shafique","03009048758",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khowari", "Mansehra","Naveed Roshan","03025401312",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kotli Payeen","Mansehra" ,"Ghulam Ghoos","03325178312",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Labarkot", "Mansehra","Naheem Afzal","03335401938",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Lachi Mang", "Mansehra","Tajamal Hussain Shah","03105082232",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Lassan Nawab","Mansehra" ,"Qamar Zaman","03469658473",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Lassan Takral", "Mansehra","Muhammad Javed Malik","03009111971",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Maddan", "Mansehra","Khuram Shezad","03438130252",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Maira Amjad Ali","Mansehra" ,"Muhammad Ashraf","03449432668",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Malik Pur", "Mansehra","Muhammad Asif","03449474532",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Malkan Gali Gada", "Mansehra","Daud Khan","03155812976",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Malookra","Mansehra" ,"Firoz Khan","03135821894",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Manda Gucha", "Mansehra","Muhammad Dildar Khan","03425028796",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mangal Doga", "Mansehra","Alamgeer","03429705812",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mangloor","Mansehra" ,"Muhammad Sajjad","03005649115",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mansehra Rural-1", "Mansehra","Muhammad Sadique","03469644623",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mansehra Rural-2", "Mansehra","Zahoor Ahmed","03462668912",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Matsarian","Mansehra" ,"Shams ur Rehman","03002988514",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Moh/Ayub Khan", "Mansehra","Mushtaq Ahmed","03008170340",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Moh/Channai", "Mansehra","Nisar Ahmed","03429545133",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mohallah Dab","Mansehra" ,"Shabeer Khan","03009110615",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Moh/Muftiabad", "Mansehra","Bashir Ahmed","03219670998",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mohar Kalan", "Mansehra","Haider Zaman","03465365428",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mohyan","Mansehra" ,"Nazar Hussain","03005617844",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Narbeer", "Mansehra","Jehanzeb Sohail","03478488998",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nasurdi ", "Mansehra","Naveed Khan","03335053628",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nokot","Mansehra" ,"Rukhsar ","03401901220",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Pairan", "Mansehra","Ali Asghar Awan","03469659553",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Palsala", "Mansehra","Muhammad Sadique","03432853101",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Panjool", "Mansehra","Kamran","03155152880",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Perhanna","Mansehra" ,"Toqeer Ahmed","03459098585",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Phulra", "Mansehra","Muhammad Shakeel","03440911573",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Pottah", "Mansehra","Adeel Arshad","03419499982",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Rehar", "Mansehra","Malik Javed","03319361141",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sandasar ","Mansehra" ,"Muhammad Jameel","03488551642",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shanai", "Mansehra","Arif Shah","03015506216",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sherpur", "Mansehra","Manawar Shah","03429523644",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shinkiari -1 ", "Mansehra","Faqir Hussain","03145164230",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shinkiari -2","Mansehra" ,"Jehanzeb Khan ","03105006264",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sofaida", "Mansehra","Muhammad Azeem","03479694781",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Suchan Kalan", "Mansehra","Fida Mohammad","0997253391",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Suchan Khurd", "Mansehra","Babu Nawaz Ahmed","0997233391",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sum-1","Mansehra" ,"Muhammad Sadique","03439566275",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sum-2", "Mansehra","Muhammad yousaf","03325647540",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Susal", "Mansehra","Muammad Sajjid","03449713305",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Tanda", "Mansehra","Abdul Razaq","03459617648",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Terhari ","Mansehra" ,"Ali Muhammad","03463068199",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Timber Khola", "Mansehra","Abdul Salam","03449522292",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Timbri ","Mansehra" ,"Dildar Hussain","03018127694",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Trappi", "Mansehra","Ayaz Bashir","03421953663",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Baila Sucha", "Balakot ","Babar Sultan","03469651059",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Balakot-I","Balakot " ,"Tanveer Iqbal Khan","03459554818",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Balakot-2", "Balakot ","Abdul Waheed","03459626389",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bambara Patlan", "Balakot ","Muhammad Rafique","03456979787",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ban Bagar","Balakot " ,"","",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bararkot", "Balakot ","Muhammad Yaqoob","03339882898",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bissian ", "Balakot ","Muhammad Yousaf","03109081121",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Boonja","Balakot " ,"Muhammad Sajjad ","03453764445",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dogha", "Balakot ","Riazul Haq","03319280855",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("G/Habibullah (S)", "Balakot ","Muhammad Shafique","03115818003",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("G/Habibullah (G)", "Balakot","Raja Mohd Jehangir","03118880196 ",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ganool (J)","Balakot" ,"Haji Farzaman","03463994294",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ganool (S)", "Balakot","Shafiq ur Rehman","03459554758",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Garlat", "Balakot","Lahar Asif","03328975983",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghaneela ", "Balakot","Bashsir Ali","03225436845",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hangrahi ","Balakot" ,"Muhammad Muneer","03469698318",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hasam Abad", "Balakot","Ali Asghar","03469624176",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Hassa", "Balakot","Waseem Khan","03426033505",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jabri Kaleesh", "Balakot","Molvi Mehfooz Rehman","03008366901",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jaraid (J)","Balakot" ,"Muhammad Naheem Akhtar","03479474935",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jaraid Shamli", "Balakot","Muhammad Fiaz","03449522332",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jigan", "Balakot","Mushtaq Ahmed","03459464646",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kaghan  (S)", "Balakot","Shams ur Rehman","03469532181",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kaghan (J)","Balakot" ,"Muhammad Sajjad","03478948821",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kalas Jamal Mari", "Balakot","Muhammad Idrees","03449492636",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kanshian", "Balakot","Ibrar Hussain Shah","03135956434",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Karnol", "Balakot","Muhammad Khalid","03333729474",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kewai ","Balakot" ,"Syed Riaz Shah","03469611833",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khait Sarash", "Balakot","Abdul Qadeer","03430976813",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kumi Khangeeri ","Balakot" ,"Muhammad Sadique","03409340491",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Manoor A Khan", "Balakot","Muhammad Siraj","03410999962",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);


        districtPageData = new LZCPageModel("Manoor M .Jan", "Balakot","Muhammad Naveed","03319327554",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Mitti Kot","Balakot" ,"Sadaqat ","03499025920",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Paghal Potan Des", "Balakot","Shabir Hussain","03215967875",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sangar", "Balakot","Rashad Khan","03458835237",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shohal Maizulla", "Balakot","Arif  Hussain","03459468893",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shohal Najaf ","Balakot" ,"Ihsan Ahmed","03149355555",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shukrah", "Balakot","Liaqat Ali","03418926630",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sughdar","Balakot" ,"Zardad Khan","03325294508",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Telhatta ", "Balakot","Ghuylam Murtaza","03325710361",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

    }

    private void populateWithBuner() {
        districtPageData = new LZCPageModel("Agarai","Mandanr" ,"Suleman Ali","0347-9895015",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ambela", "Mandanr","Liaqat Sayed","0343-9614520",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Amnawar", "Gagra","Said Laiq Shah","0336-9426766",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Anghapur","Daggar" ,"Azizul Haq","0332-9697756",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bagra", "Daggar","Sharifullah","0347-9526148",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bajkata", "Gagra","Tariq","0342-9641340",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Balo Khan","Daggar" ,"Fazle Wadood","0345-8520111",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bampokha", "Daggar","Muhammad Munir","0333-8419936",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bar Kalay", "Gagra","Bakht Ali","0333-9694272",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Batara","Gagra" ,"Hussain Shah","0333-9697351",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bathai", "Daggar","Shah Faisal","0346-2055297",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bazar Gay", "Daggar","M. Ilyas","0315-7550681",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bazar Kot","Gagra" ,"Nisar Ahmad","0346-9568422",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Bhero", "Gagra","Suleman","0345-9285800",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Budal", "Gagra","Jehan Muhammad","0341-9335101",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chanar","Gagra" ,"Tariq","0333-5043102",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Charorai", "Mandanr","Tauseef Ahmad","0345-5665791",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Cheena", "Gagra","Majid","0305-9717120",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Chinglai","Khado Khel" ,"Ali Zeb","0342-9647450",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dagai", "Khado Khel","Qaisar","0343-9033765",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Daggar", "Daggar","Said Faqir","0335-9694551",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dewana Baba","Gagra" ,"Bakht Farin","0347-9386780",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dherai", "Gagra","Roman Khalid","0333-3992626",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Dokada", "Daggar","Hazrat Maaz","0334-5695195",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ellai", "Daggar","Said Hakeem Shah","0344-9654897",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghazi Khaney","Daggar" ,"Muhammad Rafiq","0346-9420907",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghazi Kot", "Khado Khel","Riaz Akbar","0342-3297620",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Ghurghustoo", "Khado Khel","Sardar Ali Shah","0344-9884920",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Girarai", "Daggar","Zahid Shah","0347-9526095",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Gokand","Daggar" ,"Hameedullah","0343-9615525",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Gul Bandai", "Gagra","Tariq Zaman","0346-9461458",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Gumbat", "Gagra","Bakhtullah","0344-2656961",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Jowar","Daggar" ,"Muhammad Karim","0312-6098113",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kalpanai", "Gagra","Shakirullah","0342-9602341",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Katkala", "Daggar","Shamsul Rehman","0345-8959674",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khadar Khan","Mandanr" ,"Raaz Ali","0034-4524531",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khaidara", "Daggar","Khurshed","0344-9171878",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Khanano Dherai", "Mandanr","M. Ilyas","0333-9699687",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kingar Galai","Daggar" ,"M. Din Shah","0333-2118285",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Koga", "Mandanr","Sher Umer","0343-9610465",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Korya", "Mandanr","Ishaaq","0342-9616051",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kot","Gagra" ,"Wajid Hussain","0344-4553954",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Krapa", "Daggar","Shakir","0333-9691282",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Kulyarai", "Gagra","Hameedullah","0332-9691745",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Malak Pur","Daggar" ,"Taaj M. Khan","0314-9906163",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nagrai", "Mandanr","Tariq Aziz","0345-3352307",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nawagai", "Mandanr","Ali Sher","0342-8508550",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Nawan Kalay","Daggar" ,"Mulana Abdul Haq","0342-9429446",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Pacha Kalay", "Daggar","Azizullah","0332-9412009",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Pandher", "Gagra","M. Sohail","0344-9727951",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Panjtar","Khado Khel" ,"Anwar Ali","0348-3961449",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Rega", "Gagra","Sajid Ali","0333-9704734",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sawarai", "Gagra","Fazle Subhan","0333-9698161",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sawawai","Khado Khel" ,"Gulzar Ali Khan","0315-1997676",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Shalbandai", "Gagra","Amir Ghawas Khan","0343-9626622",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sultanwas", "Daggar","Saleem Khan","0346-9445871",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Sura", "Mandanr","Haji Noor Rehman","0344-9628120",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Tangora","Gagra" ,"Sher Ahmad","0344-9046738",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Topai", "Gagra","Nawab Ali","0346-9620160",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Topdara", "Daggar","Mulana Sher Alam","0345-4552082",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Torwasak No.1", "Daggar","Sher M. Khan","0314-9917856",R.color.blue700,
                R.drawable.ic_provider);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Torwasak No.2","Daggar" ,"Behramand Khan","0314-9819271",R.color.orange,
                R.drawable.ic_address);
        arrayList.add(districtPageData);

        districtPageData = new LZCPageModel("Totalai", "Khado Khel","Sohail Ahmad","0311-1880278",R.color.yellow50,
                R.drawable.ic_health);
        arrayList.add(districtPageData);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

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