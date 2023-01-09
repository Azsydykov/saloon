package kg.mega.saloon.models.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity   //создает нам такблицы в postgres
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_client")
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться
    Long id;
    String name;
    String surname;
    @Column(unique = true)
    String phoneNumber;
    @Column(unique = true)
    String email;
    boolean active;

    @PrePersist
    protected void onCreate() {
        active = true;
    }

}
