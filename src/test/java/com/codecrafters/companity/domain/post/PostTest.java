package com.codecrafters.companity.domain.post;

import com.codecrafters.companity.domain.enumclass.City;
import com.codecrafters.companity.domain.enumclass.SportType;
import com.codecrafters.companity.domain.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PostTest {

    @Test
    void createNewPost() {
        String title = "Test Title";
        City city = City.Seoul;
        String content = "Test Content";
        SportType sportType = SportType.Soccer;
        String userId = "shtjdrb";
        String nickName = "노성규";

        Post requestPost = Post.builder()
                .title(title)
                .city(city)
                .content(content)
                .sportType(sportType)
                .build();
        User user = User.builder().userId(userId).nickName(nickName).build();
        LocalDate now = LocalDate.now();
        Post newPost = requestPost.createNewPost(user, now);

        assertThat(newPost.getId()).isNull();
        assertThat(newPost.getCity()).isEqualTo(city);
        assertThat(newPost.getComments()).isNull();
        assertThat(newPost.getContent()).isEqualTo(content);
        assertThat(newPost.getTitle()).isEqualTo(title);
        assertThat(newPost.getLikeCount()).isEqualTo(0);
        assertThat(newPost.getUser()).isEqualTo(user);
        assertThat(newPost.getLocalDate()).isEqualTo(now);
    }
}