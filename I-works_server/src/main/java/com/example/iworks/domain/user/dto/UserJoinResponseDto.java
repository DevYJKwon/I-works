package com.example.iworks.domain.user.dto;

import com.example.iworks.domain.department.domain.Department;
import com.example.iworks.domain.user.domain.Status;
import com.example.iworks.global.model.entity.Code;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJoinResponseDto {
    private int userId; //유저 아이디

    private Department userDepartment; //부서
    private Code userPositionCode; //직급 코드 아이디
    private String userEid = "240001"; //사번
    private String userNameFirst = "work"; // 유저 이름
    private String userNameLast = "i";
    private String userEmail = "11111@naver.com"; //유저 이메일
    private String userPassword = "1234"; //비밀번호
    private String userTel; //전화번호
    private String userAddress; //주소
    private String userGender; // 성별
    private LocalDateTime userCreatedAt = LocalDateTime.now(); // 가입일시
    private LocalDateTime userUpdatedAt = LocalDateTime.now(); // 회원 정보 수정 일시
    private LocalDateTime userDeletedAt; // 탈퇴일시
    private Boolean userIsDeleted; //탈퇴여부
    private String userRole = "USER,ADMIN"; //권한
    private Status userStatus; // 상태
}
