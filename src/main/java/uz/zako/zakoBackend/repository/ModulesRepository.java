package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Courses;
import uz.zako.zakoBackend.entity.Modules;

import java.util.List;

@Repository
public interface ModulesRepository extends JpaRepository<Modules,Long> {
    List<Modules> findAllByCourseIdOrderById(Long courseId);
}
