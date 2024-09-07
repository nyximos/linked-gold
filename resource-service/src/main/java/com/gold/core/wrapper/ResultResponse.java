package com.gold.core.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ResultResponse<T> implements Serializable {
    private boolean success;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageResponse links;

    public ResultResponse() {
        this.success = true;
        this.message = "success";
    }

    public ResultResponse(T data) {
        this.success = true;
        this.message = "success";
        this.data = data;
    }

    public ResultResponse(String message) {
        this.success = true;
        this.message = message;
    }

    public ResultResponse(boolean status, String message) {
        this.success = status;
        this.message = message;
    }

    public T getResult() {
        return data;
    }
}
