package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.exception.BadRequestException;
import com.travisolbrich.scavenger.hunt.repository.CodeTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeTagService {

    @Autowired
    CodeTagRepository codeTagRepository;

    @Autowired
    UnlockedTaskService unlockedTaskService;

    @Autowired
    UnlockedClueService unlockedClueService;

    public CodeTagEntity getById(Long codeTagId) {
        return codeTagRepository.findOne(codeTagId);
    }

    /**
     * Unlock the task referenced by this code tag. Preauthorize already checked that the user can scan
     * this tag. We check for a valid key and make sure that a task at this checkpoint level has not
     * already been unlocked.
     * @param codeTagEntity Code tag that was scanned
     * @param key Key provided to unlock the task
     * @param userEntity User that scanned the tag
     * @return UnlockedTaskEntity for the newly unlocked task.
     */
    public UnlockedTaskEntity unlockTask(CodeTagEntity codeTagEntity, String key, UserEntity userEntity) {
        if(! codeTagEntity.isKeyValid(key)){
            throw new BadRequestException("WRONG_CODE");
        }

        if(unlockedTaskService.alreadyUnlockedCheckpoint(userEntity.getTeam(), codeTagEntity.getCheckpointEntity())) {
            throw new BadRequestException("ALREADY_UNLOCKED");
        }

        TaskEntity taskEntity = (TaskEntity) codeTagEntity.getRandomUnlockableEntity();
        return unlockedTaskService.assignTaskToUser(taskEntity, userEntity);
    }

    /**
     * The code tag is correct if the previous clue is assigned to our user, or if there is no
     * referencing clue (meaning this is the first tag).     *
     * @param codeTagEntity the code tag we want to check
     * @param userEntity the user that is trying to scan the tag
     * @return True if referencing clue is null or if the previous clue references this codeTag
     */
    public boolean userCanScanTag(CodeTagEntity codeTagEntity, UserEntity userEntity) {
        ClueEntity referencingClue = codeTagEntity.getReferencingClue();
        TeamEntity teamEntity = userEntity.getTeam();

        return referencingClue == null || unlockedClueService.alreadyUnlockedSpecific(teamEntity, referencingClue);

    }
}
