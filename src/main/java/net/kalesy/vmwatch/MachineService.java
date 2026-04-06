package net.kalesy.vmwatch;

import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class MachineService {
    private final MachineRepository machineRepository;
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }
    public Machine findById (Long id) {
        return machineRepository.findById(id).orElse(null);
    }
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }
    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }
    public Machine disableMachine(Long id) {
        Machine tmp = findById(id);
        if (tmp == null) {
            throw new IllegalArgumentException("Machine with id " + id + " does not exist");
        }
        tmp.setEnabled(false);
        return machineRepository.save(tmp);
    }
    public Machine enableMachine(Long id) {
        Machine tmp = findById(id);
        if (tmp == null) {
            throw new IllegalArgumentException("Machine with id " + id + " does not exist");
        }
        tmp.setEnabled(true);
        return machineRepository.save(tmp);
    }
    public Machine updateLastFetchedTime(Machine machine, Metric metric) {
        Machine tmp = findById(machine.getId());
        tmp.setLastSuccessfulPoll(metric.getTimestamp());
        return machineRepository.save(tmp);
    }
}
