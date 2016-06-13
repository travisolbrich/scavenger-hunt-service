package com.travisolbrich.scavenger.hunt.repository;

import com.travisolbrich.scavenger.hunt.entity.ClueEntity;
import com.travisolbrich.scavenger.hunt.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

}