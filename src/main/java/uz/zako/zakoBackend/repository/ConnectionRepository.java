package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
