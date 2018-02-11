package com.puneet.android.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.puneet.android.R;
import com.puneet.android.adapter.SectionAdapter;
import com.puneet.android.model.Entry;
import com.puneet.android.model.ServerResponse;
import com.puneet.android.network.EntryApiResponse;
import com.puneet.android.service.ApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SectionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private String mNewsCategory;
    public SectionAdapter sectionAdapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    List<Entry> entryList;
    Unbinder unbinder;
    Map<String, Object> recievedMap = new HashMap<>();
    String getmNewsCategory;
    ServerResponse serverResponse;

    @BindView(R.id.recyclerView) RecyclerView myRecyclerView;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;

    public SectionFragment() {
    }

    public static SectionFragment newInstance(String newsCategory) {
        SectionFragment fragment = new SectionFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("CATEGORY", newsCategory);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsCategory = getArguments().getString("CATEGORY");
        }
        Log.d(SectionFragment.class.getSimpleName(), "Category pased is" + mNewsCategory);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section_story, container, false);
        unbinder = ButterKnife.bind(this, view);
        sectionAdapter = new SectionAdapter(getContext(), entryList);
        swipeRefreshLayout.setOnRefreshListener(this);
        myRecyclerView.setNestedScrollingEnabled(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(sectionAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ApiService.getResults(getContext(), mNewsCategory);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setRefreshing(true);
        if (!EventBus.getDefault().hasSubscriberForEvent(EntryApiResponse.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Map<String, Object> recievedMap) {
        getmNewsCategory = (String) recievedMap.get("category");

        swipeRefreshLayout.setRefreshing(false);
        if(mNewsCategory.equals(getmNewsCategory)) {
            serverResponse = (ServerResponse) recievedMap.get("response");
            entryList = serverResponse.getResults();
            sectionAdapter.update(entryList);
        //    swipeRefreshLayout.setRefreshing(true);
            EventBus.getDefault().removeStickyEvent(recievedMap);
        }
    }

    @Override
    public void onRefresh() {
        sectionAdapter.clear();
        handler.postDelayed(() -> ApiService.getResults(getContext(), mNewsCategory), 500);
    }

 /*   private void completeAdapterRefresh() {
        sectionAdapter.clear();
        swipeRefreshLayout.setRefreshing(true);
        handler.postDelayed(() -> {
            sectionAdapter = new SectionAdapter(getContext(), entryList);
            myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            myRecyclerView.setAdapter(sectionAdapter);
            ApiService.getResults(getContext(), mNewsCategory);
        }, 2000);
    }*/

    @Override
    public void onClick(View v) {

    }
}

