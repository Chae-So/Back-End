package com.contest.chaeso.global.oauth2.userinfo;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseOAuthUserInfoDto;
import com.contest.chaeso.domain.users.users.domain.SocialType;
import com.contest.chaeso.domain.users.users.domain.Users;
import com.contest.chaeso.domain.users.users.domain.repository.UsersRepository;
import com.contest.chaeso.global.exception.CustomException;
import com.contest.chaeso.global.exception.ErrorCode;
import com.contest.chaeso.global.join.api.dto.req.RequestOAuthUserInfoDto;
import com.contest.chaeso.global.jwt.service.JwtService;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GoogleSocialLogin implements OAuthLogin {

    private final JwtService jwtService;
    private final UsersRepository usersRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    Optional<Users> googleUser;
    String accessToken = "";
    String refreshToken = "";
    SocialType socialType = SocialType.GOOGLE;
    String socialId = "";
    String email = "";
    String nickname="";
    Long couponCount;

    /**
     * 인가 코드를 통해 AccessToken 얻기
     */
    @Override
    public String getAccessToken(String code){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String,String> body = new LinkedMultiValueMap<>();

            body.add("grant_type","authorization_code");
            body.add("code" ,code);
            body.add("client_id", clientId);
            body.add("client_secret",clientSecret);
            body.add("redirect_uri" ,redirectUri);

            RestTemplate rt = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body,headers);
            ResponseEntity<String> response = rt.exchange(
                    "https://oauth2.googleapis.com/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject tokenResponse   = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) tokenResponse.get("access_token");

        } catch (ParseException e) {
            throw new CustomException(ErrorCode.GOOGLE_LOGIN_FAILURE);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.GOOGLE_LOGIN_FAILURE);
        }

        return accessToken;
    }

    /**
     * AccessToken으로 사용자 정보 얻기
     */
    @Override
    public ResponseOAuthUserInfoDto getUserInfo(String code) {

        accessToken = getAccessToken(code);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            RestTemplate rt = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = rt.exchange(
                    "https://www.googleapis.com/oauth2/v1/userinfo",
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );


            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            socialId = String.valueOf(jsonObj.get("id"));
            email = String.valueOf(jsonObj.get("email"))+"[" +socialType +"]";
            nickname = String.valueOf(jsonObj.get("name")) + (int)((Math.random() * 8999) + 1000);
            accessToken = jwtService.createAccessToken(email);
            refreshToken = jwtService.createRefreshToken();


            if(usersRepository.findBySocialId(socialId).isPresent()){
                googleUser = usersRepository.findBySocialId(socialId);
                googleUser.get().updateRefreshToken(refreshToken,accessToken);
                usersRepository.flush();
            }
            else{
                RequestOAuthUserInfoDto userInfo = new RequestOAuthUserInfoDto(socialId,socialType,email,nickname,accessToken,refreshToken);
                googleUser = Optional.ofNullable(userSave(userInfo));
            }


        } catch (ParseException e) {
            throw new CustomException(ErrorCode.GOOGLE_LOGIN_FAILURE);
        }catch (Exception e){
            throw new CustomException(ErrorCode.GOOGLE_LOGIN_FAILURE);
        }

        return ResponseOAuthUserInfoDto.getUserInfo(googleUser);
    }

    /**
     * 유저 정보 저장
     */
    @Override
    public Users userSave(RequestOAuthUserInfoDto userInfo){

        Users users = userInfo.toEntity(userInfo);

        if(!usersRepository.findBySocialId(users.getSocialId()).isPresent()){
            usersRepository.save(users);
        }
        return users;
    }
}
