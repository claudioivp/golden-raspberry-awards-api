package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AwardIntervalMapper {

    Set<AwardInterval> toAwardInterval(Set<ProducerIntervalsProjection> producerIntervalsProjection);

}
