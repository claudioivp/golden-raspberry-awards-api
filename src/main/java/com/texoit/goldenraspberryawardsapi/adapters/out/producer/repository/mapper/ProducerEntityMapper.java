package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProducerMovieEntityMapper.class})
public interface ProducerEntityMapper {

    ProducerEntity toProducerEntity(Producer producer);
    Producer toProducer(ProducerEntity producerEntity);

}
