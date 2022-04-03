package io.github.mmpodkanski.reservation.dto;

import java.io.Serial;
import java.io.Serializable;

public class AddReservationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7393306547106612835L;
    private Integer daysToBook;
    private String lessorName;
    private String tenantName;
    private String objectName;

    public AddReservationDTO() {
    }

    public Integer getDaysToBook() {
        return daysToBook;
    }

    public String getLessorName() {
        return lessorName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public String getObjectName() {
        return objectName;
    }
}
