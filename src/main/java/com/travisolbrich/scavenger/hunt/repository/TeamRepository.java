package com.travisolbrich.scavenger.hunt.repository;

import com.travisolbrich.scavenger.hunt.entity.TeamEntity;
import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Long> {
    TeamEntity findOneByName(String name);
}