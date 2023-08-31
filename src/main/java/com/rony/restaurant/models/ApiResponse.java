package com.rony.restaurant.models;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    public boolean success;
    public String message;
    public Object data;

    public ApiResponse(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
