package com.autobid.autobid.Factory;

import java.util.List;

public class MessageFactory<T> {
    private String message;
    private List<T> data;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public MessageFactory MessageResponse(String message, boolean success, List<T> data) {
        this.message = message;
        this.success = success;
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}