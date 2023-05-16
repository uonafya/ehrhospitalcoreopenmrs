package org.openmrs.module.hospitalcore.model;

public class EhrAppointmentDailyCount {
    private String date;

    private int dailyCount;

    public EhrAppointmentDailyCount() {
    }

    public EhrAppointmentDailyCount(String date, int dailyCount) {
        setDate(date);
        setDailyCount(dailyCount);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDailyCount() {
        return dailyCount;
    }

    public void setDailyCount(int dailyCount) {
        this.dailyCount = dailyCount;
    }
}
