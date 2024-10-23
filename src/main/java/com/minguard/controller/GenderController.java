package com.minguard.controller;

import com.minguard.dto.gender.GenderResponse;
import com.minguard.service.spec.GenderService;
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
@Tag(name = "Gender Controller")
@RequestMapping("/gender")
@RestController
public class GenderController {

    private final GenderService genderService;

    @GetMapping
    @Operation(summary = "Get all genders", description = "Get all registered genders.")
    public ResponseEntity<List<GenderResponse>> getGenders() {
        List<GenderResponse> genders = genderService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(genders);
    }
}
