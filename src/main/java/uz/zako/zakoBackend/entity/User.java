package uz.zako.zakoBackend.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true,nullable = false)
    String userName;
    String password;
    String fullName;

    @Column(unique = true,nullable = false)
    String phoneNumber;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updateAt;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roleList;  // ROLE_ADMIN ROLE_USER

}
