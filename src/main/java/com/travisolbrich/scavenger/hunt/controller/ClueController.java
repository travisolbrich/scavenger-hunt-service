package com.travisolbrich.scavenger.hunt.controller;

import com.travisolbrich.scavenger.hunt.entity.ClueEntity;
import com.travisolbrich.scavenger.hunt.entity.TaskEntity;
import com.travisolbrich.scavenger.hunt.entity.UnlockedClueEntity;
import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import com.travisolbrich.scavenger.hunt.model.Clue;
import com.travisolbrich.scavenger.hunt.model.Task;
import com.travisolbrich.scavenger.hunt.model.User;
import com.travisolbrich.scavenger.hunt.service.CodeTagService;
import com.travisolbrich.scavenger.hunt.service.CurrentUserInfoService;
import com.travisolbrich.scavenger.hunt.service.ClueService;
import com.travisolbrich.scavenger.hunt.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/secured/clues/")
public class ClueController {

    @Autowired
    UserService userService;

    @Autowired
    ClueService clueService;

    @Autowired
    CodeTagService codeTagService;

    @Autowired
    CurrentUserInfoService currentUserInfoService;

    /**
     * Get clues for a single user. Authenticated to the user.
     * @param userId the user we want to access
     * @return a list of clues pertaining to this user
     * @throws IOException
     */
    @RequestMapping(value = "", params = {"userId"}, method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.allowedUserAccess(#userId) or hasRole('ROLE_ADMIN')")
    public Iterable<Clue> getCluesForUser(@RequestParam("userId") Long userId) throws IOException {
        UserEntity userEntity = userService.getUserEntity(userId);
        Iterable<ClueEntity> clueEntityList = clueService.getCluesForUser(userEntity);

        Type listType = new TypeToken<List<Clue>>(){}.getType();
        return new ModelMapper().map(clueEntityList, listType);
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Clue getLatestClueForCurrentUser() throws IOException {
        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();
        ClueEntity clueEntity = clueService.getLatestClueForUser(userEntity);

        return new ModelMapper().map(clueEntity, Clue.class);
    }

    /**
     * Get a single clue. User must be admin or must have unlocked this clue.
     * @param clueId the ID of the clue we want to view
     * @return the clue
     * @throws IOException
     */
    @RequestMapping(value = "{clueId}", method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.hasUnlockedClue(#clueId) or hasRole('ROLE_ADMIN')")
    public Clue getClue(@PathVariable("clueId") Long clueId) throws IOException {
        ClueEntity clueEntity = clueService.getClue(clueId);

        return new ModelMapper().map(clueEntity, Clue.class);
    }
}
