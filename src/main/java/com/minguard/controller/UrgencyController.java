package com.minguard.controller;

import com.minguard.dto.urgency.RegisterUrgencyRequest;
import com.minguard.dto.urgency.RegisterUrgencyResponse;
import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.service.spec.UrgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("/register")
    @Operation(summary = "Create a new urgency", description = "Creates a new urgency in the system.")
    public ResponseEntity<RegisterUrgencyResponse> register(@Valid @RequestBody RegisterUrgencyRequest request) {

        RegisterUrgencyResponse registeredUrgency = urgencyService.register(request);

        return ResponseEntity.ok(registeredUrgency);
    }
}
