package com.example.releasepilot.model;

public class ReleaseCheck {
    private Long id;
    private String name;
    private String description;
    private ReleaseStatus status;

    public ReleaseCheck(Long id, String name, String description, ReleaseStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ReleaseStatus getStatus() {
        return status;
    }

    public void setStatus(ReleaseStatus status) {
        this.status = status;
    }
}
