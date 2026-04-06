package net.kalesy.vmwatch;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MetricService {
    private final MetricRepository metricRepository;

    public MetricService(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }
    public Metric saveMetric(Metric metric) {
        return metricRepository.save(metric);
    }
    public Metric getMetricById(Long id) {
        Metric tmp = metricRepository.findById(id).orElse(null);
        if (tmp == null) {
            throw new IllegalArgumentException("No metrics with ID " + id);
        }
        return tmp;
    }
    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }
    public List<Metric> getMetricsByMachine(Machine machine) {
        List<Metric> tmp = metricRepository.findByMachine(machine);
        if (tmp.isEmpty()) {
            throw new IllegalArgumentException("No metrics found for machine " + machine);
        }
        return tmp;
    }

}
