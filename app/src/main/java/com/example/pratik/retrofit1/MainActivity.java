package com.example.pratik.retrofit1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.androidbegin.com/";
    RecyclerView rvVerticalScroll;
    private VericalRecycleviewAdapter vericalRecycleviewAdapter;
//    CustomBaseAdapter customBaseAdapter;
//    ListView listView;
    ProgressDialog progressdialog;
    Country country;
    DatabaseHandler databaseHandler;
    List<Worldpopulation> worldpopulationList;
    List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listView = (ListView) findViewById(R.id.list_view);
        rvVerticalScroll = (RecyclerView)findViewById(R.id.list_view);
        country = new Country();
        worldpopulationList = new ArrayList<Worldpopulation>();
        countryList = new ArrayList<Country>();
        databaseHandler = new DatabaseHandler(MainActivity.this);

        if (isNetworkAvailable()) {
            progressdialog = new ProgressDialog(MainActivity.this);
            progressdialog.setMessage("Please Wait....");
            progressdialog.setCancelable(false);
            progressdialog.show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

            Call<Country> call = service.getStudentDetails();

            call.enqueue(new Callback<Country>() {
                @Override
                public void onResponse(Call<Country> call, Response<Country> response) {

                    progressdialog.dismiss();
                    worldpopulationList = response.body().getWorldpopulation();
                    databaseHandler.addContact(worldpopulationList);
                    databaseHandler.getAllContacts();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvVerticalScroll.setLayoutManager(linearLayoutManager);
                    vericalRecycleviewAdapter = new VericalRecycleviewAdapter(MainActivity.this, worldpopulationList);
                    vericalRecycleviewAdapter.setRecycleViewItemClickListener(recycleViewItemClickListener);
                    rvVerticalScroll.setAdapter(vericalRecycleviewAdapter);
//                    customBaseAdapter = new CustomBaseAdapter(MainActivity.this, worldpopulationList);
//                    listView.setAdapter(customBaseAdapter);



//                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//                            Worldpopulation worldpopulation = (Worldpopulation)customBaseAdapter.getItem(position);
//                            CustomBaseAdapter customBaseAdapter = (CustomBaseAdapter) parent.getAdapter();
//                            customBaseAdapter.getItem(position);
//                            Integer s1 = worldpopulation.getRank();
//                            String s2 = worldpopulation.getCountry();
//
//                            Bundle bundle = new Bundle();
//                            bundle.putString("s2", s2);
//                            bundle.putInt("s1",s1);
//
//
//                            FragmentManager fragmentManager = getFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            BlankFragment blankFragment = new BlankFragment();
//                            blankFragment.setArguments(bundle);
//                            getSupportFragmentManager().beginTransaction()
//                                    .add(R.id.frg_1, blankFragment)
//                                    .commit();
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();
//                        }
//                    });

                }

                @Override
                public void onFailure(Call<Country> call, Throwable t) {

                }
            });

        } else {
            List<Worldpopulation> worldpopulationList1 = databaseHandler.getAllContacts();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvVerticalScroll.setLayoutManager(linearLayoutManager);
            vericalRecycleviewAdapter = new VericalRecycleviewAdapter(MainActivity.this, worldpopulationList1);
            vericalRecycleviewAdapter.setRecycleViewItemClickListener(recycleViewItemClickListener);
            rvVerticalScroll.setAdapter(vericalRecycleviewAdapter);

//            customBaseAdapter = new CustomBaseAdapter(MainActivity.this, worldpopulationList1);
//            listView.setAdapter(customBaseAdapter);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private RecycleViewItemClickListener recycleViewItemClickListener = new RecycleViewItemClickListener() {
        @Override
        public void onClickItem(int position, Worldpopulation worldpopulation) {
            String s2 = worldpopulation.getCountry();
            Toast.makeText(MainActivity.this, ""+s2, Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
                            bundle.putString("s2", s2);



                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            BlankFragment blankFragment = new BlankFragment();
                            blankFragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.frg_1, blankFragment)
                                    .commit();
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();


        }
    };
}
//