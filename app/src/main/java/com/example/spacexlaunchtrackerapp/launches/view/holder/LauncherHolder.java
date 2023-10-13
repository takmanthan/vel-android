package com.example.spacexlaunchtrackerapp.launches.view.holder;

import static com.example.spacexlaunchtrackerapp.database.LauncherDB.getBookmark;
import static com.example.spacexlaunchtrackerapp.database.LauncherDB.getFavourite;
import static com.example.spacexlaunchtrackerapp.utility.Utility.getDateFormated;
import static com.example.spacexlaunchtrackerapp.utility.Utility.loadUrlImage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.R;
import com.example.spacexlaunchtrackerapp.database.BookmarkDataEntity;
import com.example.spacexlaunchtrackerapp.database.LauncherDB;
import com.example.spacexlaunchtrackerapp.database.FavouriteDataEntity;
import com.example.spacexlaunchtrackerapp.launches.contractor.LauncherContractor;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;

import java.util.List;

public class LauncherHolder extends RecyclerView.ViewHolder {

    private ImageView launcherImageView;
    private ImageView favouriteImageView;
    private ImageView bookmarkImageView;
    private ImageView statusImageView;
    private TextView statusTextView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private RelativeLayout root_container;
    private BaseActivity mActivity;

    private Boolean isBookmarkCheck = false;
    private Boolean isFavouriteCheck = false;
    private LauncherContractor.Presenter presenter;


    public LauncherHolder(@NonNull View itemView, BaseActivity mActivity, LauncherContractor.Presenter presenter) {
        super(itemView);
        this.mActivity = mActivity;
        this.presenter = presenter;
        launcherImageView = itemView.findViewById(R.id.item_launcher_image_view);
        favouriteImageView = itemView.findViewById(R.id.item_launcher_favourite_icon);
        bookmarkImageView = itemView.findViewById(R.id.item_launcher_bookmark_icon);
        statusImageView = itemView.findViewById(R.id.item_launcher_status_image);
        statusTextView = itemView.findViewById(R.id.item_launcher_status_text);
        titleTextView = itemView.findViewById(R.id.item_launcher_title_tv);
        descriptionTextView = itemView.findViewById(R.id.item_launcher_description_tv);
        root_container = itemView.findViewById(R.id.item_launcher_root_container);
    }


    public void bind(LaunchersResponse launchersResponse)
    {
        try {
            LiveData<List<FavouriteDataEntity>> liveData = getFavourite(mActivity, launchersResponse);
            liveData.observe(mActivity, new Observer<List<FavouriteDataEntity>>() {
                @Override
                public void onChanged(List<FavouriteDataEntity> launcherDataEntities) {
                    isFavouriteCheck = launcherDataEntities.size() > 0;
                    favouriteImageView.setImageResource(isFavouriteCheck ? R.drawable.heart_selected : R.drawable.heart_unselected);
                    favouriteImageView.setColorFilter(ContextCompat.getColor(mActivity,isFavouriteCheck ? R.color.failure : R.color.white));
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }


        try {
            LiveData<List<BookmarkDataEntity>> liveData = getBookmark(mActivity, launchersResponse);
            liveData.observe(mActivity, new Observer<List<BookmarkDataEntity>>() {
                @Override
                public void onChanged(List<BookmarkDataEntity> launcherDataEntities) {
                    isBookmarkCheck = launcherDataEntities.size() > 0;
                    bookmarkImageView.setImageResource(isBookmarkCheck ? R.drawable.bookmark_selected : R.drawable.bookmark_unselected);
                    bookmarkImageView.setColorFilter(ContextCompat.getColor(mActivity, R.color.white));
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        titleTextView.setText(launchersResponse.mission_name);
        descriptionTextView.setText(
                "Launch Date : "+getDateFormated(launchersResponse.launch_date_utc)+"\nRocket Name : " + launchersResponse.rocket.rocket_name
        );
        statusImageView.setColorFilter(ContextCompat.getColor(mActivity, launchersResponse.launch_success ? R.color.success : R.color.failure));
        statusTextView.setText(mActivity.getString(launchersResponse.launch_success ? R.string.success : R.string.failure));

        loadUrlImage(mActivity, launchersResponse.links.mission_patch, launcherImageView);

        bookmarkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBookmarkCheck = !isBookmarkCheck;
                bookmarkImageView.setImageResource(isBookmarkCheck ? R.drawable.bookmark_selected : R.drawable.bookmark_unselected);
                bookmarkImageView.setColorFilter(ContextCompat.getColor(mActivity, R.color.white));

                if (isBookmarkCheck) {
                    LauncherDB.insertBookmark(mActivity, launchersResponse);
                } else {
                    LauncherDB.deleteBookmark(mActivity, launchersResponse);
                }
            }
        });

        favouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavouriteCheck = !isFavouriteCheck;
                favouriteImageView.setImageResource(isFavouriteCheck ? R.drawable.heart_selected : R.drawable.heart_unselected);
                favouriteImageView.setColorFilter(ContextCompat.getColor(mActivity,isFavouriteCheck ? R.color.failure : R.color.white));
                if (isFavouriteCheck) {
                    LauncherDB.insertFavourite(mActivity, launchersResponse);
                } else {
                    LauncherDB.deleteFavourite(mActivity, launchersResponse);
                }
            }
        });

        root_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLauncherClick(launchersResponse);
            }
        });
    }

}
