package io.github.mmpodkanski.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.mmpodkanski.reservation.dto.AddReservationDTO;
import io.github.mmpodkanski.reservation.dto.ReservationDTO;

@RestController
@RequestMapping("/reservations")
class ReservationController {
    private final ReservationService reservationService;
    private final ReservationServiceView reservationServiceView;

    ReservationController(final ReservationService reservationService,
            final ReservationServiceView reservationServiceView) {
        this.reservationService = reservationService;
        this.reservationServiceView = reservationServiceView;
    }

    @GetMapping("/objectName/{objectName}")
    ResponseEntity<List<ReservationDTO>> getAllReservationByObjectName(@PathVariable String objectName) {
        return new ResponseEntity<>(reservationServiceView.getAllReservationsByObjectName(objectName), HttpStatus.OK);
    }

    @GetMapping("/tenantName/{tenantName}")
    ResponseEntity<List<ReservationDTO>> getAllReservationByTenantName(@PathVariable String tenantName) {
        return new ResponseEntity<>(reservationServiceView.getAllReservationsByTenantName(tenantName), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Void> addReservation(@RequestBody AddReservationDTO addReservationDTO) {
        reservationService.addReservation(addReservationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> addReservation(@RequestBody AddReservationDTO addReservationDTO, @PathVariable Long id) {
        reservationService.changeReservation(addReservationDTO, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
