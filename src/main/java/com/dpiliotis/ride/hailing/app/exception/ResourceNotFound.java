package com.dpiliotis.ride.hailing.app.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() { }

    public ResourceNotFound(String message) {
        super(message);
    }
}
