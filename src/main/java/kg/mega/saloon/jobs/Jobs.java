package kg.mega.saloon.jobs;

import kg.mega.saloon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Jobs {

    @Autowired
    private OrderService orderService;

//    @Scheduled(cron = "0 0/1 * * * ?")
//    private void  setInProccessOrders(){
//        System.out.println(LocalDateTime.now());
//        orderService.checkSuspendOrders();
//
//
//    }
}
