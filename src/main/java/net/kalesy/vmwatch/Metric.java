package net.kalesy.vmwatch;

import jakarta.persistence.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@Entity
public class Metric {
    public  Metric() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    ObjectMapper mapper = new ObjectMapper();
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    private Instant timestamp;
    private double cpu;
    private double memory;
    @Column(columnDefinition = "TEXT")
    private String disk;
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

    public HashMap<String, Double> getDisk() {
        try{
            return mapper.readValue(disk, new TypeReference<HashMap<String, Double>>(){});
        }catch (Exception e){
            return null;
        }
    }

    public void setDisk(HashMap<String, Double> disk) {
        try{
            this.disk = mapper.writeValueAsString(disk);
        }catch (Exception e){
            this.disk = null;
        }
    }
}
