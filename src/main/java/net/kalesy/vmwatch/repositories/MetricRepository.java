package net.kalesy.vmwatch.repositories;

import net.kalesy.vmwatch.entities.Machine;
import net.kalesy.vmwatch.entities.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    public List<Metric> findByMachine(Machine machine);
}
