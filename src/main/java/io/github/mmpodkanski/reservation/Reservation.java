package io.github.mmpodkanski.reservation;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATIONS")
class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DAYS_BOOKED")
    private Integer daysBooked;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "LESSOR_ID")
    private Long lessorId;

    @Column(name = "TENANT_ID")
    private Long tenantId;

    @OneToOne
    @JoinColumn(name = "OBJECT_ID")
    private ObjectForRent objectForRent;

    @Column(name = "COST")
    private BigDecimal cost;

    private Reservation() {
        // private constructor to hide public one
    }

    Reservation(final Integer daysBooked, final LocalDateTime startDate, final LocalDateTime endDate, final Long lessorId,
            final Long tenantId, final ObjectForRent objectForRent, final BigDecimal cost) {
        this.daysBooked = daysBooked;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lessorId = lessorId;
        this.tenantId = tenantId;
        this.objectForRent = objectForRent;
        this.cost = cost;
    }

    void update(final Integer daysBooked, final LocalDateTime startDate, final LocalDateTime endDate, final Long lessorId,
            final Long tenantId, final ObjectForRent objectForRent, final BigDecimal cost) {
        this.daysBooked = daysBooked;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lessorId = lessorId;
        this.tenantId = tenantId;
        this.objectForRent = objectForRent;
        this.cost = cost;
    }

    Long getId() {
        return id;
    }

    Integer getDaysBooked() {
        return daysBooked;
    }

    LocalDateTime getStartDate() {
        return startDate;
    }

    LocalDateTime getEndDate() {
        return endDate;
    }

    Long getLessorId() {
        return lessorId;
    }

    Long getTenantId() {
        return tenantId;
    }

    ObjectForRent getObjectForRent() {
        return objectForRent;
    }

    BigDecimal getCost() {
        return cost;
    }
}
