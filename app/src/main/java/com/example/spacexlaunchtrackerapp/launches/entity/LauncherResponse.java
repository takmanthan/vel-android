package com.example.spacexlaunchtrackerapp.launches.entity;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */
class Core implements Serializable {
    public String core_serial;
    public int flight;
    public int block;
    public boolean gridfins;
    public boolean legs;
    public boolean reused;
    public boolean land_success;
    public boolean landing_intent;
    public String landing_type;
    public String landing_vehicle;
}

class Fairings implements Serializable {
    public boolean reused;
    public boolean recovery_attempt;
    public boolean recovered;
    public String ship;
}

class FirstStage implements Serializable {
    public ArrayList<Core> cores;
}

class LaunchSite implements Serializable {
    public String site_id;
    public String site_name;
    public String site_name_long;
}

class OrbitParams implements Serializable {
    public String reference_system;
    public String regime;
    public double longitude;
    public double semi_major_axis_km;
    public double eccentricity;
    public double periapsis_km;
    public double apoapsis_km;
    public double inclination_deg;
    public double period_min;
    public double lifespan_years;
    public Date epoch;
    public double mean_motion;
    public double raan;
    public double arg_of_pericenter;
    public double mean_anomaly;
}

class Payload implements Serializable {
    public String payload_id;
    public ArrayList<Integer> norad_id;
    public boolean reused;
    public ArrayList<String> customers;
    public String nationality;
    public String manufacturer;
    public String payload_type;
    public double payload_mass_kg;
    public double payload_mass_lbs;
    public String orbit;
    public OrbitParams orbit_params;
    public String cap_serial;
    public double mass_returned_kg;
    public double mass_returned_lbs;
    public int flight_time_sec;
    public String cargo_manifest;
    public String uid;
}

class SecondStage implements Serializable {
    public int block;
    public ArrayList<Payload> payloads;
}

class Telemetry implements Serializable {
    public String flight_club;
}

class Timeline implements Serializable {
    public int webcast_liftoff;
    public int go_for_prop_loading;
    public int rp1_loading;
    public int stage1_lox_loading;
    public int stage2_lox_loading;
    public int engine_chill;
    public int prelaunch_checks;
    public int propellant_pressurization;
    public int go_for_launch;
    public int ignition;
    public int liftoff;
    public int maxq;
    public int meco;
    public int stage_sep;
    public int second_stage_ignition;
    public int dragon_separation;
    public int dragon_solar_deploy;
    public int dragon_bay_door_deploy;
    public int fairing_deploy;
    public int payload_deploy;
    public int second_stage_restart;
    public int webcast_launch;
    public int payload_deploy_1;
    public int payload_deploy_2;
    public int first_stage_boostback_burn;
    public int first_stage_entry_burn;
    public int first_stage_landing;
    public int beco;
    public int side_core_sep;
    public int side_core_boostback;
    public int center_stage_sep;
    public int center_core_boostback;
    public int side_core_entry_burn;
    public int center_core_entry_burn;
    public int side_core_landing;
    public int center_core_landing;
    public int first_stage_landing_burn;
    public int stage1_rp1_loading;
    public int stage2_rp1_loading;
}

