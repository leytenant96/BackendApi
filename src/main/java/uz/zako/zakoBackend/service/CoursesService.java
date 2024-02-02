package uz.zako.zakoBackend.service;

import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.CoursesPayload;

public interface CoursesService {
    Result saveCourse(CoursesPayload coursesPayload);

    Result deleteCourse(Long id);

    Result findById(Long id);

    Result getAll();

    Result editCourse(Long id, CoursesPayload coursesPayload);

}
