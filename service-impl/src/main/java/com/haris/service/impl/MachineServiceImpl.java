package com.haris.service.impl;

import com.haris.domain.dto.MachineDto;
import com.haris.domain.entity.Machine;
import com.haris.repository.MachineRepository;
import com.haris.service.api.MachineService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public Set<MachineDto> getAllMachines() {
        Set<MachineDto> result = machineRepository.findAll()
            .stream().map(m -> new MachineDto(m))
            .collect(Collectors.toSet());
        return result;
    }

    @Override
    public Optional<MachineDto> getMachineById(Long id) {
        Optional<Machine> machine = machineRepository.findById(id);
        if (machine.isPresent()) {
            return Optional.ofNullable(new MachineDto(machine.get()));
        } else return Optional.empty();
    }

    @Override
    public Optional<MachineDto> updateMachineById(Long id) {
        Optional<Machine> machineOpt = machineRepository.findById(id);

        if (machineOpt.isPresent()) {
            Machine machine = machineOpt.get();
            machine.setUpdatedAt(LocalDateTime.now());
            return Optional.ofNullable(new MachineDto(machineRepository.save(machine)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MachineDto> createMachineByName(String name) {

        Machine machine = new Machine();
        machine.setName(name);
        machine.setCreatedAt(LocalDateTime.now());
        machine.setDeleted(false);
        machine.setUpdatedAt(null);

        return Optional.ofNullable(new MachineDto(machineRepository.save(machine)));

    }

    @Override
    public boolean deleteMachineById(Long id) {
        Optional<Machine> machineOpt = machineRepository.findById(id);
        if (machineOpt.isPresent()) {
            machineRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
