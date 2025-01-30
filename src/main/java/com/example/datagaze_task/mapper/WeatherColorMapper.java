package com.example.datagaze_task.mapper;

import org.springframework.stereotype.Component;

@Component
public class WeatherColorMapper {
    public static String getTempColor(double temp) {
        if (temp <= -30) return "#003366";
        if (temp <= -20) return "#4A90E2";
        if (temp <= -10) return "#B3DFFD";
        if (temp <= 0) return "#E6F7FF";
        if (temp <= 10) return "#D1F2D3";
        if (temp <= 20) return "#FFFCAD";
        if (temp <= 30) return "#FFCC80";
        if (temp <= 40) return "#FF7043";
        return "#D32F2F";
    }
    public static String getWindColor(double speed) {
        if (speed <= 10) return "#E0F7FA";
        if (speed <= 20) return "#B2EBF2";
        if (speed <= 40) return "#4DD0E1";
        if (speed <= 60) return "#0288D1";
        return "#01579B";
    }

    public static String getCloudColor(int cloud) {
        if (cloud <= 10) return "#FFF9C4";
        if (cloud <= 30) return "#FFF176";
        if (cloud <= 60) return "#E0E0E0";
        if (cloud <= 90) return "#9E9E9E";
        return "#616161";
    }
}
