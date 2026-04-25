package com.example.releasepilot.service;

import com.example.releasepilot.model.ReleaseCheck;
import com.example.releasepilot.model.ReleaseStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReleaseService {
    private final List<ReleaseCheck> checks = new ArrayList<>();

    public ReleaseService() {
        checks.add(new ReleaseCheck(1L, "Source code pushed to GitHub", "Code is available in GitHub repository", ReleaseStatus.PASSED));
        checks.add(new ReleaseCheck(2L, "GitHub webhook configured", "GitHub can trigger Jenkins automatically", ReleaseStatus.IN_PROGRESS));
        checks.add(new ReleaseCheck(3L, "Maven build completed", "Project builds using mvn clean package", ReleaseStatus.PENDING));
        checks.add(new ReleaseCheck(4L, "Build artifact archived", "JAR file is stored in Jenkins artifacts", ReleaseStatus.PENDING));
        checks.add(new ReleaseCheck(5L, "Deployment approval received", "Release is approved for deployment", ReleaseStatus.PENDING));
    }

    public List<ReleaseCheck> getAllChecks() {
        return checks;
    }

    public Optional<ReleaseCheck> updateStatus(Long id, ReleaseStatus status) {
        return checks.stream()
                .filter(check -> check.getId().equals(id))
                .findFirst()
                .map(check -> {
                    check.setStatus(status);
                    return check;
                });
    }

    public Map<String, Object> getReleaseSummary() {
        long total = checks.size();
        long passed = checks.stream().filter(check -> check.getStatus() == ReleaseStatus.PASSED).count();
        long failed = checks.stream().filter(check -> check.getStatus() == ReleaseStatus.FAILED).count();
        long pending = checks.stream().filter(check -> check.getStatus() == ReleaseStatus.PENDING).count();
        long inProgress = checks.stream().filter(check -> check.getStatus() == ReleaseStatus.IN_PROGRESS).count();

        int readiness = (int) ((passed * 100) / total);
        String releaseStatus = readiness == 100 ? "READY_TO_DEPLOY" : "NOT_READY";

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("releaseName", "Jenkins CI/CD Demo Release");
        summary.put("totalChecks", total);
        summary.put("passedChecks", passed);
        summary.put("failedChecks", failed);
        summary.put("pendingChecks", pending);
        summary.put("inProgressChecks", inProgress);
        summary.put("readinessPercentage", readiness);
        summary.put("releaseStatus", releaseStatus);

        return summary;
    }
}
