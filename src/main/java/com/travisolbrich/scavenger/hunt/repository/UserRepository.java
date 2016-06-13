package com.travisolbrich.scavenger.hunt.repository;

import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    public UserEntity findOneByAuth0Id(String auth0Id);
}