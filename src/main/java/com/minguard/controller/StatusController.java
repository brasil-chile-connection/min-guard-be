package com.minguard.controller;

import com.minguard.dto.status.StatusResponse;
import com.minguard.service.spec.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Status Controller")
@RequestMapping("/status")
@RestController
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    @Operation(summary = "Get all statuses", description = "Get all registered statuses.")
    public ResponseEntity<List<StatusResponse>> getStatuses() {
        List<StatusResponse> statuses = statusService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(statuses);
    }

}
