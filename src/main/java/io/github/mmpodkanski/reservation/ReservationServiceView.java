package io.github.mmpodkanski.reservation;

import java.util.List;

import io.github.mmpodkanski.reservation.dto.ReservationDTO;

interface ReservationServiceView {
    List<ReservationDTO> getAllReservationsByObjectName(String objectName);

    List<ReservationDTO> getAllReservationsByTenantName(String tenantName);
}
