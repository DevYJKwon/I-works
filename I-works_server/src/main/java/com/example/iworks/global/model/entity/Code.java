package com.example.iworks.global.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "code")
@Getter @Setter
@EqualsAndHashCode
@Builder @AllArgsConstructor @NoArgsConstructor
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id", nullable = false)
    private Integer codeId; // 코드 아이디

    @Column(name = "code_name", length = 30, nullable = false)
    private String codeName; // 코드명

    @Builder.Default
    @Column(name = "code_is_use", nullable = false)
    private boolean codeIsUse = true; // 코드 사용 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_group_id", nullable = false)
    private CodeGroup codeCodeGroup; // 코드 그룹 아이디 (외래키)

    public void setCodeGroup(CodeGroup codeGroup){
        this.codeCodeGroup = codeGroup;
        if (codeGroup.getCodeGroupCodes() != this){
            codeGroup.setCodes(this);
        }
    }
}
