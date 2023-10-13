package com.example.spacexlaunchtrackerapp.launches.entity;

import java.io.Serializable;

public class Rocket implements Serializable {
    public String rocket_id;
    public String rocket_name;
    public String rocket_type;
    public FirstStage first_stage;
    public SecondStage second_stage;
    public Fairings fairings;
}
