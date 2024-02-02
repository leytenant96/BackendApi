package uz.zako.zakoBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulesPayload {
    String name;
    String bodyUZ;
    String bodyRU;
    Long courseId;
    String hashId;
}
