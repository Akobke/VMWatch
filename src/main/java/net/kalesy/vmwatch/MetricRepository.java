package net.kalesy.vmwatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    public List<Metric> findByMachine(Machine machine);
}
