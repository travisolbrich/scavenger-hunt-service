package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.exception.BadRequestException;
import com.travisolbrich.scavenger.hunt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UnlockedClueService unlockedClueService;

    /**
     * Unlock a clue when a task is answered correctly.
     * @param taskEntity The task that we want to check against
     * @param key User's response to the question in the task
     * @param userEntity The user that answered the question
     * @return UnlockedClueEntity for the newly unlocked clue
     */
    public UnlockedClueEntity unlockClue(TaskEntity taskEntity, String key, UserEntity userEntity) {
        key = key.trim().toLowerCase();

        if(! taskEntity.isKeyValid(key)){
            throw new BadRequestException("WRONG_ANSWER");
        }

        if(unlockedClueService.alreadyUnlockedCheckpoint(userEntity.getTeam(), taskEntity.getCheckpointEntity())) {
            throw new BadRequestException("ALREADY_UNLOCKED");
        }

        ClueEntity clueEntity = (ClueEntity) taskEntity.getRandomUnlockableEntity();
        return unlockedClueService.assignClueToUser(clueEntity, userEntity);
    }

    public TaskEntity getLatestTaskForUser(UserEntity userEntity) {
        List<TaskEntity> tasksForUser = this.getTasksForUser(userEntity);

        if(tasksForUser.size() == 0) {
            throw new BadRequestException("NO_UNLOCKS");
        }

        return tasksForUser.get(0);
    }

    public List<TaskEntity> getTasksForUser(UserEntity userEntity) {
        return userEntity.getTeam().getTaskEntityList();
    }

    public TaskEntity getTask(Long taskId) {
        return taskRepository.findOne(taskId);
    }
}
