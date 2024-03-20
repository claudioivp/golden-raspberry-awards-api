package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieProducerEntityMapper {

    @Mapping(target = "movies", ignore = true)
    ProducerEntity toProducerEntity(Producer producer);
    @Mapping(target = "movies", ignore = true)
    Producer toProducer(ProducerEntity producerEntity);

}