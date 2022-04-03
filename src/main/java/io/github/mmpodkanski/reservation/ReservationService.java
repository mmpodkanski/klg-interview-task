package io.github.mmpodkanski.reservation;

import org.springframework.stereotype.Service;

import io.github.mmpodkanski.reservation.dto.AddReservationDTO;

@Service
interface ReservationService {
    void addReservation(AddReservationDTO addReservationDTO);

    void changeReservation(AddReservationDTO addReservationDTO, Long reservationId);
}
