package com.travisolbrich.scavenger.hunt.repository;

import com.travisolbrich.scavenger.hunt.entity.UnlockedClueEntity;
import com.travisolbrich.scavenger.hunt.entity.UnlockedTaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnlockedClueRepository extends CrudRepository<UnlockedClueEntity, Long> {
    UnlockedClueEntity findOneByTeamEntity_TeamIdAndCheckpointEntity_CheckpointId(Long teamId, Long checkpointId);

    UnlockedClueEntity findOneByTeamEntity_TeamIdAndClueEntity_ClueId(Long teamId, Long clueId);
}