package com.example.spacexlaunchtrackerapp.launches.view.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.R;
import com.example.spacexlaunchtrackerapp.launches.contractor.LauncherContractor;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.example.spacexlaunchtrackerapp.launches.view.holder.LauncherHolder;

import java.util.ArrayList;

public class LauncherAdaptor extends RecyclerView.Adapter<LauncherHolder> {

    private LauncherContractor.Presenter presenter;
    private BaseActivity mActivity;

    public LauncherAdaptor(BaseActivity mActivity, LauncherContractor.Presenter presenter) {
        this.presenter = presenter;
        this.mActivity = mActivity;
    }

    private ArrayList<LaunchersResponse> launchersList = new ArrayList<LaunchersResponse>();

    public void setLauncherListing(ArrayList<LaunchersResponse> launchersList)
    {
        this.launchersList = launchersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LauncherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LauncherHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_launcher, null),
                mActivity,
                presenter
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LauncherHolder holder, int position) {
        holder.bind(launchersList.get(position));
    }

    @Override
    public int getItemCount() {
        return launchersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
