package uz.zako.zakoBackend.service;

import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.SocialMediaPayload;

public interface SocialMediaService {

    Result saveSocialMedia (SocialMediaPayload socialMediaPayload);
    Result deleteSocialMedia(Long id);
    Result editSocialMedia(Long id, SocialMediaPayload socialMediaPayload);
    Result getSocialMedia();
    Result findSocialMedia(Long id);


}
