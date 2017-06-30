package com.example.bohdan.provectustask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bohdan.provectustask.data.Result;
import com.example.bohdan.provectustask.data.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RandomUserAdapter.ClickListener {
    private RecyclerView recyclerView;
    private RandomUserAdapter mAdapter;
    private ProgressBar pBar;
    private ArrayList<Results> res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.main_RV);
        pBar = (ProgressBar) findViewById(R.id.progressBar);
        pBar.setVisibility(View.VISIBLE);
        res = getUsers();
        mAdapter = new RandomUserAdapter(res, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setAutoMeasureEnabled(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Results> getUsers() {
        final ArrayList<Results> userResults = new ArrayList<>();
        ;
        App.getmRandomuserApi().getUsers("10").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    pBar.setVisibility(View.GONE);
                    userResults.addAll(response.body().getResults());
                    Log.w(" printed gson =>", res.get(0).getEmail());
                    Log.w(" printed gson =>", res.get(1).getEmail());
                    Log.w(" printed gson =>", res.get(2).getEmail());
                    Log.w(" printed gson =>", res.get(3).getEmail());
                    Log.w(" printed gson =>", res.size() + "");

                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("FAIL", "fail");
            }
        });
//        pBar.setVisibility(View.GONE);
        return userResults;

    }


    @Override
    public void ItemClicked(View v, int position) {
        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
    }
}
