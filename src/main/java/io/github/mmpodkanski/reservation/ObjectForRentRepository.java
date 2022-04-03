package io.github.mmpodkanski.reservation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ObjectForRentRepository extends JpaRepository<ObjectForRent, Long> {
    Optional<ObjectForRent> findObjectByObjectName(String name);
}
