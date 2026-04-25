package com.example.releasepilot.controller;

import com.example.releasepilot.model.ReleaseCheck;
import com.example.releasepilot.model.ReleaseStatus;
import com.example.releasepilot.service.ReleaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping("/checks")
    public List<ReleaseCheck> getChecks() {
        return releaseService.getAllChecks();
    }

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        return releaseService.getReleaseSummary();
    }

    @PutMapping("/checks/{id}/status")
    public ReleaseCheck updateStatus(@PathVariable Long id, @RequestParam ReleaseStatus status) {
        return releaseService.updateStatus(id, status)
                .orElseThrow(() -> new RuntimeException("Release check not found"));
    }
}
