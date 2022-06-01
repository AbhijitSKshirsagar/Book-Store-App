package com.example.bookstoreapp.dto;

import lombok.Data;

import java.awt.print.Book;
import java.util.List;

public @Data class ResponseDTO {
    private String message;
    private Object data;
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDTO() {
    }
}
