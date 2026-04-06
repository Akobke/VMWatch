package net.kalesy.vmwatch;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Metric {
    public  Metric() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    private Instant timestamp;
    private double cpu;
    private double memory;
    private double disk;
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
    public Machine getMachine() { return machine; }

    public void setMachine(Machine machineId) { this.machine = machineId; }

    public Instant getTimestamp() { return timestamp; }

    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public double getCpu() { return cpu; }

    public void setCpu(double cpu) { this.cpu = cpu; }

    public double getMemory() { return memory; }

    public void setMemory(double memory) { this.memory = memory; }

    public double getDisk() { return disk; }

    public void setDisk(double disk) { this.disk = disk; }
}
