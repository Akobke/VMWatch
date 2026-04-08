package net.kalesy.vmwatch.controllers;

import net.kalesy.vmwatch.entities.Machine;
import net.kalesy.vmwatch.services.MachineService;
import net.kalesy.vmwatch.services.MetricService;
import net.kalesy.vmwatch.entities.Metric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MetricController {
    private final MetricService metricService;
    private final MachineService machineService;
    public MetricController(MetricService metricService, MachineService machineService) {
        this.metricService = metricService;
        this.machineService = machineService;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @GetMapping("/metric/{id}")
    public ResponseEntity<Metric> metric(@PathVariable Long id) {
        Metric res = metricService.getMetricById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @GetMapping("/metric/vm/{id}")
    public ResponseEntity<List<Metric>> metrics(@PathVariable Long id) {
        Machine machine = machineService.findById(id);
        if (machine == null) {
            return ResponseEntity.notFound().build();
        }
        List<Metric> res = metricService.getMetricsByMachine(machine);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping("/metric")
    public ResponseEntity<Metric> saveMetric(@RequestBody Metric metric) {
        Metric res = metricService.saveMetric(metric);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @GetMapping("/metric")
    public ResponseEntity<List<Metric>> getAllMetrics() {
        List<Metric> res = metricService.getAllMetrics();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
