package com.example.spacexlaunchtrackerapp.launches.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.R;
import com.example.spacexlaunchtrackerapp.launches.contractor.LauncherContractor;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.example.spacexlaunchtrackerapp.launches.presenter.LauncherPresenter;
import com.example.spacexlaunchtrackerapp.launches.view.adaptor.LauncherAdaptor;

import java.util.ArrayList;

public class LaunchersActivity extends BaseActivity implements LauncherContractor.View {

    private RecyclerView mRecyclerView;
    private LauncherContractor.Presenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshContainer;
    private LauncherAdaptor mLauncherAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchers);

        mPresenter = new LauncherPresenter(this, this);
        mLauncherAdaptor = new LauncherAdaptor(this,mPresenter);

        mRecyclerView = findViewById(R.id.launchers_recycleview);
        mSwipeRefreshContainer = findViewById(R.id.launcher_swipe_refresh_container);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(mLauncherAdaptor);

        mSwipeRefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getAllLauncher();
            }
        });

        mPresenter.getAllLauncher();
    }

    @Override
    public void setLaunchers(ArrayList<LaunchersResponse> launchersResponses) {
        mLauncherAdaptor.setLauncherListing(launchersResponses);
    }

    @Override
    public void showLoader() {
        mSwipeRefreshContainer.setRefreshing(true);
    }

    @Override
    public void hideLoader() {
        mSwipeRefreshContainer.setRefreshing(false);
    }
}