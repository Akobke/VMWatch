package net.kalesy.vmwatch;


import net.kalesy.vmwatch.entities.Machine;
import net.kalesy.vmwatch.services.MachineService;
import net.kalesy.vmwatch.services.MetricService;
import net.kalesy.vmwatch.services.ServicesService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VmPollingService {

    private final MachineService machineService;
    private final MachinePoller machinePoller;
    public VmPollingService(MachineService machineService, MetricService metricService, ServicesService serviceService, MachinePoller machinePoller) {
        this.machineService = machineService;
        this.machinePoller = machinePoller;
    }

    @Scheduled(fixedRate = 10000)
    public void pollAll(){
        List<Machine> machines = machineService.findAll();
        for (Machine m : machines) {
            if(m.isEnabled()){
                machinePoller.pollMachine(m);
            }
        }
    }

}
