package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long> {
}
