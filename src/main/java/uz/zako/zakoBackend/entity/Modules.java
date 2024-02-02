package uz.zako.zakoBackend.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Modules implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String bodyUZ;
    String bodyRU;
    Long courseId;

    @OneToOne
    Attachment image;


    @CreationTimestamp
    Date cratedAt;

    @UpdateTimestamp
    Date updatedAt;
    



}
