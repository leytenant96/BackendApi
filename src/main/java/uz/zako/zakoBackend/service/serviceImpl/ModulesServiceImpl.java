package uz.zako.zakoBackend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import uz.zako.zakoBackend.entity.Modules;
import uz.zako.zakoBackend.exeption.ResourceNotFound;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.ModulesPayload;
import uz.zako.zakoBackend.repository.CoursesRepository;
import uz.zako.zakoBackend.repository.ModulesRepository;
import uz.zako.zakoBackend.service.ModulesService;

import java.net.ResponseCache;

@Service
@RequiredArgsConstructor

public class ModulesServiceImpl implements ModulesService {
    private final Result result;
    private final AttachmentServiceImpl attachmentService;
    private final ModulesRepository modulesRepository;
    private final CoursesRepository coursesRepository;
    @Override
    public Result saveModule(ModulesPayload modulePayload) {
        try {
            Modules modules = Modules.builder()
                    .name(modulePayload.getName())
                    .bodyUZ(modulePayload.getBodyUZ())
                    .bodyRU(modulePayload.getBodyRU())
                    .courseId(modulePayload.getCourseId())
                    .image(attachmentService.findAttachmentByHashId(modulePayload.getHashId()))
                    .build();
            modulesRepository.save(modules);
            return result.success(modules);
        }catch (Exception e){
            return result.failed(e);
        }
    }
    @Override
    public Result findById(Long id) {
        try {
            return result.success(modulesRepository.findById(id).orElseThrow(()->new ResourceNotFound("Module","id",id)));
        }catch (Exception e){
            return result.failed(e);
        }
    }
    @Override
    public Result delete(Long id) {
        try {
            modulesRepository.deleteById(id);
            return result.success("DELETED");
        }catch (Exception e){
            return result.failed(e);
        }
    }
    @Override
    public Result editModule(Long id, ModulesPayload modulePayload) {
        try {
            Modules modules = modulesRepository.findById(id).orElseThrow(()->new ResourceNotFound("Module","id",id));
            if (modulePayload.getName()!=null) modules.setName(modules.getName());
            if (modulePayload.getBodyUZ()!=null) modules.setBodyUZ(modulePayload.getBodyUZ());
            if (modulePayload.getBodyRU()!=null) modules.setBodyRU(modulePayload.getBodyRU());
            if (modulePayload.getCourseId()!=null) modules.setCourseId(modulePayload.getCourseId());
            if (modulePayload.getHashId()!=null) modules.setImage(attachmentService.findAttachmentByHashId(modulePayload.getHashId()));
            modulesRepository.save(modules);
            return result.success(modules);

        }catch (Exception e){
            return result.failed(e);
        }
    }
    @Override
    public Result findAllByCourseId(Long courseId) {
        try {
            if (coursesRepository.existsById(courseId)){
                return result.success(modulesRepository.findAllByCourseIdOrderById(courseId));
            }
            return result.failed(new ResourceNotFound("Course","id",courseId));
        }catch (Exception e){
            return result.failed(e);
        }
    }
}
