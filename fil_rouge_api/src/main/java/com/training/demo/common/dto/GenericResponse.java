package com.training.demo.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {
    private T data;
    //private T errors;

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .data(data)
                .build();
    }

}
