package com.codecrafters.companity.domain.post;

import com.codecrafters.companity.domain.enumclass.City;
import com.codecrafters.companity.domain.enumclass.SportType;
import com.codecrafters.companity.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private User user;
    private Long id;
    private String title;
    private City city;
    private SportType sportType;
    private String content;
    private LocalDateTime localDateTime;
    private boolean recruit;
    private int likeCount;
    private List<Comment> comments;
}
