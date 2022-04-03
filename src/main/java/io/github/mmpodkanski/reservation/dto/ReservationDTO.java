package io.github.mmpodkanski.reservation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationDTO {
    private final Long id;
    private final Integer daysBooked;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String lessorName;
    private final String tenantName;
    private final String objectName;
    private final BigDecimal cost;

    public ReservationDTO(final Long id, final Integer daysBooked, final LocalDateTime startDate,
            final LocalDateTime endDate, final String lessorName, final String tenantName, final String objectName,
            final BigDecimal cost) {
        this.id = id;
        this.daysBooked = daysBooked;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lessorName = lessorName;
        this.tenantName = tenantName;
        this.objectName = objectName;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public Integer getDaysBooked() {
        return daysBooked;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
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

    public BigDecimal getCost() {
        return cost;
    }
}
