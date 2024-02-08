package com.grabACycle.grabACycle.config;

import com.grabACycle.grabACycle.entity.Cycle;
import org.springframework.batch.item.ItemProcessor;

public class CycleProcessor implements ItemProcessor<Cycle, Cycle> {

    @Override
    public Cycle process(Cycle cycle) throws Exception{
        return cycle;
    }
}
