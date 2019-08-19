package com.app.auptsoft.meterutililty.model;

public class NMeterStatus {
    private String meterNumber;
    private double voltage;
    private double current;
    private double power;
    private double energy;
    private double frequency;
    private double availableUnit;
    private int redPhaseStatus;
    private int yellowPhaseStatus;
    private int bluePhaseStatus;
    private int faultStatus;
    private int selectedPhase;

    public static NMeterStatus fromFormatedString(String inString) {
        String[] entries = inString.split(">");

        try {
            String meterNumber = entries[1];
            double voltage = Double.parseDouble(entries[2]) / 10;
            double current = Double.parseDouble(entries[3]) / 1000;
            double power = Double.parseDouble(entries[4]) / 10;
            double energy = Double.parseDouble(entries[5]) / 1000;
            double frequency = Double.parseDouble(entries[6]) / 10;
            double availableUnit = Double.parseDouble(entries[7]) /100;
            int redPhaseStatus = Integer.parseInt(entries[8]);
            int yellowPhaseStatus = Integer.parseInt(entries[9]);
            int bluePhaseStatus = Integer.parseInt(entries[10]);
            int faultStatus = Integer.parseInt(entries[11]);
            int seletedPhase = Integer.parseInt(entries[12]);

            NMeterStatus nMeterStatus = new NMeterStatus(
                    meterNumber,
                    voltage,
                    current,
                    power,
                    energy,
                    frequency,
                    availableUnit,
                    redPhaseStatus,
                    yellowPhaseStatus,
                    bluePhaseStatus,
                    faultStatus,
                    seletedPhase
            );
            return nMeterStatus;
        } catch (Exception e) {
            return null;
        }
    }

    public NMeterStatus(String meterNumber, double voltage, double current, double power, double energy, double frequency, double availableUnit, int redPhaseStatus, int yellowPhaseStatus, int bluePhaseStatus, int faultStatus, int selectedPhase) {
        this.meterNumber = meterNumber;
        this.voltage = voltage;
        this.current = current;
        this.power = power;
        this.energy = energy;
        this.frequency = frequency;
        this.availableUnit = availableUnit;
        this.redPhaseStatus = redPhaseStatus;
        this.yellowPhaseStatus = yellowPhaseStatus;
        this.bluePhaseStatus = bluePhaseStatus;
        this.faultStatus = faultStatus;
        this.selectedPhase = selectedPhase;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getEnergy() {
        return energy;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getAvailableUnit() {
        return availableUnit;
    }

    public void setAvailableUnit(double availableUnit) {
        this.availableUnit = availableUnit;
    }

    public int getRedPhaseStatus() {
        return redPhaseStatus;
    }

    public void setRedPhaseStatus(int redPhaseStatus) {
        this.redPhaseStatus = redPhaseStatus;
    }

    public int getYellowPhaseStatus() {
        return yellowPhaseStatus;
    }

    public void setYellowPhaseStatus(int yellowPhaseStatus) {
        this.yellowPhaseStatus = yellowPhaseStatus;
    }

    public int getBluePhaseStatus() {
        return bluePhaseStatus;
    }

    public void setBluePhaseStatus(int bluePhaseStatus) {
        this.bluePhaseStatus = bluePhaseStatus;
    }

    public int getFaultStatus() {
        return faultStatus;
    }

    public void setFaultStatus(int faultStatus) {
        this.faultStatus = faultStatus;
    }

    public int getSelectedPhase() {
        return selectedPhase;
    }

    public void setSelectedPhase(int selectedPhase) {
        this.selectedPhase = selectedPhase;
    }
}
