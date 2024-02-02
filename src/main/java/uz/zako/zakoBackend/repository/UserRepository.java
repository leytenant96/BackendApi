package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
