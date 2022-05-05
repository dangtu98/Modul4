package com.codegym.repository;

import com.codegym.model.SmartPhone;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartPhoneRepository extends PagingAndSortingRepository<SmartPhone ,Long> {
}
