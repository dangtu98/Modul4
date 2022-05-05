package com.codegym.controller;

import com.codegym.model.Post;
import com.codegym.service.PostService.IPostService;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Page<Post>> findAllPost(@RequestParam(name = "q") Optional<String> q,
                                                  Pageable pageable) {
        Page<Post> posts = (Page<Post>) postService.findAll(pageable);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (q.isPresent()){
         posts = postService.findByName(q.get(),pageable);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        Optional<Post> posts = postService.findById(id);
        if (!posts.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts.get(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        Post post1 = new Post(post.getId(), post.getName(), post.getTime(), post.getCategory());
        return new ResponseEntity<>(postService.save(post1), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Post oldPost = postOptional.get();
        oldPost.setName(post.getName());
        oldPost.setTime(post.getTime());
        oldPost.setCategory(post.getCategory());
        return new ResponseEntity<>(postService.save(oldPost), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        postService.remove(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

}
