package uz.zako.zakoBackend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.zako.zakoBackend.entity.Register;
import uz.zako.zakoBackend.exeption.ResourceNotFound;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.RegisterPayload;
import uz.zako.zakoBackend.repository.RegisterRepository;
import uz.zako.zakoBackend.service.RegisterService;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepository registerRepository;
    private final CourseServiceImpl courseService;
    private final Result result;
    @Override
    public Result saveRegister(RegisterPayload registerPayload) {
        try {
            Register register=Register.builder()
                    .fullName(registerPayload.getFullName())
                    .phoneNumber(registerPayload.getPhoneNumber())
                    .courses(courseService.getCourse(registerPayload.getCourses()))
                    .build();
            registerRepository.save(register);
            return result.success(register);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result findById(Long id) {
        try {
            return result.success(registerRepository.findById(id).orElseThrow(()->new ResourceNotFound("register","id",id)));
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result editRegister(Long id, RegisterPayload registerPayload) {
        try {
            Register register=registerRepository.findById(id).orElseThrow();
            register.setFullName(registerPayload.getFullName());
            register.setPhoneNumber(registerPayload.getPhoneNumber());
            register.setCourses(courseService.getCourse(registerPayload.getCourses()));
            registerRepository.save(register);
            return result.success(register);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result deleteRegister(Long id) {
        try {
            Register register=registerRepository.findById(id).orElseThrow(()->new ResourceNotFound("register","id",id));
            registerRepository.delete(register);
            return result.success("register deleted");
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed  (e);
        }
    }

    @Override
    public Result getAll() {
        try {
            return result.success(registerRepository.findAll());
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public List<Register> finAllByCourses(Long courses) {
        try {
            return registerRepository.finAllByCourses(courses);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

}
