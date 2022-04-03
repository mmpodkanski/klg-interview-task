package io.github.mmpodkanski.reservation;

import static io.github.mmpodkanski.security.ExceptionMessages.LESSOR_NOT_EXISTS;
import static io.github.mmpodkanski.security.ExceptionMessages.OBJECT_NOT_FOUND;
import static io.github.mmpodkanski.security.ExceptionMessages.RESERVATION_EXISTS;
import static io.github.mmpodkanski.security.ExceptionMessages.TENANT_NOT_EXISTS;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.mmpodkanski.reservation.dto.AddReservationDTO;
import io.github.mmpodkanski.security.ExceptionMessages;
import io.github.mmpodkanski.user.UserRepository;

@Transactional
@Service
class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ObjectForRentRepository objectsRepository;
    private final UserRepository userRepository;

    ReservationServiceImpl(final ReservationRepository reservationRepository,
            final ObjectForRentRepository objectsRepository, final UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.objectsRepository = objectsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addReservation(AddReservationDTO addReservationDTO) {
        String objectName = addReservationDTO.getObjectName();
        ObjectForRent objectToRent = checkIfReservationHasCorrectDataAndGetObject(objectName);

        Long lessorId = getUserId(addReservationDTO.getLessorName(), LESSOR_NOT_EXISTS);
        Long tenantId = getUserId(addReservationDTO.getTenantName(), TENANT_NOT_EXISTS);

        BigDecimal cost = getFinalCostOfRent(objectToRent.getPrice(), addReservationDTO.getDaysToBook());
        Integer daysToBook = addReservationDTO.getDaysToBook();

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(daysToBook);

        Reservation newReservation = new Reservation(daysToBook,
                startDate,
                endDate,
                lessorId,
                tenantId,
                objectToRent,
                cost);
        reservationRepository.save(newReservation);
    }

    @Override
    public void changeReservation(final AddReservationDTO addReservationDTO, Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);

        if (reservationOptional.isEmpty()) {
            addReservation(addReservationDTO);
            return;
        }

        String objectName = addReservationDTO.getObjectName();
        ObjectForRent objectToRent = checkIfReservationHasCorrectDataAndGetObject(objectName);
        Reservation reservation = reservationOptional.get();

        Long lessorId = getUserId(addReservationDTO.getLessorName(), LESSOR_NOT_EXISTS);
        Long tenantId = getUserId(addReservationDTO.getTenantName(), TENANT_NOT_EXISTS);

        BigDecimal cost = getFinalCostOfRent(objectToRent.getPrice(), addReservationDTO.getDaysToBook());
        Integer daysToBook = addReservationDTO.getDaysToBook();

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(daysToBook);

        reservation.update(daysToBook, startDate, endDate, lessorId, tenantId, objectToRent, cost);
        reservationRepository.save(reservation);
    }

    private ObjectForRent checkIfReservationHasCorrectDataAndGetObject(final String objectName) {
        Optional<ObjectForRent> objectOptional = objectsRepository.findObjectByObjectName(objectName);

        if (objectOptional.isEmpty()) {
            throw new IllegalArgumentException(OBJECT_NOT_FOUND.getMessage());
        }
        List<Reservation> reservationWithTheSameObject = reservationRepository.getAllReservationsByObjectForRentObjectName(
                objectName);

        boolean alreadyExistsReservation = reservationWithTheSameObject.stream()
                .anyMatch(reservation -> reservation.getEndDate().isAfter(LocalDateTime.now()));

        if (alreadyExistsReservation) {
            throw new IllegalStateException(RESERVATION_EXISTS.getMessage());
        }

        return objectOptional.get();
    }

    private Long getUserId(final String userName, final ExceptionMessages messageIfNotExists) {
        return Optional.ofNullable(userRepository.getUserIdByName(userName))
                .orElseThrow(() -> new IllegalArgumentException(messageIfNotExists.getMessage()));
    }

    private BigDecimal getFinalCostOfRent(final BigDecimal price, final Integer daysToBook) {
        return price.multiply(new BigDecimal(daysToBook));
    }
}
