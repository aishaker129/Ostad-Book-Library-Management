package com.cma.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private String category;
}
