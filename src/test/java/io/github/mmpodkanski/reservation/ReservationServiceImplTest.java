package io.github.mmpodkanski.reservation;

import io.github.mmpodkanski.reservation.dto.AddReservationDTO;
import io.github.mmpodkanski.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ObjectForRentRepository objectForRentRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    private ReservationService reservationService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        reservationService = new ReservationServiceImpl(
                reservationRepository,
                objectForRentRepositoryMock,
                userRepositoryMock
        );
    }

    @Test
    public void testAddReservationWhenOneAlreadyExists_shouldFail() {
        // GIVEN
        AddReservationDTO newReservation = new AddReservationDTO(30, "lessorName", "tenantName", "Object1");
        String objectName = newReservation.getObjectName();

        ObjectForRent objectForRent = new ObjectForRent(1L, "test", null, null, null);
        Optional<ObjectForRent> objectOptional = Optional.of(objectForRent);
        List<Reservation> alreadyExistingReservations = List.of(
                new Reservation(
                        10,
                        LocalDateTime.of(2022, 1, 1, 0, 0),
                        LocalDateTime.now().plusDays(1),
                        null, null, null, null));

        when(objectForRentRepositoryMock.findObjectByObjectName(objectName)).thenReturn(objectOptional);
        when(reservationRepository.getAllReservationsByObjectForRentObjectName(objectName)).thenReturn(alreadyExistingReservations);

        // WHEN && THEN
        Assertions.assertThrowsExactly(IllegalStateException.class, () -> reservationService.addReservation(newReservation));

    }
}
