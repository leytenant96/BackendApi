package uz.zako.zakoBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.zako.zakoBackend.entity.User;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.LoginPayload;
import uz.zako.zakoBackend.repository.UserRepository;
import uz.zako.zakoBackend.security.JwtTokenProvider;
import uz.zako.zakoBackend.service.serviceImpl.SocialMediaServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;
    private final SocialMediaServiceImpl socialMediaService;

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody LoginPayload loginPayload){

        User user=userRepository.findByUserName(loginPayload.getUserName());

        if (user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPayload.getUserName(),loginPayload.getPassword()));

//        create
        String token=jwtTokenProvider.createToken(user.getUserName(),user.getRoleList());
        if(token==null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nimadir xato");

        Map<String,Object> result=new HashMap<>();
        result.put("status",true);
        result.put("userName",user.getUserName());
        result.put("token",token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @GetMapping("/social-media")
    public ResponseEntity<?> getSocialMedia() {
        Result result = socialMediaService.getSocialMedia();
        return ResponseEntity.status(result.isStatus() ? 200 : 400).body(result);
    }

}
