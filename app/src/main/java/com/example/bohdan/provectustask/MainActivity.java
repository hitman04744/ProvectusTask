package com.example.bohdan.provectustask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bohdan.provectustask.adapters.RandomUserAdapter;
import com.example.bohdan.provectustask.application.App;
import com.example.bohdan.provectustask.data.Result;
import com.example.bohdan.provectustask.data.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RandomUserAdapter.ClickListener {
    private RecyclerView recyclerView;
    private RandomUserAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar pBar;
    private ArrayList<Results> resultUsers = new ArrayList<>();
    private boolean mNetworkState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.main_RV);
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        initRecyclerView();
        isOnline();
        pBar.setVisibility(View.VISIBLE);
        if (resultUsers.size() == 0 && mNetworkState) {
            getUsers();
            Log.d("USERS", resultUsers.size() + "");
            initRecyclerView();
        }
//

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_RV);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isOnline();
                resultUsers.clear();
                getUsers();


            }
        });
    }

    public void initRecyclerView() {
        mAdapter = new RandomUserAdapter(resultUsers, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setAutoMeasureEnabled(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

    }

    public void getUsers() {
        final ArrayList<Results> userResults = new ArrayList<>();
        App.getmRandomuserApi().getUsers("20").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    pBar.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    resultUsers.addAll(response.body().getResults());
                    mAdapter.notifyDataSetChanged();
                    Log.d("RESPONSE", userResults.size() + "");
                }

            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("FAIL", "fail");
                Toast.makeText(getApplicationContext(), "oops something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void ItemClicked(View v, int position) {
        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
//        Bundle bd= new Bundle();
//        bd.putParcelable("user",res.get(position));
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("user", resultUsers.get(position));
        startActivity(intent);
    }

    public void isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                mNetworkState = true;
//                Toast.makeText(this, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan

                mNetworkState = true;
//                Toast.makeText(this, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
            }
        } else {
            mNetworkState = false;
            Toast.makeText(this, "There is no Internet Connection", Toast.LENGTH_SHORT).show();
            createNetErrorDialog();
        }
    }

    protected void createNetErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You need a network connection to use this application. Please turn on mobile network or Wi-Fi in Settings.")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                Intent intent;
                                if (wifi.isWifiEnabled()) {
                                    intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                                } else {
                                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                }
                                startActivity(intent);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.this.finish();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }



}
