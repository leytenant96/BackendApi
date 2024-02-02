package uz.zako.zakoBackend.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "connection")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

   @Column(unique = true)
    String phoneNumber;

   @Column(unique = true)
    String email;

   @Column()
    String addressUz;

   @Column()
   String addressRu;

}
