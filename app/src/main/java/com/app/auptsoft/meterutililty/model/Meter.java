package com.app.auptsoft.meterutililty.model;

public class Meter {
    private String meterName;
    private String meterNumber;
    private String designation;
    private String capacity;
    private String address;

    public Meter() {
        this("", "", "", "", "");
    }

    public Meter(String meterName, String meterNumber, String designation, String capacity, String address) {
        this.meterName = meterName;
        this.meterNumber = meterNumber;
        this.designation = designation;
        this.capacity = capacity;
        this.address = address;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
