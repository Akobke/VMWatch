package net.kalesy.vmwatch;

import java.util.HashMap;
import java.util.List;

public class VmResponse {
    private Double cpuUsage;
    private Double memoryUsage;
    private HashMap<String,Double> diskUsage;

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public HashMap<String,Double> getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(HashMap<String, Double> diskUsage) {
        this.diskUsage = diskUsage;
    }
}
