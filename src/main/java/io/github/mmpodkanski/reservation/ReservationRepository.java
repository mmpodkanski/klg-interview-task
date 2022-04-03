package io.github.mmpodkanski.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllReservationsByObjectForRentObjectName(String objectName);

    List<Reservation> getAllReservationsByTenantId(Long tenantId);
}
