package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response.ProducerIntervalsProjection;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwardIntervalMapper {

    List<AwardInterval> toAwardInterval(List<ProducerIntervalsProjection> producerIntervalsProjection);

}
