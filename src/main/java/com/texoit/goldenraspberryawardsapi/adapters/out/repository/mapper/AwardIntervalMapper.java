package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.application.core.domain.AwardInterval;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AwardIntervalMapper {

    Set<AwardInterval> toAwardInterval(Set<ProducerIntervalsProjection> producerIntervalsProjection);

}
