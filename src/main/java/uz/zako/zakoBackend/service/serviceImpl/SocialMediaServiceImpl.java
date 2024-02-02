package uz.zako.zakoBackend.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.zako.zakoBackend.entity.SocialMedia;
import uz.zako.zakoBackend.exeption.ResourceNotFound;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.SocialMediaPayload;
import uz.zako.zakoBackend.repository.SocialMediaRepository;
import uz.zako.zakoBackend.service.SocialMediaService;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialMediaServiceImpl implements SocialMediaService {
private final SocialMediaRepository socialMediaRepository;
private final Result result;

@Override
public Result saveSocialMedia(SocialMediaPayload socialMediaPayload) {
    try {
        SocialMedia socialMedia=SocialMedia.builder()
                .telegramLink(socialMediaPayload.getTelegramLink())
                .instagramLink(socialMediaPayload.getInstagramLink())
                .youTubeLink(socialMediaPayload.getYouTubeLink())
                .build();
        socialMediaRepository.save(socialMedia);
        return result.success(socialMedia);
    }catch (Exception e){
        log.error(e.getMessage());
        return result.failed(e);
    }
}

    @Override
    public Result deleteSocialMedia(Long id) {
        try {
            SocialMedia socialMedia = socialMediaRepository.findById(id).orElseThrow();
            socialMediaRepository.delete(socialMedia);
            return result.success("socialMedia deleted");
        } catch (Exception e){
            log.error(e.getMessage());
            return result.failed  (e);
        }
    }

    @Override
    public Result editSocialMedia(Long id, SocialMediaPayload socialMediaPayload) {
        try{
            SocialMedia socialMedia =socialMediaRepository.findById(id).orElseThrow();
            if (socialMedia.getTelegramLink()!=(null)) socialMedia.setTelegramLink(socialMedia.getTelegramLink());
            if (socialMedia.getInstagramLink()!=(null)) socialMedia.setInstagramLink(socialMediaPayload.getInstagramLink());
            if (socialMediaPayload.getYouTubeLink()!=(null)) socialMedia.setYouTubeLink(socialMedia.getYouTubeLink());
            socialMediaRepository.save(socialMedia);
            return result.success(socialMedia);}
        catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result getSocialMedia() {
        try {
            return result.success(socialMediaRepository.findAll());
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result findSocialMedia(Long id) {
        try {
            SocialMedia socialMedia=socialMediaRepository.findById(id).orElseThrow(()->new ResourceNotFound("socialmedia","id",id));
            return result.success(socialMedia);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }


}
