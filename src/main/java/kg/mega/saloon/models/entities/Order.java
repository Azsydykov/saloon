package kg.mega.saloon.models.entities;

import kg.mega.saloon.enums.OrderStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться
    Long id;
    Date addDate;
    Date updateDate;
    Date appointment_date;

    @Enumerated(EnumType.STRING)
    OrderStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @OneToOne
    @JoinColumn(name = "master_id")
    Master master;
    boolean active;


    @PrePersist
    protected void onCreate() {
        status = OrderStatusEnum.CONFIRM;
        addDate = new Date();
        updateDate = new Date();
        active = true;
    }
}
