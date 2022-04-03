package io.github.mmpodkanski.reservation;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OBJECTS_FOR_RENT")
class ObjectForRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OBJECT_NAME")
    private String objectName;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "OBJECT_SPACE")
    private Integer space;

    @Column(name = "DESCRIPTION")
    private String description;

    private ObjectForRent() {
        // private constructor to hide public one
    }

    String getObjectName() {
        return objectName;
    }

    BigDecimal getPrice() {
        return price;
    }
}
