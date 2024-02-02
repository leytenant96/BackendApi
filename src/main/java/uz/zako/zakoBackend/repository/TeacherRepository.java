package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Teacher;
import uz.zako.zakoBackend.model.Result;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    @Query(value = "select t.* from teacher t inner join teacher_courses tc on t.id=tc.courses_id where tc.courses_id=?1",nativeQuery = true)
    List<Teacher> getAllByCourses(@Param("courseId") Long courseId);
}
