package com.travisolbrich.scavenger.hunt.controller;

import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import com.travisolbrich.scavenger.hunt.model.Csrf;
import com.travisolbrich.scavenger.hunt.model.ProgressionStatus;
import com.travisolbrich.scavenger.hunt.model.User;
import com.travisolbrich.scavenger.hunt.service.ClueService;
import com.travisolbrich.scavenger.hunt.service.CurrentUserInfoService;
import com.travisolbrich.scavenger.hunt.service.TaskService;
import com.travisolbrich.scavenger.hunt.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/secured/users/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ClueService clueService;

    @Autowired
    TaskService taskService;

    @Autowired
    CurrentUserInfoService currentUserInfoService;

    /**
     * Register a new user for someone with the given token.
     * @return the newly instantiated User
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public User register() {
        User user = currentUserInfoService.getCurrentUser();
        UserEntity userEntity = userService.registerUser(user);

        return new ModelMapper().map(userEntity, User.class);
    }

    /**
     * Provide a csrf token to the user.
     * @param request The HTTP request itself
     * @return a CSRF token for the user
     */
    @RequestMapping(value = "csrf-token", method = RequestMethod.GET)
    public Csrf getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        Csrf csrf = new Csrf();
        csrf.setToken(token.getToken());
        return csrf;
    }

    @RequestMapping(value = "progressionStatus", params = {"userId"}, method = RequestMethod.GET)
    @PreAuthorize("@SecurityService.allowedUserAccess(#userId) or hasRole('ROLE_ADMIN')")
    public ProgressionStatus getProgressionStatus(@RequestParam("userId") Long userId) throws IOException {
        UserEntity userEntity = userService.getUserEntity(userId);
        return userService.getProgressionStatus(userEntity);
    }

    @RequestMapping(value = "progressionStatus", method = RequestMethod.GET)
    public ProgressionStatus getProgressionStatusForCurrentUser() throws IOException {
        UserEntity userEntity = currentUserInfoService.getCurrentUserEntity();
        return userService.getProgressionStatus(userEntity);
    }

    @RequestMapping(value = "whoami", method = RequestMethod.GET)
    public User whoami() throws IOException {
        User userEntity = currentUserInfoService.getCurrentRegisteredUser();
        return new ModelMapper().map(userEntity, User.class);
    }

}
