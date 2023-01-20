package kg.mega.saloon.dao;

import kg.mega.saloon.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClientRep extends JpaRepository<Client, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into ", nativeQuery = true)
    int update();
}
