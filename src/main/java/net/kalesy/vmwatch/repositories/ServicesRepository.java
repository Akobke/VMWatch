package net.kalesy.vmwatch.repositories;

import net.kalesy.vmwatch.entities.Machine;
import net.kalesy.vmwatch.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    public void deleteByMachine(Machine machine);
}
