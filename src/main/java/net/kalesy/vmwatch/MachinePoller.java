package net.kalesy.vmwatch;

import net.kalesy.vmwatch.entities.Machine;
import net.kalesy.vmwatch.entities.Metric;
import net.kalesy.vmwatch.entities.Services;
import net.kalesy.vmwatch.services.MachineService;
import net.kalesy.vmwatch.services.MetricService;
import net.kalesy.vmwatch.services.ServicesService;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.time.Instant;
@Component
public class MachinePoller {

    private final MachineService machineService;
    private final ServicesService servicesService;
    private final MetricService metricService;
    private final RestClient restClient;
    public MachinePoller(MachineService machineService, ServicesService servicesService, MetricService metricService) {
        this.machineService = machineService;
        this.servicesService = servicesService;
        this.metricService = metricService;

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3));
        factory.setReadTimeout(Duration.ofSeconds(3));
        this.restClient = RestClient.builder().requestFactory(factory).build();
    }
    @Async

    public void pollMachine(Machine m){
        String hostname = m.getHostname();
        int port = m.getPort();
        try{
            VmResponse res = restClient.get().uri("http://"+ hostname+":" + port+ "/metrics").retrieve().body(VmResponse.class);
            System.out.println(res);
            Metric metric = new Metric();
            Services services = new Services();
            metric.setCpu(res.getCpuUsage());
            metric.setMemory(res.getMemoryUsage());
            metric.setDisk(res.getDiskUsage());
            metric.setMachine(m);
            metric.setTimestamp(Instant.now());
            services.setMachine(m);
            services.setCpuUsage(res.getServiceCpuUsage());
            services.setMemoryUsage(res.getServiceMemoryUsage());
            System.out.println("Finished polling for " + hostname + ":" + port);
            machineService.updateLastFetchedTime(m,metric);
            servicesService.deleteServices(m);
            servicesService.saveServices(services);
            metricService.saveMetric(metric);
        }catch(Exception e){
            System.err.println("Error getting metrics for "+hostname+":"+port + e.toString());
        }
    }
}

