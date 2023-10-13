package com.example.spacexlaunchtrackerapp.launches.view;

import static com.example.spacexlaunchtrackerapp.database.LauncherDB.getBookmark;
import static com.example.spacexlaunchtrackerapp.database.LauncherDB.getFavourite;
import static com.example.spacexlaunchtrackerapp.utility.Utility.getDateFormated;
import static com.example.spacexlaunchtrackerapp.utility.Utility.loadUrlImage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.R;
import com.example.spacexlaunchtrackerapp.database.BookmarkDataEntity;
import com.example.spacexlaunchtrackerapp.database.FavouriteDataEntity;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.example.spacexlaunchtrackerapp.utility.BundleConstants;

import java.util.List;

public class LauncherDetailActivity extends BaseActivity {
    private BaseActivity mActivity;
    private ImageView launcherImageView;
    private ImageView favouriteImageView;
    private ImageView bookmarkImageView;
    private ImageView backImageView;
    private ImageView statusImageView;
    private TextView launchName;
    private TextView launchDate;
    private TextView rocketname;
    private TextView rocketType;
    private TextView rocketDescription;
    private TextView launchStatus;
    private TextView launchStatusText;
    private TextView launchSite;
    private TextView launchWindow;
    private TextView missionPatch;
    private TextView missionDescription;
    private TextView links;
    private Boolean isBookmarkCheck = false;
    private Boolean isFavouriteCheck = false;
    private LaunchersResponse launchersResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_detail);
        mActivity = this;

        launcherImageView = findViewById(R.id.launcher_detail_image_view);
        favouriteImageView = findViewById(R.id.launcher_detail_favourite_icon);
        bookmarkImageView = findViewById(R.id.launcher_detail_bookmark_icon);
        statusImageView = findViewById(R.id.launcher_detail_status_image);
        backImageView = findViewById(R.id.launcher_detail_back_icon);
        launchName = findViewById(R.id.launcher_detail_title_tv);
        launchDate = findViewById(R.id.launcher_detail_description_tv);
        rocketname = findViewById(R.id.launcher_detail_rocket_name_tv);
        rocketType = findViewById(R.id.launcher_detail_rocket_type_tv);
        rocketDescription = findViewById(R.id.launcher_detail_rocket_description);
        launchStatus = findViewById(R.id.launcher_detail_status_tv);
        launchStatusText = findViewById(R.id.launcher_detail_status_text);
        launchSite = findViewById(R.id.launcher_detail_launch_site_tv);
        launchWindow = findViewById(R.id.launcher_detail_launch_window_tv);
        missionPatch = findViewById(R.id.launcher_detail_mission_patch);
        missionDescription = findViewById(R.id.launcher_detail_mission_description_tv);
        links = findViewById(R.id.launcher_detail_links_tv);


        if (getIntent().hasExtra(BundleConstants.LAUNCHER_DATA_BUNDLE))
        {
            try {
                launchersResponse = (LaunchersResponse) getIntent().getExtras().getSerializable(BundleConstants.LAUNCHER_DATA_BUNDLE);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

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


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bookmarkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBookmarkCheck = !isBookmarkCheck;
                bookmarkImageView.setImageResource(isBookmarkCheck ? R.drawable.bookmark_selected : R.drawable.bookmark_unselected);
                bookmarkImageView.setColorFilter(ContextCompat.getColor(mActivity, R.color.white));
            }
        });

        favouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavouriteCheck = !isFavouriteCheck;
                favouriteImageView.setImageResource(isFavouriteCheck ? R.drawable.heart_selected : R.drawable.heart_unselected);
                favouriteImageView.setColorFilter(ContextCompat.getColor(mActivity,isFavouriteCheck ? R.color.failure : R.color.white));
            }
        });

        if (launchersResponse != null)
        {
            setLauncherDetail();
        }
    }


    private void setLauncherDetail()
    {
        launchName.setText(launchersResponse.mission_name);
        launchDate.setText(
                "Launch Date : "+getDateFormated(launchersResponse.launch_date_utc)+"\nRocket Name : " + launchersResponse.rocket.rocket_name
        );
        statusImageView.setColorFilter(ContextCompat.getColor(mActivity, launchersResponse.launch_success ? R.color.success : R.color.failure));
        launchStatusText.setText(mActivity.getString(launchersResponse.launch_success ? R.string.success : R.string.failure));

        rocketname.setText("Name: " + launchersResponse.rocket.rocket_name);
        rocketname.setText("Type: " + launchersResponse.rocket.rocket_type);
        rocketname.setText("Description: not available");

        String launchStatusString = mActivity.getString(launchersResponse.launch_success ? R.string.success : R.string.failure);
        launchStatusString +=   launchersResponse.launch_success ? "" : " due to "+launchersResponse.launch_failure_details.reason;
        launchStatus.setText("Status: " + launchStatusString);

        launchSite.setText("Launch Site: " + launchersResponse.rocket.rocket_name);
        launchWindow.setText("Launch Window: " + launchersResponse.rocket.rocket_name);
       /* missionPatch.setText(launchersResponse.mis);
        missionDescription.setText(launchersResponse.rocket.rocket_name);
        missionDescription.setText(launchersResponse.rocket.rocket_name);*/

        loadUrlImage(mActivity, launchersResponse.links.mission_patch, launcherImageView);

    }
}