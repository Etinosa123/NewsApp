package com.hfad.devotionapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.getSystemService;


public class EntertainmentFragment extends Fragment {



    String api= "ddeee79081b243caa40c5a44d240e93b";// this is the api key
    ArrayList<NewsModel> modelArrayList;
    SwipeRefreshLayout swipeRefreshLayout;
    Adapter adapter;
    String country = "ng";
    private String category ="entertainment";//the category needed fo the Fragment
    private RecyclerView recyclerViewofentertainment;
    private boolean connected = false;

    private int num  = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.entertainmentfragment, null);
        //binding the variables
        recyclerViewofentertainment  = v.findViewById(R.id.recyclerviewofentertainment);
        swipeRefreshLayout  = v.findViewById(R.id.refreshlayout);
        modelArrayList = new ArrayList<>();
        recyclerViewofentertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelArrayList);
        recyclerViewofentertainment.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNetworkAvailable()){
                    //method to call api to fetch news
                    findNews();
                }else {
                    Toast.makeText(getActivity(), "No Internet!!!", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        if (isNetworkAvailable()){
            //method to call api to fetch news
            findNews();
        }else {
            Toast.makeText(getActivity(), "No Internet!!! Drag to Refresh", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
//checking if there is Network
    private boolean isNetworkAvailable() {
        try {
            ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = null;

            if (manager != null){
                info = manager.getActiveNetworkInfo();
            }
            return info != null && info.isConnected();
        }catch (NullPointerException e)
        {
            return false;
        }
    }


    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()){
                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}