package com.ssmdemo.dto;

import java.io.Serializable;

/**
 * Created by niu_ben on 2017/5/5.
 */
public class BaseResultList<T> implements Serializable {
    private static final long serialVersionUID = -4042979343030843949L;
    private boolean success;
    private T data;
    private String error;

    public BaseResultList(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public BaseResultList(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
