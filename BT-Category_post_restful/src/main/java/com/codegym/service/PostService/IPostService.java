package com.codegym.service.PostService;

import com.codegym.model.Category;
import com.codegym.model.Post;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService extends IGeneralService<Post> {
    Page<Post> findAllByCategory(Category category, Pageable pageable);

    Page<Post> findByName(String name,Pageable pageable);

}
