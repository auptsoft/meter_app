package com.app.auptsoft.meterutililty.model;

public class MeterStatus {
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

    public MeterStatus() {
        this(0, "", "", "", "", "", "", "", "", "", "", "");
    }

    public MeterStatus(int id, String meterDesignation, String availableAmount, String meterState, String reason, String powerConsumption, String redPhaseActive, String yellowPhaseActive, String bluePhaseActive, String fraudDetected, String currentPhase, String capacity) {
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

    public static MeterStatus getSendable(String sendableString) {
        MeterStatus meterStatus = new MeterStatus();
        meterStatus.meterDesignation = sendableString.substring(0, 4);
        meterStatus.availableAmount = sendableString.substring(5, 9);
        meterStatus.meterState = sendableString.substring(9, 10);
        meterStatus.reason = sendableString.substring(10, 11);
        meterStatus.powerConsumption = sendableString.substring(12, 16);
        meterStatus.redPhaseActive = sendableString.substring(16, 17);
        meterStatus.bluePhaseActive = sendableString.substring(17, 18);
        meterStatus.yellowPhaseActive = sendableString.substring(18, 19);
        meterStatus.fraudDetected = sendableString.substring(19, 20);
        meterStatus.currentPhase = sendableString.substring(20, 21);
        meterStatus.capacity = sendableString.substring(22, 26);

        return meterStatus;
    }

    public static MeterStatus getSendableFromSeperatedData(String data) {
        String[] dataArray = data.split(">");
        try {
            MeterStatus meterStatus = new MeterStatus();

            meterStatus.setMeterDesignation(dataArray[0]);
            meterStatus.setAvailableAmount(dataArray[1]);
            meterStatus.setWorth(dataArray[2]);
            meterStatus.setPowerConsumption(dataArray[3]);
            meterStatus.setRedPhaseActive(dataArray[4]);
            meterStatus.setYellowPhaseActive(dataArray[5]);
            meterStatus.setBluePhaseActive(dataArray[6]);
            meterStatus.setFraudDetected(dataArray[7]);
            meterStatus.setIsShutdown(dataArray[8]);
            meterStatus.setShutdownReason(dataArray[9]);
            meterStatus.setCurrentPhase(dataArray[10]);

            return meterStatus;

        } catch (Exception e) {
            return new MeterStatus();
        }
    }

    public static String getSendableString(MeterStatus meterModel) {
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
