package com.texoit.goldenraspberryawardsapi.adapters.out.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.mapper.AwardIntervalMapper;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.FindMinAwardIntervalOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindMinAwardIntervalAdapter implements FindMinAwardIntervalOutputPort {
    
    private final ProducerRepository producerRepository;
    private final AwardIntervalMapper awardIntervalMapper;

    public FindMinAwardIntervalAdapter(ProducerRepository producerRepository, AwardIntervalMapper awardIntervalMapper) {
        this.producerRepository = producerRepository;
        this.awardIntervalMapper = awardIntervalMapper;
    }

    @Override
    public List<AwardInterval> findAll() {
        List<ProducerIntervalsProjection> intervals = producerRepository.findMinIntervals();
        return awardIntervalMapper.toAwardInterval(intervals);
    }

}
