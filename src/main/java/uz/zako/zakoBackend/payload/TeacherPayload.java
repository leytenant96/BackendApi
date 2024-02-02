package uz.zako.zakoBackend.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherPayload {
    String fullName;
    String descriptionUZ;
    String descriptionRU;
    List<Long> courses;   // 1 2
    String hashId;
}
