package kg.mega.saloon.dao;

import kg.mega.saloon.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRep extends JpaRepository<Schedule, Long> {

    @Query(value = "select ms.* FROM tb_master_schedule as ms \n" +
            "INNER JOIN tb_master as m on ms.id=m.id WHERE m.name=:name", nativeQuery = true)
    List<Schedule> getScheduleByMasterName(String name);
}
