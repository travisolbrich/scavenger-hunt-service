package com.travisolbrich.scavenger.hunt.repository;

import com.travisolbrich.scavenger.hunt.entity.CodeTagEntity;
import com.travisolbrich.scavenger.hunt.entity.UnlockedTaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnlockedTaskRepository extends CrudRepository<UnlockedTaskEntity, Long> {

    UnlockedTaskEntity findOneByTeamEntity_TeamIdAndCheckpointEntity_CheckpointId(Long teamId, Long checkpointId);
}