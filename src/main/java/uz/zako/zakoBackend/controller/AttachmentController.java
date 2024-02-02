package uz.zako.zakoBackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.zakoBackend.entity.Attachment;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.service.serviceImpl.AttachmentServiceImpl;

import java.net.MalformedURLException;
import java.net.URLEncoder;

@RequestMapping("/api/")
@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentServiceImpl attachmentService;

    @PostMapping("admin/save")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile multipartFile){
        Result result=attachmentService.saveAttachment(multipartFile);
        return ResponseEntity.status(result.isStatus()?200:403).body(result);
    }



    @GetMapping("auth/preview/{hashId}")
    public ResponseEntity<?> preview(@PathVariable String hashId) throws MalformedURLException {
        Attachment attachment = attachmentService.findAttachmentByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.EXPIRES, "inline; fileName=" + URLEncoder.encode(attachment.getFileName()))
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s",
                        attachment.getUploadPath(),
                        attachment.getHashId(),
                        attachment.getExtension())));
    }

    @GetMapping("auth/download/{hashId}")
    public ResponseEntity<?> download(@PathVariable String hashId) throws MalformedURLException {
        Attachment attachment = attachmentService.findAttachmentByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "file; fileName=" + URLEncoder.encode(attachment.getFileName()))
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s",
                        attachment.getUploadPath(),
                        attachment.getHashId(),
                        attachment.getExtension())));
    }
    @DeleteMapping("admin/delete/{hashId}")
    public ResponseEntity<?> deleteFile(@PathVariable String hashId){
        Result result=attachmentService.deleteAttachmentByHashId(hashId);
        return  ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
}
