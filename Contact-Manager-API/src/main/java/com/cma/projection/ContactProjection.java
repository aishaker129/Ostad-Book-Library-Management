package com.cma.projection;

import java.time.LocalDateTime;

public interface ContactProjection {
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhoneNumber();
    String getCategory();
    Boolean getIsActive();
    LocalDateTime getCreatedDate();
}
