package uz.zako.zakoBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesPayload {
    String name;
    String bodyUZ;
    String bodyRU;
    String hashId;
    String duration;
    Long price;
}
