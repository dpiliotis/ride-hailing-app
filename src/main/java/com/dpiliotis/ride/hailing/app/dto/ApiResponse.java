package com.dpiliotis.ride.hailing.app.dto;

import java.util.List;

public class ApiResponse<T> {

    private T data;
    private List<ErrorDto> errors;
    private List<ActionDto> actions;

    public ApiResponse() { }

    public ApiResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public List<ActionDto> getActions() {
        return actions;
    }

    public void setActions(List<ActionDto> actions) {
        this.actions = actions;
    }
}
