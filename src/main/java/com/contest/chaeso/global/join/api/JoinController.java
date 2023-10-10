package com.contest.chaeso.global.join.api;

import com.contest.chaeso.domain.users.language.api.dto.res.ResponseOAuthUserInfoDto;
import com.contest.chaeso.domain.users.users.api.dto.req.RequestUserSignUpDto;
import com.contest.chaeso.domain.users.users.application.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@Api(value = "JoinController")
@RequestMapping("/api/join")
public class JoinController {

    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PostMapping("/membership")
    public ResponseEntity join(@RequestBody RequestUserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "소셜 로그인", notes = "소셜 로그인 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "회원 정보가 올바르지 않습니다.")
    })
    @GetMapping("/login/oauth/{socialType}")
    public ResponseOAuthUserInfoDto oauthLogin(@PathVariable(name = "socialType") String socialType, @RequestParam String code) {
        return userService.socialUserInfo(socialType, code);
    }

    @ApiOperation(value = "닉네임 중복검사", notes = "닉네임의 중복여부를 검증합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "fail")
    })
    @GetMapping("/duplicate-check")
    public ResponseEntity CheckForDuplicateNickname(String nickname) {
        userService.CheckForDuplicateNickname(nickname);
        return ResponseEntity.ok().build();
    }


}
