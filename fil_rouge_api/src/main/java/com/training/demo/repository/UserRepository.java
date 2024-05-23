package com.training.demo.repository;

import com.training.demo.repository.entity.Tutorial;
import com.training.demo.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
