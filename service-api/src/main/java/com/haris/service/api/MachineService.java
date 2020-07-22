package com.haris.service.api;

import com.haris.domain.dto.MachineDto;
import java.util.Optional;
import java.util.Set;

public interface MachineService {

    Set<MachineDto> getAllMachines();
    Optional<MachineDto> getMachineById(Long id);
    Optional<MachineDto> updateMachineById(Long id);
    Optional<MachineDto> createMachineByName(String name);
    boolean deleteMachineById(Long id);
}
