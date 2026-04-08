package net.kalesy.vmwatch.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostname;
    private String friendlyName;
    private int port;
    private boolean enabled;
    private Instant lastSuccessfulPoll;

    public void setId(Long id) {
        this.id = id;
    }
    public void setHostname(String hostname) { this.hostname = hostname; }
    public void setFriendlyName(String friendlyName) { this.friendlyName = friendlyName; }
    public void setPort(int port) { this.port = port; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setLastSuccessfulPoll(Instant lastSuccessfulPoll) { this.lastSuccessfulPoll = lastSuccessfulPoll; }

    public Long getId() {
        return id;
    }
    public String getHostname() { return hostname; }
    public String getFriendlyName() { return friendlyName; }
    public int getPort() { return port; }
    public boolean isEnabled() { return enabled; }
    public Instant getLastSuccessfulPoll() { return lastSuccessfulPoll; }

    public Machine() {}

}
