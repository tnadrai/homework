/*
 * Copyright © 2020 Ericsson. A written permission from Ericsson is required to use this software.
 */

package com.haris.controller;

import com.haris.service.dto.MachineDto;
import com.haris.service.api.MachineService;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/machines")
    public Set<MachineDto> getAllMachines() {
        return machineService.getAllMachines();
    }

    @GetMapping(path = "/machine/{id}")
    public ResponseEntity<MachineDto> getMachine(@PathVariable Long id) {
        Optional<MachineDto> machine = machineService.getMachineById(id);
        return machine.isPresent() ? new ResponseEntity(machine.get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/machine/{id}")
    public ResponseEntity<MachineDto> updateMachine(@PathVariable Long id, @RequestParam String name) {
        Optional<MachineDto> machine = machineService.updateMachineNameById(id, name);
        return machine.isPresent() ? new ResponseEntity(machine.get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/machine")
    public ResponseEntity<MachineDto> createMachine(@RequestParam String name) {
        Optional<MachineDto> machine = machineService.createMachineByName(name);
        return machine.isPresent() ? new ResponseEntity(machine.get(), HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/machine/{id}")
    public ResponseEntity deleteMachine(@PathVariable Long id) {
        return machineService.deleteMachineById(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
