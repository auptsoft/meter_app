package com.app.auptsoft.meterutililty.model;

/**
 * Created by Andrew on 23.3.19.
 */

public class MeterModel {
    private int id;

    private String meterDesignation;
    private String availableAmount;
    private String worth;
    /**
     * can be "0" or "1".
     * "0" means ON
     * "1" means OFF
     */
    private String meterState;

    /**
     * value can be "0", "1", "2", or "3", 0 means meter is ok.
     * 1 means overload
     * 2 means fraud
     * 3 unit exhusted
     */
    private String reason;

    private String powerConsumption;
    private String redPhaseActive;
    private String yellowPhaseActive;
    private String bluePhaseActive;

    private String fraudDetected;
    private String currentPhase;
    private String capacity;
    private String isShutdown;
    private String shutdownReason;

    public MeterModel() {
        this(0, "", "", "", "", "", "", "", "", "", "", "");
    }

    public MeterModel(int id, String meterDesignation, String availableAmount, String meterState, String reason, String powerConsumption, String redPhaseActive, String yellowPhaseActive, String bluePhaseActive, String fraudDetected, String currentPhase, String capacity) {
        this.id = id;
        this.meterDesignation = meterDesignation;
        this.availableAmount = availableAmount;
        this.meterState = meterState;
        this.reason = reason;
        this.powerConsumption = powerConsumption;
        this.redPhaseActive = redPhaseActive;
        this.yellowPhaseActive = yellowPhaseActive;
        this.bluePhaseActive = bluePhaseActive;
        this.fraudDetected = fraudDetected;
        this.currentPhase = currentPhase;
        this.capacity = capacity;
    }

    public static MeterModel getSendable(String sendableString) {
        MeterModel meterModel = new MeterModel();
        meterModel.meterDesignation = sendableString.substring(0, 4);
        meterModel.availableAmount = sendableString.substring(5, 9);
        meterModel.meterState = sendableString.substring(9, 10);
        meterModel.reason = sendableString.substring(10, 11);
        meterModel.powerConsumption = sendableString.substring(12, 16);
        meterModel.redPhaseActive = sendableString.substring(16, 17);
        meterModel.bluePhaseActive = sendableString.substring(17, 18);
        meterModel.yellowPhaseActive = sendableString.substring(18, 19);
        meterModel.fraudDetected = sendableString.substring(19, 20);
        meterModel.currentPhase = sendableString.substring(20, 21);
        meterModel.capacity = sendableString.substring(22, 26);

        return meterModel;
    }

    public static MeterModel getSendableFromSeperatedData(String data) {
        String[] dataArray = data.split(">");
        try {
            MeterModel meterModel = new MeterModel();

            meterModel.setMeterDesignation(dataArray[0]);
            meterModel.setAvailableAmount(dataArray[1]);
            meterModel.setWorth(dataArray[2]);
            meterModel.setPowerConsumption(dataArray[3]);
            meterModel.setRedPhaseActive(dataArray[4]);
            meterModel.setYellowPhaseActive(dataArray[5]);
            meterModel.setBluePhaseActive(dataArray[6]);
            meterModel.setFraudDetected(dataArray[7]);
            meterModel.setIsShutdown(dataArray[8]);
            meterModel.setShutdownReason(dataArray[9]);
            meterModel.setCurrentPhase(dataArray[10]);

            return meterModel;

        } catch (Exception e) {
            return new MeterModel();
        }
    }

    public static String getSendableString(MeterModel meterModel) {
        return "";
    }

    public static String appendZeros(String value, int noOfDigits){
        int numberOfZeros = noOfDigits-value.length();
        String output = value;
        for (int i =0; i<numberOfZeros; i++) {
            value = "0"+value;
        }
        return output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeterDesignation() {
        return meterDesignation;
    }

    public void setMeterDesignation(String meterDesignation) {
        this.meterDesignation = meterDesignation;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getMeterState() {
        return meterState.equals("1")? "ON" : "OFF";
    }

    public void setMeterState(String meterState) {
        this.meterState = meterState;
    }

    public String getReason() {
        if (reason.equals("1")) {
            return "You meter is shutdown due to overload. Contact base station for help";
        } else if (reason.equals("2")) {
            return "You meter is shutdown due to suspicious action on the meter. Contact base station for help";
        } else if (reason.equals("3")) {
            return "You meter is shutdown due to insufficient unit. Recharge your meter";
        } else {
            return "";
        }
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPowerConsumption() {
        return powerConsumption+"W";
    }

    public void setPowerConsumption(String powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String getRedPhaseActive() {
        return redPhaseActive.equals("1")?"ON":"OFF";
    }

    public void setRedPhaseActive(String redPhaseActive) {
        this.redPhaseActive = redPhaseActive;
    }

    public String getYellowPhaseActive() {
        return yellowPhaseActive.equals("1")?"ON":"OFF";
    }

    public void setYellowPhaseActive(String yellowPhaseActive) {
        this.yellowPhaseActive = yellowPhaseActive;
    }

    public String getBluePhaseActive() {
        return bluePhaseActive.equals("1")?"ON":"OFF";
    }

    public void setBluePhaseActive(String bluePhaseActive) {
        this.bluePhaseActive = bluePhaseActive;
    }

    public String getFraudDetected() {
        return fraudDetected;
    }

    public void setFraudDetected(String fraudDetected) {
        this.fraudDetected = fraudDetected;
    }

    public String getCurrentPhase() {
        if(currentPhase.equals("1")) {
            return "RED";
        } else if (currentPhase.equals("2")) {
            return "BLUE";
        } else if (currentPhase.equals("3")) {
            return "RED";
        } else return "UNKNOWN";
    }

    public void setCurrentPhase(String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public String getCapacity() {
        return capacity+"W";
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getWorth() {
        return worth;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getIsShutdown() {
        return isShutdown;
    }

    public void setIsShutdown(String isShutdown) {
        this.isShutdown = isShutdown;
    }

    public String getShutdownReason() {
        return shutdownReason;
    }

    public void setShutdownReason(String shutdownReason) {
        this.shutdownReason = shutdownReason;
    }
}
