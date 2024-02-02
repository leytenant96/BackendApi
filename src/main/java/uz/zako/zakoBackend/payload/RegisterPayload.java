package uz.zako.zakoBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterPayload {
    String fullName;
    String phoneNumber;
    List<Long> courses;
}
