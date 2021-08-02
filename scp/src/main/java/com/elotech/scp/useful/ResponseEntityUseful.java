package com.elotech.scp.useful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseEntityUseful {

    public static ResponseEntity<?> getResponseEntity(Object obj) {
        return getResponseEntity(HttpStatus.OK, obj);
    }

    public static ResponseEntity<?> getResponseEntity(HttpStatus status, Object obj) {
        if (obj == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(status).body(obj);
    }

    public static ResponseEntity<?> getResponseEntity(Optional<?> obj) {
        return getResponseEntity(HttpStatus.OK, obj);
    }

    public static ResponseEntity<?> getResponseEntity(HttpStatus status, Optional<?> obj) {
        if (obj.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(status).body(obj);
    }

}
