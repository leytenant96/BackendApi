package uz.zako.zakoBackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.SocialMediaPayload;
import uz.zako.zakoBackend.service.SocialMediaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/social-media")
public class SocialMediaController {
    private final SocialMediaService socialMediaService;

    @PostMapping("/save")
    public ResponseEntity<?> addSocialMedia(@RequestBody SocialMediaPayload socialMediaPayload) {
        Result result = socialMediaService.saveSocialMedia(socialMediaPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 400).body(result);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editSocialMedia(@RequestBody SocialMediaPayload socialMediaPayload, @PathVariable Long id) {
        Result result = socialMediaService.editSocialMedia(id, socialMediaPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSocialmedia(@PathVariable Long id) {
        Result result = socialMediaService.deleteSocialMedia(id);
        return ResponseEntity.status(result.isStatus() ? 200 : 400).body(result);
    }

}
