package uz.zako.zakoBackend.service;

import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.ModulesPayload;

public interface ModulesService {
    Result saveModule(ModulesPayload modulePayload);
    Result findById(Long id);
    Result delete(Long id);
    Result editModule(Long id, ModulesPayload modulePayload);
    Result findAllByCourseId(Long courseId);

}
