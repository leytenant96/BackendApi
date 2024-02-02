package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Register;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Long> {
    @Query(value = "select r.* from register r inner join register_courses rc on r.id=rc.register_id where rc.courses_id=?1", nativeQuery = true)
    List<Register> finAllByCourses(@Param("courses") Long courses);
}