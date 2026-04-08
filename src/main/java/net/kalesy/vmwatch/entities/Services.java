package net.kalesy.vmwatch.entities;

import jakarta.persistence.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @Column(columnDefinition = "TEXT")
    private String memoryUsage;
    @Column(columnDefinition = "TEXT")
    private String cpuUsage;

    @Transient
    ObjectMapper mapper = new ObjectMapper();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public HashMap<String, Double> getMemoryUsage() {
        try{
            return mapper.readValue(memoryUsage, new TypeReference<HashMap<String, Double>>(){});
        }catch (Exception e){
            return null;
        }
    }

    public void setMemoryUsage(HashMap<String, Double> memoryUsage) {
        try{
            this.memoryUsage = mapper.writeValueAsString(memoryUsage);
        }catch (Exception e){
            this.memoryUsage = null;
        }
    }

    public HashMap<String,Double> getCpuUsage() {
        try{
            return mapper.readValue(cpuUsage, new TypeReference<HashMap<String, Double>>() {});
        }catch (Exception e){
            return null;
        }
    }

    public void setCpuUsage(HashMap<String,Double> cpuUsage) {
        try{
            this.cpuUsage = mapper.writeValueAsString(cpuUsage);
        }catch(Exception e){
            this.cpuUsage = null;
        }
    }
}
