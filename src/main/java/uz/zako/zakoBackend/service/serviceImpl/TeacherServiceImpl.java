package uz.zako.zakoBackend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.zako.zakoBackend.entity.Teacher;
import uz.zako.zakoBackend.exeption.ResourceNotFound;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.TeacherPayload;
import uz.zako.zakoBackend.repository.TeacherRepository;
import uz.zako.zakoBackend.service.TeacherService;

import java.io.File;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final AttachmentServiceImpl attachmentService;
    private final CourseServiceImpl courseService;
    private final Result result;
    @Override
    public Result saveTeacher(TeacherPayload teacherPayload) {
        try {
            Teacher teacher=Teacher.builder()
                    .fullName(teacherPayload.getFullName())
                    .descriptionRU(teacherPayload.getDescriptionRU())
                    .descriptionUZ(teacherPayload.getDescriptionUZ())
                    .image(attachmentService.findAttachmentByHashId(teacherPayload.getHashId()))
                    .courses(courseService.getCourse(teacherPayload.getCourses()))
                    .build();
            teacherRepository.save(teacher);
            return result.success(teacher);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result findById(Long id) {
        try {
            return result.success(teacherRepository.findById(id).orElseThrow(()->new ResourceNotFound("teacher","id",id)));
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }
    @Override
    public Result editTeacher(Long id, TeacherPayload teacherPayload) {
        try {
            Teacher teacher=teacherRepository.findById(id).orElseThrow();
            teacher.setDescriptionUZ(teacherPayload.getDescriptionUZ());
            teacher.setFullName(teacherPayload.getFullName());
            teacher.setDescriptionRU(teacherPayload.getDescriptionRU());
            teacher.setImage(attachmentService.findAttachmentByHashId(teacherPayload.getHashId()));
            teacher.setCourses(courseService.getCourse(teacherPayload.getCourses()));
            teacherRepository.save(teacher);
            return result.success(teacher);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result deleteTeacher(Long id) {
        try {
            Teacher teacher=teacherRepository.findById(id).orElseThrow(()->new ResourceNotFound("teacher","id",id));
            File file=new File(teacher.getImage().getLink());
            System.out.println(file.delete());
            teacherRepository.delete(teacher);
            return result.success("Teacher deleted");
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed  (e);
        }
    }

    @Override
    public Result getAll() {
        try {
            return result.success(teacherRepository.findAll());
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public List<Teacher> getAllByCourses(Long courseId) {
        try {
            return teacherRepository.getAllByCourses(courseId);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

}
