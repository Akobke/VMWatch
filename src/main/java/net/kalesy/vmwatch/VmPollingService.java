package net.kalesy.vmwatch;


import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class VmPollingService {

    private final MachineService machineService;
    private final MetricService metricService;
    private final RestClient restClient;
    public VmPollingService(MachineService machineService, MetricService metricService) {
        this.machineService = machineService;
        this.metricService = metricService;

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(3));
        this.restClient = RestClient.builder().requestFactory(factory).build();

    }
    public Metric pollMachine(Machine m){
        String hostname = m.getHostname();
        int port = m.getPort();
        try{
            VmResponse res = restClient.get().uri("http://"+ hostname+":" + port+ "/metrics").retrieve().body(VmResponse.class);
            System.out.println(res);
            Metric metric = new Metric();
            metric.setCpu(res.getCpuUsage());
            metric.setMemory(res.getMemoryUsage());
            metric.setDisk(res.getDiskUsage());
            metric.setMachine(m);
            metric.setTimestamp(Instant.now());
            System.out.println("Finished polling for " + hostname + ":" + port);
            machineService.updateLastFetchedTime(m,metric);
            return metricService.saveMetric(metric);
        }catch(Exception e){
            System.err.println("Error getting metrics for "+hostname+":"+port);
            return null;
        }

    }
    @Scheduled(fixedRate = 10000)
    public void pollAll(){
        List<Machine> machines = machineService.findAll();
        for (Machine m : machines) {
            if(m.isEnabled()){
                pollMachine(m);
            }
        }
    }

}
