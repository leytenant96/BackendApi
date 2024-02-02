package uz.zako.zakoBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPayload {
    String userName;
    String password;
    String fullName;
    String phoneNumber;

    List<Long> rolesId;
}
