package io.github.mmpodkanski.user;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.id from User u where u.name = ?1")
    Long getUserIdByName(String name);

    default Map<Long, String> findAllUserNamesMap() {
        return findAll().stream().collect(Collectors.toMap(User::getId, User::getName));
    }
}
