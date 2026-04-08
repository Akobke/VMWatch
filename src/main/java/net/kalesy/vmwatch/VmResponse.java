package net.kalesy.vmwatch;

import net.kalesy.vmwatch.entities.Services;

import java.util.HashMap;
import java.util.List;

public class VmResponse {
    private Double cpuUsage;
    private Double memoryUsage;
    private HashMap<String,Double> diskUsage;

    private HashMap<String,Double> serviceCpuUsage;
    private HashMap<String,Double> serviceMemoryUsage;

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

    public HashMap<String, Double> getServiceCpuUsage() { return serviceCpuUsage; }

    public void setServiceCpuUsage(HashMap<String, Double> serviceCpuUsage) { this.serviceCpuUsage = serviceCpuUsage; }

    public HashMap<String, Double> getServiceMemoryUsage() {
        return serviceMemoryUsage;
    }

    public void setServiceMemoryUsage(HashMap<String, Double> serviceMemoryUsage) {
        this.serviceMemoryUsage = serviceMemoryUsage;
    }
}
