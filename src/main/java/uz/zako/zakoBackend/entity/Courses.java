package uz.zako.zakoBackend.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Courses implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String bodyUZ;
    String bodyRU;
    String duration;
    Long price;
    // image
    @OneToOne(cascade = CascadeType.REMOVE)
    Attachment image;


    @CreationTimestamp
    Date cratedAt;

    @UpdateTimestamp
    Date updatedAt;




}
