package uz.zako.zakoBackend.service;

import uz.zako.zakoBackend.entity.Register;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.RegisterPayload;

import java.util.List;

public interface RegisterService {
    Result saveRegister(RegisterPayload registerPayload);
    Result editRegister(Long id, RegisterPayload registerPayload);
    Result deleteRegister(Long id);
    Result findById(Long id);
    Result getAll();
    List<Register> finAllByCourses(Long courses);
}
