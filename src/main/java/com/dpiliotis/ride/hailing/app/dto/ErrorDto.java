package com.dpiliotis.ride.hailing.app.dto;

public class ErrorDto {

    private String description;

    public ErrorDto() { }

    public ErrorDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
