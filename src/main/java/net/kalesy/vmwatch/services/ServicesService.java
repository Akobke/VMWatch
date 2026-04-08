package net.kalesy.vmwatch.services;

import jakarta.transaction.Transactional;
import net.kalesy.vmwatch.entities.Machine;
import net.kalesy.vmwatch.entities.Services;
import net.kalesy.vmwatch.repositories.ServicesRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
    private final ServicesRepository servicesRepository;
    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }
    public Services saveServices(Services services) {
        return servicesRepository.save(services);
    }
    @Transactional
    public void deleteServices(Machine machine) {
        servicesRepository.deleteByMachine(machine);
    }

}
