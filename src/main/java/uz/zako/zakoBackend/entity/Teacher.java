package uz.zako.zakoBackend.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fullName;
    String descriptionUZ;
    String descriptionRU;

    @OneToOne(cascade = CascadeType.REMOVE)
    Attachment image;

    @ManyToMany(fetch= FetchType.LAZY)
    List<Courses> courses;

    @CreationTimestamp
    Date cratedAt;

    @UpdateTimestamp
    Date updatedAt;

}
