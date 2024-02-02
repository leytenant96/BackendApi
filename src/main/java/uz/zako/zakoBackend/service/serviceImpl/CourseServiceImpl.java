package uz.zako.zakoBackend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.zako.zakoBackend.entity.Courses;
import uz.zako.zakoBackend.exeption.ResourceNotFound;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.CoursesPayload;
import uz.zako.zakoBackend.repository.CoursesRepository;
import uz.zako.zakoBackend.service.CoursesService;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CoursesService {
    private final CoursesRepository coursesRepository;
    private final AttachmentServiceImpl attachmentService;
    private final Result result;

    @Override
    public Result saveCourse(CoursesPayload coursePayload) {
        try {
            Courses courses = Courses.builder()
                    .name(coursePayload.getName())
                    .price(coursePayload.getPrice())
                    .bodyUZ(coursePayload.getBodyUZ())
                    .bodyRU(coursePayload.getBodyRU())
                    .duration(coursePayload.getDuration())
                    .image(attachmentService.findAttachmentByHashId(coursePayload.getHashId()))
                    .build();
            coursesRepository.save(courses);
            return result.success(courses);
        } catch (Exception e) {
            return result.failed(e);
        }
    }

    @Override
    public Result deleteCourse(Long id) {
        try {
            Courses courses = findByCourseId(id);
            File file = new File(courses.getImage().getLink());
            coursesRepository.delete(courses);
            return result.success("Course deleted");
        } catch (Exception e) {
            return result.failed(e);
        }
    }

    @Override
    public Result findById(Long id) {
        try {
            return result.success(findByCourseId(id));
        } catch (Exception e) {
            return result.failed(e);
        }
    }

    @Override
    public Result getAll() {
        try {
            return result.success(coursesRepository.findAll(Sort.by("cratedAt")));
        } catch (Exception e) {
            return result.failed(e);
        }
    }

    @Override
    public Result editCourse(Long id, CoursesPayload coursePayload) {
        try {
            Courses courses = findByCourseId(id);
            if (coursePayload.getName() != (null)) courses.setName(coursePayload.getName());
            if (coursePayload.getBodyRU() != null) courses.setBodyRU(coursePayload.getBodyRU());
            if (coursePayload.getBodyUZ() != null) courses.setBodyUZ(coursePayload.getBodyUZ());
            if (coursePayload.getHashId() != null)
                courses.setImage(attachmentService.findAttachmentByHashId(coursePayload.getHashId()));
            if (coursePayload.getDuration() != null) courses.setDuration(coursePayload.getDuration());
            if (coursePayload.getPrice() != null) courses.setPrice(courses.getPrice());
            coursesRepository.save(courses);
            return result.success(courses);
        } catch (Exception e) {
            return result.failed(e);
        }
    }


    public List<Courses> getCourse(List<Long> courseId) {
        return courseId.stream().map(this::findByCourseId).collect(Collectors.toList());
    }


    public Courses findByCourseId(Long id) {
        return coursesRepository.findById(id).orElseThrow(() -> new ResourceNotFound("course", "id", id));
    }

}
