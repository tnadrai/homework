package com.haris.service.impl;

import com.haris.service.dto.MachineDto;
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

    private MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Set<MachineDto> getAllMachines() {
        return machineRepository.findAll()
            .stream().map(MachineDto::new)
            .collect(Collectors.toSet());
    }

    @Override
    public Optional<MachineDto> getMachineById(Long id) {
        return machineRepository.findById(id).map(MachineDto::new);
    }

    @Override
    public Optional<MachineDto> updateMachineNameById(Long id, String name) {
        Optional<Machine> machineOpt = machineRepository.findById(id);

        if (machineOpt.isPresent()) {
            Machine machine = machineOpt.get();
            machine.setName(name);
            return Optional.of(new MachineDto(machineRepository.save(machine)));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MachineDto> createMachineByName(String name) {

        Machine machine = new Machine();
        machine.setName(name);

        return Optional.of(new MachineDto(machineRepository.save(machine)));

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
