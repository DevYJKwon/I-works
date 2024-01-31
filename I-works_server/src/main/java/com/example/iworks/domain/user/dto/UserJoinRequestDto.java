package com.example.iworks.domain.user.dto;

import com.example.iworks.domain.user.domain.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJoinRequestDto {

    private int userDepartmentId; //부서
    private int userPositionCodeId; //직급 코드 아이디
    private String userEid; //사번
    private String userNameFirst; // 유저 이름
    private String userNameLast;
    private String userEmail; //유저 이메일
    private String userTel; //전화번호
    private String userAddress; //주소
    private String userGender; // 성별
    private LocalDateTime userCreatedAt = LocalDateTime.now(); // 가입일시
    private LocalDateTime userUpdatedAt = LocalDateTime.now(); // 회원 정보 수정 일시
    private String userRole; //권한

    public User toEntitiy(){
            return User.builder()
                    .userEid(userEid)
                    .userNameFirst(userNameFirst)
                    .userNameLast(userNameLast)
                    .userEmail(userEmail)
                    .userTel(userTel)
                    .userAddress(userAddress)
                    .userGender(userGender)
                    .userCreatedAt(userCreatedAt)
                    .userUpdatedAt(userUpdatedAt)
                    .build();
    }
}
