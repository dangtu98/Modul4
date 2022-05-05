package com.codegym.repository;

import com.codegym.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {


    @Modifying
    @Query(value = "call delete_category(?1)",nativeQuery = true )
    void deleteCategory(Long id);


}
