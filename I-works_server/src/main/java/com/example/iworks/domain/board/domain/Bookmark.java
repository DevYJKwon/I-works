package com.example.iworks.domain.board.domain;

import com.example.iworks.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Bookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookmarkId; //북마크 아이디

    @OneToOne
    @JoinColumn(name="board_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "bookmark_is_active")
    private Boolean bookmarkIsActive; //북마크 여부

    public void update() {
        this.bookmarkIsActive = !this.bookmarkIsActive;
    }

}
