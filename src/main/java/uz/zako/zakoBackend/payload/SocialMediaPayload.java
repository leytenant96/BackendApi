package uz.zako.zakoBackend.payload;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialMediaPayload {
String telegramLink;
String instagramLink;
String youTubeLink;
}
