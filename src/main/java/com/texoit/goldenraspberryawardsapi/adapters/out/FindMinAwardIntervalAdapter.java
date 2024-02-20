package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.AwardIntervalMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.application.core.domain.AwardInterval;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindMinAwardIntervalOutputPort;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FindMinAwardIntervalAdapter implements FindMinAwardIntervalOutputPort {
    
    private final ProducerRepository producerRepository;
    private final AwardIntervalMapper awardIntervalMapper;

    public FindMinAwardIntervalAdapter(ProducerRepository producerRepository, AwardIntervalMapper awardIntervalMapper) {
        this.producerRepository = producerRepository;
        this.awardIntervalMapper = awardIntervalMapper;
    }

    @Override
    public Set<AwardInterval> findAll() {
        Set<ProducerIntervalsProjection> intervals = producerRepository.findMinIntervals();
        return awardIntervalMapper.toAwardInterval(intervals);
    }

}
