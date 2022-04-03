package io.github.mmpodkanski.reservation;

import static io.github.mmpodkanski.security.ExceptionMessages.TENANT_NOT_EXISTS;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.github.mmpodkanski.reservation.dto.ReservationDTO;
import io.github.mmpodkanski.user.UserRepository;

@Transactional(readOnly = true)
@Component
class ReservationServiceViewImpl implements ReservationServiceView {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    ReservationServiceViewImpl(final ReservationRepository reservationRepository, final UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ReservationDTO> getAllReservationsByObjectName(final String objectName) {
        Map<Long, String> allUserNames = userRepository.findAllUserNamesMap();
        List<Reservation> allReservationsByObject = reservationRepository.getAllReservationsByObjectForRentObjectName(
                objectName);

        return allReservationsByObject.stream()
                .map(reservation -> new ReservationDTO(reservation.getId(),
                        reservation.getDaysBooked(),
                        reservation.getStartDate(),
                        reservation.getEndDate(),
                        allUserNames.get(reservation.getLessorId()),
                        allUserNames.get(reservation.getTenantId()),
                        reservation.getObjectForRent().getObjectName(),
                        reservation.getCost()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getAllReservationsByTenantName(final String tenantName) {
        Map<Long, String> allUserNames = userRepository.findAllUserNamesMap();
        Long tenantId = allUserNames.entrySet()
                .stream()
                .filter(entry -> tenantName.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(TENANT_NOT_EXISTS.getMessage()));

        List<Reservation> allReservationsByObject = reservationRepository.getAllReservationsByTenantId(tenantId);

        return allReservationsByObject.stream()
                .map(reservation -> new ReservationDTO(reservation.getId(),
                        reservation.getDaysBooked(),
                        reservation.getStartDate(),
                        reservation.getEndDate(),
                        allUserNames.get(reservation.getLessorId()),
                        tenantName,
                        reservation.getObjectForRent().getObjectName(),
                        reservation.getCost()))
                .collect(Collectors.toList());
    }
}
