package com.texoit.goldenraspberryawardsapi.adapters.in.controller;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper.StudioMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.StudioRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.StudioResponse;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindStudioByIdInputPort;
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
    private final FindStudioByIdInputPort findStudioByIdInputPort;
    private final StudioMapper studioMapper;

    public StudioController(InsertStudioInputPort insertStudioInputPort, FindStudioByIdInputPort findStudioByIdInputPort, StudioMapper studioMapper) {
        this.insertStudioInputPort = insertStudioInputPort;
        this.findStudioByIdInputPort = findStudioByIdInputPort;
        this.studioMapper = studioMapper;
    }

    @PostMapping
    public ResponseEntity<StudioResponse> insert(@Valid @RequestBody StudioRequest studioRequest, UriComponentsBuilder uriBuilder) {
        var studio = insertStudioInputPort.insert(studioMapper.toStudio(studioRequest));
        var uri = uriBuilder.path("api/v1/studios/{id}").buildAndExpand(studio.getId()).toUri();

        return ResponseEntity.created(uri).body(studioMapper.toStudioResponse(studio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudioResponse> retrieve(@PathVariable UUID id) {
        var studioResponse = studioMapper.toStudioResponse(findStudioByIdInputPort.find(id));
        return ResponseEntity.ok(studioResponse);
    }
}
