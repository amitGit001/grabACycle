package com.grabACycle.grabACycle.web.dto;

import com.grabACycle.grabACycle.entity.Cycle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CycleDto {

    private List<Cycle> cycles;
    private long totalItems;

    public CycleDto(List<Cycle> cycles, long totalItems) {
        this.cycles = cycles;
        this.totalItems = totalItems;
    }
}
