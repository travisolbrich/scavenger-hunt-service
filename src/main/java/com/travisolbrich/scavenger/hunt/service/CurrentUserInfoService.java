package com.travisolbrich.scavenger.hunt.service;

import com.auth0.spring.security.auth0.Auth0UserDetails;
import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import com.travisolbrich.scavenger.hunt.exception.UnauthorizedException;
import com.travisolbrich.scavenger.hunt.model.Team;
import com.travisolbrich.scavenger.hunt.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CurrentUserInfoService {

    @Autowired
    UserService userService;

    /**
     * Get the current user but add in their ID from the database.
     * @return A user containing the database ID.
     */
    public User getCurrentRegisteredUser() {
        User user = getCurrentUser();

        // Get the user ID from the database
        UserEntity userEntity = userService.getUserEntity(user);

        if(userEntity == null) {
            throw new UnauthorizedException("NOT_REGISTERED");
        }

        user.setUserId(userEntity.getUserId());
        user.setTeam(new ModelMapper().map(userEntity.getTeam(), Team.class));

        return user;
    }

    /**
     * Decode the JWT to return a User. User does not need to be registered
     * @return User matching decoded JWT
     */
    public User getCurrentUser() {
        Auth0UserDetails details = this.getPrincipal();

        // Map role objects to string
        ArrayList<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : details.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        // Set values that were available in the token
        User user = new User();
        user.setGivenName((String) details.getAuth0Attribute("given_name"));
        user.setFamilyName((String) details.getAuth0Attribute("family_name"));
        user.setEmail((String) details.getAuth0Attribute("email"));
        user.setAuth0Id((String) details.getAuth0Attribute("sub"));
        user.setRoles(roles);

        return user;
    }

    /**
     * Get the current user's persistence entity
     * @return An entity for the user
     */
    public UserEntity getCurrentUserEntity() {
        return userService.getUserEntity(getCurrentUser());
    }

    private Auth0UserDetails getPrincipal() {
        return (Auth0UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
