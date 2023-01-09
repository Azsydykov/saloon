package kg.mega.saloon.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "saloon_tb")
public class Saloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться
    Long id;
    String name;
    String address;
    @Column(unique = true)
    String phoneNumber;
    boolean active;

    @PrePersist
    protected void onCreate() {
        active = true;
    }

}
