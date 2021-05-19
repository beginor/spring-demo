package com.example.springdemo.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    
    List<AppUser> findByNormalizedUserName(String userName);
}
