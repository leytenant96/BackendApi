package uz.zako.zakoBackend.service;

import uz.zako.zakoBackend.entity.Teacher;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.TeacherPayload;

import java.util.List;

public interface TeacherService {
    Result saveTeacher(TeacherPayload teacherPayload);
    Result editTeacher(Long id, TeacherPayload teacherPayload);
    Result deleteTeacher(Long id);
    Result findById(Long id);
    Result getAll();
    List<Teacher> getAllByCourses(Long courseId);
}
