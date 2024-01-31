package com.example.iworks.domain.user.dto;

import com.example.iworks.domain.department.domain.Department;
import com.example.iworks.domain.user.domain.User;
import com.example.iworks.global.model.entity.Code;
import lombok.Data;

@Data
public class UserJoinResponseDto {
    private int userId; //유저 아이디

    private Department userDepartment; //부서
    private Code userPositionCode; //직급 코드 아이디
    private String userEid = "240001"; //사번


    public  UserJoinResponseDto(User user){
        this.userDepartment = user.getUserDepartment();
        this.userPositionCode = user.getUserPositionCode();
        this.userEid = user.getUserEid();

    }
}
