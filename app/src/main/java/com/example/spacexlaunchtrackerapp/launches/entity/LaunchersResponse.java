package com.example.spacexlaunchtrackerapp.launches.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class LaunchersResponse implements Serializable {
    public int flight_number;
    public String mission_name;
    public ArrayList<String> mission_id;
    public boolean upcoming;
    public String launch_year;
    public int launch_date_unix;
    public String launch_date_utc;
    public String launch_date_local;
    public boolean is_tentative;
    public String tentative_max_precision;
    public boolean tbd;
    public int launch_window;
    public Rocket rocket;
    public ArrayList<String> ships;
    public Telemetry telemetry;
    public LaunchSite launch_site;
    public boolean launch_success;
    public LaunchFailureDetailsData launch_failure_details;
    public Links links;
    public String details;
    public Date static_fire_date_utc;
    public int static_fire_date_unix;
    public Timeline timeline;
    public ArrayList<Object> crew;
    public Date last_date_update;
    public Date last_ll_launch_date;
    public Date last_ll_update;
    public Date last_wiki_launch_date;
    public String last_wiki_revision;
    public Date last_wiki_update;
    public String launch_date_source;
}
