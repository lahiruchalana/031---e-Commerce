package com.lciresh.cartservice.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IllegalPermissionException extends RuntimeException {

    private String message;

    public IllegalPermissionException(String message) {
        super(message);
        this.message =message;
    }

}
