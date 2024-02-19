package com.texoit.goldenraspberryawardsapi.adapters.in.controller;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper.StudioMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.StudioRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.StudioResponse;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.MovieStudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertStudioInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/studios")
public class StudioController {

    private final InsertStudioInputPort insertStudioInputPort;
    private final StudioMapper studioMapper;
    private final MovieStudioEntityMapper studioEntityMapper;
    private final StudioRepository studioRepository;

    public StudioController(InsertStudioInputPort insertStudioInputPort, StudioMapper studioMapper, MovieStudioEntityMapper studioEntityMapper, StudioRepository studioRepository) {
        this.insertStudioInputPort = insertStudioInputPort;
        this.studioMapper = studioMapper;
        this.studioEntityMapper = studioEntityMapper;
        this.studioRepository = studioRepository;
    }

    @PostMapping
    public ResponseEntity<StudioResponse> insert(@Valid @RequestBody StudioRequest studioRequest, UriComponentsBuilder uriBuilder) {
        var studio = insertStudioInputPort.insert(studioMapper.toStudio(studioRequest));
        var uri = uriBuilder.path("api/v1/studios/{id}").buildAndExpand(studio.getId()).toUri();

        return ResponseEntity.created(uri).body(studioMapper.toStudioResponse(studio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioResponse> retrieve(@PathVariable UUID id) {
        var studioResponse = studioMapper.toStudioResponse(studioEntityMapper.toStudio(studioRepository.getReferenceById(id)));
        return ResponseEntity.ok(studioResponse);
    }
}
