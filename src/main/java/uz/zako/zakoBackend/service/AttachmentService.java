package uz.zako.zakoBackend.service;


import org.springframework.web.multipart.MultipartFile;
import uz.zako.zakoBackend.entity.Attachment;
import uz.zako.zakoBackend.model.Result;

public interface AttachmentService {
    Result saveAttachment(MultipartFile multipartFile);
    Attachment findAttachmentByHashId(String hashId);  // public
    Result deleteAttachmentByHashId(String hashId);
}
