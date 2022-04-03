package io.github.mmpodkanski.reservation;

import javax.persistence.*;
import java.math.BigDecimal;

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

    ObjectForRent(Long id, String objectName, BigDecimal price, Integer space, String description) {
        this.id = id;
        this.objectName = objectName;
        this.price = price;
        this.space = space;
        this.description = description;
    }

    String getObjectName() {
        return objectName;
    }

    BigDecimal getPrice() {
        return price;
    }
}
