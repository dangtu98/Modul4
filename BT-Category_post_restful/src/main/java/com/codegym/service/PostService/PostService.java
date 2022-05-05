package com.codegym.service.PostService;

import com.codegym.model.Category;
import com.codegym.model.Post;
import com.codegym.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAllByCategory(Category category, Pageable pageable) {
        return postRepository.findAllByCategory(category,pageable);
    }

    @Override
    public Page<Post> findByName(String name, Pageable pageable) {
        return postRepository.findByNameContaining(name,pageable);
    }


}
