package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.repository.UnlockedClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UnlockedClueService{

    @Autowired
    UnlockedClueRepository unlockedClueRepository;

    /**
     * Assign a given clue to the user's team
     * @param clueEntity Clue to assign
     * @param userEntity Team to assign to
     * @return UnlockedClueEntity for the new assignment
     */
    UnlockedClueEntity assignClueToUser(ClueEntity clueEntity, UserEntity userEntity) {
        UnlockedClueEntity unlockedClueEntity = new UnlockedClueEntity();

        unlockedClueEntity.setClueEntity(clueEntity);
        unlockedClueEntity.setUserEntity(userEntity);
        unlockedClueEntity.setTeamEntity(userEntity.getTeam());
        unlockedClueEntity.setCheckpointEntity(clueEntity.getCheckpointEntity());
        unlockedClueEntity.setUnlockedAt(new Timestamp(new Date().getTime()));

        return unlockedClueRepository.save(unlockedClueEntity);
    }

    /**
     * See if a specific ClueEntity has already been unlocked by a team
     * @param teamEntity Team we want to check against
     * @param clueEntity Clue we want to check against
     * @return true if the team unlocked this specific clue
     */
    boolean alreadyUnlockedSpecific(TeamEntity teamEntity, ClueEntity clueEntity) {
        long teamId = teamEntity.getTeamId();
        Long clueId = clueEntity.getClueId();
        UnlockedClueEntity existing = unlockedClueRepository.findOneByTeamEntity_TeamIdAndClueEntity_ClueId(teamId, clueId);

        return (existing != null);
    }

    /**
     * See if the team already unlocked a clue at this checkpoint level
     * @param teamEntity Team we want to check against
     * @param checkpointEntity Checkpoint we want to check against
     * @return true if there is an unlocked clue at this level
     */
    boolean alreadyUnlockedCheckpoint(TeamEntity teamEntity, CheckpointEntity checkpointEntity) {
        UnlockedClueEntity existing = unlockedClueRepository.findOneByTeamEntity_TeamIdAndCheckpointEntity_CheckpointId(teamEntity.getTeamId(), checkpointEntity.getCheckpointId());

        return (existing != null);
    }
}
