package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SecurityService")
public class SecurityService {

    @Autowired
    CurrentUserInfoService currentUserInfoService;

    @Autowired
    TaskService taskService;

    @Autowired
    ClueService clueService;

    @Autowired
    CodeTagService codeTagService;

    @Autowired
    UserService userService;

    @Autowired
    UnlockedClueService unlockedClueService;

    public boolean isRegistered() {
        User user = currentUserInfoService.getCurrentUser();

        // Get the user ID from the database
        UserEntity userEntity = userService.getUserEntity(user);
        return (userEntity != null);
    }

    public boolean allowedUserAccess(Long userId) {
        User currentRegisteredUser = currentUserInfoService.getCurrentRegisteredUser();

        return currentRegisteredUser.getUserId() == userId;
    }

    public boolean hasUnlockedTask(String taskIdString) {
        User currentRegisteredUser = currentUserInfoService.getCurrentRegisteredUser();

        TaskEntity taskEntity = taskService.getTask(Long.valueOf(taskIdString));

        if (taskEntity == null) {
            return false;
        }

        List<UnlockedTaskEntity> unlockedTaskEntityList = taskEntity.getUnlockedTaskEntityList();

        for (UnlockedTaskEntity unlockedTaskEntity : unlockedTaskEntityList) {
            Long currentUserTeamId = currentUserInfoService.getCurrentRegisteredUser().getTeam().getTeamId();

            if (currentUserTeamId == unlockedTaskEntity.getTeamEntity().getTeamId()) {
                return true;
            }
        }

        return false;
    }


    public boolean hasUnlockedClue(Long clueId) {
        UserEntity currentRegisteredUser = currentUserInfoService.getCurrentUserEntity();
        TeamEntity teamEntity = currentRegisteredUser.getTeam();
        ClueEntity clueEntity = clueService.getClue(clueId);

        if (clueEntity == null) {
            return false;
        }

        return unlockedClueService.alreadyUnlockedSpecific(teamEntity, clueEntity);
    }

    public boolean canScanCodeTag(Long codeTagId) {
        CodeTagEntity codeTagEntity = codeTagService.getById(codeTagId);

        if (codeTagEntity == null) {
            return false;
        }

        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();

        return codeTagService.userCanScanTag(codeTagEntity, userEntity);
    }

}
