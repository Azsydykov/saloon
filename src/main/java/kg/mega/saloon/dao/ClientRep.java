package kg.mega.saloon.dao;

import kg.mega.saloon.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRep extends JpaRepository<Client, Long> {
}
