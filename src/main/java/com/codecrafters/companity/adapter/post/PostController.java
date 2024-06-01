package com.codecrafters.companity.adapter.post;

import com.codecrafters.companity.adapter.post.dto.request.RequestForCreatingPost;
import com.codecrafters.companity.adapter.post.dto.request.RequestForUpdatingPost;
import com.codecrafters.companity.application.out.persistence.PostCriteria;
import com.codecrafters.companity.adapter.post.dto.response.ResponsePost;
import com.codecrafters.companity.application.in.post.PostUseCase;
import com.codecrafters.companity.application.out.persistence.PostRepository;
import com.codecrafters.companity.domain.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.codecrafters.companity.adapter.post.mapper.PostMapperForController.POST_MAPPER_FOR_CONTROLLER;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostUseCase postUseCase;
    private final PostRepository postRepository;
    @PostMapping
    public ResponseEntity<ResponsePost> add(@RequestBody RequestForCreatingPost requestPost){
        Post result = postUseCase.add(requestPost.toPostCreateDto());
        ResponsePost responsePost = POST_MAPPER_FOR_CONTROLLER.toResponsePost(result);
        return new ResponseEntity<>(responsePost, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponsePost> update(@RequestBody RequestForUpdatingPost requestPost){
        Post result = postUseCase.update(requestPost.toPostUpdateDto());
        log.info("result : {}", result);
        ResponsePost responsePost = POST_MAPPER_FOR_CONTROLLER.toResponsePost(result);
        return new ResponseEntity<>(responsePost, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponsePost>> getAll(@RequestBody PostCriteria criteria){
        //TODO need to implement
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePost> getDetail(@PathVariable("id") Long id){
        ResponsePost result = POST_MAPPER_FOR_CONTROLLER.toResponsePost(postRepository.getById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        postRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
