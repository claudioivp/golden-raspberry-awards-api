package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.MovieEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerEntityMapper {

    ProducerEntity toProducerEntity(Producer producer);
    Producer toProducer(ProducerEntity producerEntity);

}
