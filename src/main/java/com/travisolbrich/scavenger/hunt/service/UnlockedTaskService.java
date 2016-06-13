package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.exception.BadRequestException;
import com.travisolbrich.scavenger.hunt.repository.UnlockedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UnlockedTaskService{

    @Autowired
    UnlockedTaskRepository unlockedTaskRepository;

    /**
     * Assign a given task to the user's team
     * @param taskEntity Task to assign
     * @param userEntity Team to assign to
     * @return UnlockedTaskEntity for the new assignment
     */
    UnlockedTaskEntity assignTaskToUser(TaskEntity taskEntity, UserEntity userEntity) {
        UnlockedTaskEntity unlockedTaskEntity = new UnlockedTaskEntity();

        unlockedTaskEntity.setTaskEntity(taskEntity);
        unlockedTaskEntity.setUserEntity(userEntity);
        unlockedTaskEntity.setTeamEntity(userEntity.getTeam());
        unlockedTaskEntity.setCheckpointEntity(taskEntity.getCheckpointEntity());
        unlockedTaskEntity.setUnlockedAt(new Timestamp(new Date().getTime()));

        return unlockedTaskRepository.save(unlockedTaskEntity);
    }

    /**
     * See if the team already unlocked a task at this checkpoint level
     * @param teamEntity Team we want to check against
     * @param checkpointEntity Checkpoint we want to check against
     * @return true if there is an unlocked task at this level
     */
    public boolean alreadyUnlockedCheckpoint(TeamEntity teamEntity, CheckpointEntity checkpointEntity) {
        UnlockedTaskEntity existing = unlockedTaskRepository.findOneByTeamEntity_TeamIdAndCheckpointEntity_CheckpointId(teamEntity.getTeamId(), checkpointEntity.getCheckpointId());

        return (existing != null);
    }

}
