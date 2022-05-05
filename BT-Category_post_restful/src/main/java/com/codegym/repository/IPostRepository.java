package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findAllByCategory(Category category, Pageable pageable);

    Page<Post> findByNameContaining(String name,Pageable pageable);


}
