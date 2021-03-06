package com.haris.service.dto;

import com.haris.domain.entity.Machine;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MachineDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;

    public MachineDto(Machine machine) {
            this.id = machine.getId();
            this.createdAt = machine.getCreatedAt();
            this.updatedAt = machine.getUpdatedAt();
            this.name = machine.getName();
    }
}
