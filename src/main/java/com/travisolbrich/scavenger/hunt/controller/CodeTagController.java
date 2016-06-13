package com.travisolbrich.scavenger.hunt.controller;

import com.travisolbrich.scavenger.hunt.entity.CodeTagEntity;
import com.travisolbrich.scavenger.hunt.entity.UnlockedTaskEntity;
import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import com.travisolbrich.scavenger.hunt.exception.BadRequestException;
import com.travisolbrich.scavenger.hunt.model.Task;
import com.travisolbrich.scavenger.hunt.model.User;
import com.travisolbrich.scavenger.hunt.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/secured/codeTags/")
@PreAuthorize("hasRole('ROLE_USER')")
public class CodeTagController {

    @Autowired
    UserService userService;

    @Autowired
    CodeTagService codeTagService;

    @Autowired
    UnlockedTaskService unlockedTaskService;

    @Autowired
    CurrentUserInfoService currentUserInfoService;

    /**
     * URL visited when a code tag is scanned. Unlocks a random task for the tag's checkpoint. User must
     * be authorized on the clue that references this.
     * @param codeTagId ID of the tag being scanned.
     * @param unlockCode Key to unlock the task
     * @return Task that the code tag unlocked.
     * @throws IOException
     */
    @RequestMapping(value = "{codeTagId}/scan/{unlockCode}", method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.canScanCodeTag(#codeTagId)")
    public Task unlockTask(@PathVariable("codeTagId") Long codeTagId, @PathVariable("unlockCode") String unlockCode) throws IOException {
        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();
        CodeTagEntity codeTagEntity = codeTagService.getById(codeTagId);

        UnlockedTaskEntity unlockedTaskEntity = codeTagService.unlockTask(codeTagEntity, unlockCode, userEntity);

        return new ModelMapper().map(unlockedTaskEntity.getTaskEntity(), Task.class);
    }

    @RequestMapping(value = "{codeTagId}/isUnlocked", method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.canScanCodeTag(#codeTagId)")
    public boolean hasTagBeenUnlocked(@PathVariable("codeTagId") Long codeTagId) {
        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();
        CodeTagEntity codeTagEntity = codeTagService.getById(codeTagId);

        return unlockedTaskService.alreadyUnlockedCheckpoint(userEntity.getTeam(), codeTagEntity.getCheckpointEntity());
    }
}
