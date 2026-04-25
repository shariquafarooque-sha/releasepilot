package com.example.releasepilot;

import com.example.releasepilot.model.ReleaseStatus;
import com.example.releasepilot.service.ReleaseService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReleaseServiceTest {

    @Test
    void shouldCalculateReleaseSummary() {
        ReleaseService service = new ReleaseService();

        assertEquals(5, service.getAllChecks().size());
        assertTrue(service.getReleaseSummary().containsKey("readinessPercentage"));
    }

    @Test
    void shouldUpdateCheckStatus() {
        ReleaseService service = new ReleaseService();

        service.updateStatus(3L, ReleaseStatus.PASSED);

        assertEquals(ReleaseStatus.PASSED, service.getAllChecks().get(2).getStatus());
    }
}
