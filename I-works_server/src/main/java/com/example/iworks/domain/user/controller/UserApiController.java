package com.example.iworks.domain.user.controller;

import com.example.iworks.domain.department.repository.DepartmentRepository;
import com.example.iworks.domain.user.domain.User;
import com.example.iworks.domain.user.dto.UserJoinRequestDto;
import com.example.iworks.domain.user.dto.UserJoinResponseDto;
import com.example.iworks.domain.user.repository.UserRepository;
import com.example.iworks.global.model.Response;
import com.example.iworks.global.model.repository.CodeRepository;
import com.example.iworks.global.util.JwtProvider;
import com.example.iworks.global.util.RandomPasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final CodeRepository codeRepository;
    private final Response response;
    private final RandomPasswordUtil randomPasswordUtil;
    private  final JwtProvider jwtProvider;

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> join(@RequestBody UserJoinRequestDto dto){
        System.out.println("join start");
        if (userRepository.findByUserEid(dto.getUserEid())!=null){
            return response.handleFail("이미 존재하는 계정입니다.");
        }

        int deptId = dto.getUserDepartmentId();
        int posCodeId = dto.getUserPositionCodeId();
        System.out.println(dto);
        User user = dto.toEntitiy();
        user.setDepartment(departmentRepository.findByDepartmentId(deptId));
        user.setUserPositionCode(codeRepository.findCodeByCodeId(posCodeId));
        System.out.println("selected code:"+user.getUserPositionCode().getCodeName());
        if(user.getUserDepartment() == null || user.getUserPositionCode() == null){
            return response.handleFail("잘못된 값 입력");
        }
        ArrayList<String> roleList = new ArrayList<>();
        if(posCodeId == 6){
            roleList.add("ROLE_ADMIN");
        }else if(posCodeId==7){
            roleList.add("ROLE_CEO");
        }else if(posCodeId==8){
            roleList.add("ROLE_LEADER");
        } else{
            roleList.add("ROLE_EMPLOYEE");
        }
        int length = (int) (Math.random() * (12 - 8 + 1)) +8; // 8~12 길이
        String password = randomPasswordUtil.getRandomPassword(length);
        user.setUserPassword(bCryptPasswordEncoder.encode(password));
        user.setRoleList(roleList);
        userRepository.save(user);
        Map<String, Object> data = new HashMap<>();
        data.put("message","회원가입 성공!");
        data.put("data",password);
        data.put("user",new UserJoinResponseDto(user));
        return response.handleSuccess(data);
    }

    @GetMapping("/mypage")
    public ResponseEntity<Map<String,Object>> getProfile(@RequestHeader("Authorization") String token){
        String eid = jwtProvider.getUserEid(token);
        System.out.println("token eid : "+eid);
        User user= userRepository.findByUserEid(eid);
        if(user != null){
            return response.handleSuccess(user);
        }
        return response.handleFail("couldn't find user with "+eid);
    }

    @PutMapping("/mypage")
    @Transactional
    public ResponseEntity<Map<String,Object>> updateProfile(@RequestHeader("Authorization") String token,@RequestBody User user){
        User origin = userRepository.findByUserEid(jwtProvider.getUserEid(token));
        System.out.println("origin: " + origin);
        if(origin != null){

            if(user.getUserPassword() != null){
                origin.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
            }

            if(user.getUserAddress()!=null){
                origin.setUserAddress(user.getUserAddress());
            }

            if(user.getUserEmail()!=null){
                origin.setUserEmail(user.getUserEmail());
            }

            if(user.getUserTel() !=null){
                origin.setUserTel(user.getUserTel());
            }
            return response.handleSuccess(origin);
        }

        return response.handleFail("couldn't find user with "+user.toString());
    }

}