package uz.zako.zakoBackend.payload;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectionPayload {
    String phoneNumber;
    String email;
    String addressUz;
    String addressRu;
}
