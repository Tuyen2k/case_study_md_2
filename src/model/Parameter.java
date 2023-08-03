package model;

import java.io.Serializable;

public class Parameter implements Serializable {
    private static final long serializable = 1234567;
    private int series;
    private String color;
    private String screenTechnology;
    private String cpu;

    private String ram;
    private String rom;
    private String battery;
    private String machineStatus;

    public static int INDEX = 0;

    public Parameter() {
    }

    public Parameter(String color, String screenTechnology, String cpu, String ram, String rom, String battery, String machineStatus) {
        this.series = ++INDEX;
        this.color = color;
        this.screenTechnology = screenTechnology;
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.battery = battery;
        this.machineStatus = machineStatus;
    }

    public int getSeries() {
        return series;
    }

    public void setId_type(int series) {
        this.series = series;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(String machineStatus) {
        this.machineStatus = machineStatus;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-10s %-15s %-20s %-10s %-10s %-10s %-10s",
                series, color, screenTechnology, cpu, ram+"GB", rom+"GB", battery +"mAh", machineStatus+"%");
    }
}
