package com.codecrafters.companity.mock.repository;

import com.codecrafters.companity.application.out.persistence.PostRepository;
import com.codecrafters.companity.application.out.persistence.PostCriteria;
import com.codecrafters.companity.domain.post.Post;
import com.codecrafters.companity.domain.post.PostForUpdate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostInMemoryImpl implements PostRepository {
    public final Map<Long, Post> repository = new ConcurrentHashMap<>();
    private final AtomicLong keyCreator = new AtomicLong();
    @Override
    public Post add(Post post) {
        Post build = Post.builder().id(keyCreator.getAndIncrement())
                .comments(post.getComments())
                .createdAt(post.getCreatedAt())
                .title(post.getTitle())
                .owner(post.getOwner())
                .recruit(post.getRecruit())
                .city(post.getCity())
                .content(post.getContent())
                .sport(post.getSport())
                .likeCount(post.getLikeCount()).build();
        repository.put(build.getId(), build);
        return post;
    }

    @Override
    public Post getById(Long id) {
        if(!repository.containsKey(id)) throw new IllegalArgumentException();
        return repository.get(id);
    }

    @Override
    public Post update(PostForUpdate postForUpdate) {
        Post post = repository.get(postForUpdate.getId());
        Post build = Post.builder().id(post.getId())
                .comments(post.getComments())
                .createdAt(post.getCreatedAt())
                .title(postForUpdate.getTitle())
                .owner(post.getOwner())
                .recruit(post.getRecruit())
                .city(postForUpdate.getCity())
                .content(postForUpdate.getContent())
                .sport(postForUpdate.getSport())
                .likeCount(post.getLikeCount()).build();
        repository.put(post.getId(), build);
        return build;
    }

    @Override
    public List<Post> findBySportAndCityAndRecruitOrderByRecentDateOrFavorite(PostCriteria postCriteria) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
