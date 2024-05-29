package com.codecrafters.companity.domain.post;

import com.codecrafters.companity.application.service.post.PostFactory;
import com.codecrafters.companity.config.mapper.CustomModelMapper;
import com.codecrafters.companity.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.codecrafters.companity.static_reference.PostStatic.*;
import static com.codecrafters.companity.static_reference.UserStatic.*;
import static org.assertj.core.api.Assertions.assertThat;

class PostFactoryTest {
    private PostFactory postFactory;
    @BeforeEach
    public void init(){
        CustomModelMapper mapper = new CustomModelMapper();
        postFactory = new PostFactory(mapper);
    }

    @Test
    void create() {
        //given
        Post requestPost = getDefaultPost();
        User user = getDefaultUser();

        //when
        Post newPost = postFactory.create(requestPost, user);

        //then
        //post
        assertThat(newPost.getId()).isNull();
        assertThat(newPost.getCity()).isEqualTo(CITY);
        assertThat(newPost.getContent()).isEqualTo(CONTENT);
        assertThat(newPost.getTitle()).isEqualTo(TITLE);
        assertThat(newPost.getLikeCount()).isEqualTo(0);

        //user
        User writer = newPost.getOwner();
        assertThat(writer.equals(user)).isTrue();
    }

    @Test
    void update() {
        //given
        User user = getDefaultUser();
        Post oldPost = postFactory.create(getDefaultPost(), user);
        Post newPost = Post.builder().title("update test").build();

        //when
        Post updatedPost = postFactory.update(oldPost, newPost);

        //then
        //post
        assertThat(updatedPost.getId()).isNull();
        assertThat(updatedPost.getCity()).isEqualTo(CITY);
        assertThat(updatedPost.getContent()).isEqualTo(CONTENT);
        assertThat(updatedPost.getTitle()).isEqualTo("update test");
        assertThat(updatedPost.getLikeCount()).isEqualTo(0);

        //user
        User writer = updatedPost.getOwner();
        assertThat(writer.equals(user)).isTrue();
    }

    private Post getDefaultPost(){
        return Post.builder()
                .title(TITLE)
                .city(CITY)
                .content(CONTENT)
                .sport(SPORT_TYPE)
                .build();
    }
    private User getDefaultUser(){
        return User.builder().userId(USER_ID).userName(USER_NAME).nickName(NICKNAME).build();
    }
}