package com.minguard.controller;

import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.service.spec.UrgencyService;
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
@Tag(name = "Urgency Controller")
@RequestMapping("/urgency")
@RestController
public class UrgencyController {

    private final UrgencyService urgencyService;

    @GetMapping
    @Operation(summary = "Get all urgencys", description = "Get all registered urgencys.")
    public ResponseEntity<List<UrgencyResponse>> getUrgencys() {
        List<UrgencyResponse> urgencys = urgencyService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(urgencys);
    }
}
