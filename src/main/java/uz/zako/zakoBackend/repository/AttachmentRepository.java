package uz.zako.zakoBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.zakoBackend.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    Attachment findAttachmentByHashId(String hashId);
}
