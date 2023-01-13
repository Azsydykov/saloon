package kg.mega.saloon.dao;

import kg.mega.saloon.models.entities.MasterSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterScheduleRep extends JpaRepository<MasterSchedule, Long> {
}
