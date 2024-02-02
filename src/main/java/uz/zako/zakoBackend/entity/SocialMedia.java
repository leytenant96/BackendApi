package uz.zako.zakoBackend.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialMedia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TelegramLink")
    String telegramLink;

    @Column(name = "InstagramLink")
    String instagramLink;

    @Column(name = "YouTubeLink")
    String youTubeLink;



}
