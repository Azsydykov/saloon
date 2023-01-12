package kg.mega.saloon.dao;

import kg.mega.saloon.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRep extends JpaRepository<Schedule, Long> {


    //@Query(value = "select end_time,start_time,work_day from tb_master_schedule as ms JOIN\n" +
//        "tb_schedule as s on ms.schedule_id=s.id JOIN tb_master as m on ms.master_id=m.id\n" +
//        "WHERE m.name=:name", nativeQuery = true)

    @Query("select s from MasterSchedule ms inner join Schedule s on ms.schedule.id=s.id inner join Master m on ms.master.id=m.id\n" +
            "where m.name=:name")
    List<Schedule> getScheduleByMasterName(String name);

    @Query("select s from MasterSchedule ms inner join Schedule s on ms.schedule.id=s.id inner join Master m on ms.master.id=m.id\n" +
            "where m.id=:id")
    List<Schedule> getScheduleByMasterId(Long id);
}
