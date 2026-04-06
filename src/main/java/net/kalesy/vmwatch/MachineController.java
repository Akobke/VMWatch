package net.kalesy.vmwatch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MachineController {
    private final MachineService machineService;
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @PostMapping("/vm/machine")
    public ResponseEntity<Machine> saveMachine(@RequestBody Machine machine) {
        Machine res =  machineService.save(machine);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }
    @PostMapping("/vm/enable/{id}")
    public ResponseEntity<Machine> enableMachine(@PathVariable Long id) {
        Machine res = machineService.enableMachine(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping("/vm/disable/{id}")
    public ResponseEntity<Machine> disableMachine(@PathVariable Long id) {
        Machine res = machineService.disableMachine(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @GetMapping("/vm/machine/{id}")
    public ResponseEntity<Machine> getMachine(@PathVariable Long id) {
        Machine res = machineService.findById(id);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
    @GetMapping("/vm/machine")
    public ResponseEntity<List<Machine>> getAllMachines() {
        List<Machine> res = machineService.findAll();
        return ResponseEntity.ok(res);
    }
}
