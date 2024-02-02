package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Role;
import uz.zako.zakoBackend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
