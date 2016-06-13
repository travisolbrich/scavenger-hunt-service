package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.exception.BadRequestException;
import com.travisolbrich.scavenger.hunt.model.Clue;
import com.travisolbrich.scavenger.hunt.model.ProgressionStatus;
import com.travisolbrich.scavenger.hunt.model.Task;
import com.travisolbrich.scavenger.hunt.model.User;
import com.travisolbrich.scavenger.hunt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    CurrentUserInfoService currentUserInfoService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamService teamService;

    /**
     * Add a record in the user table based on current user's JWT
     *
     * @param user User model that we want to register
     * @return Entity representing the user
     */
    public UserEntity registerUser(User user) {
        UserEntity oneByAuth0Id = userRepository.findOneByAuth0Id(user.getAuth0Id());
        if (oneByAuth0Id != null) {
            return oneByAuth0Id;
        }

        UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
        userEntity.setTeam(teamService.getUnassignedTeam());
        return userRepository.save(userEntity);
    }

    public ProgressionStatus getProgressionStatus(UserEntity userEntity) {
        TeamEntity teamEntity = userEntity.getTeam();

        List<ClueEntity> unlockedClueEntityList = teamEntity.getClueEntityList();
        List<TaskEntity> unlockedTaskEntityList = teamEntity.getTaskEntityList();
        ProgressionStatus progressionStatus = new ProgressionStatus();

        if (unlockedClueEntityList.size() > 0) {
            ClueEntity clueEntity = unlockedClueEntityList.get(0);
            Clue clue = new ModelMapper().map(clueEntity, Clue.class);
            progressionStatus.setLastClue(clue);
        }
        if (unlockedTaskEntityList.size() > 0) {
            TaskEntity taskEntity = unlockedTaskEntityList.get(0);
            Task task = new ModelMapper().map(taskEntity, Task.class);
            progressionStatus.setLastTask(task);
        }

        if(progressionStatus.getLastTask() == null) {
            progressionStatus.setLastName(null);
            progressionStatus.setNextName("SCAN");
        }
        else if(progressionStatus.getLastClue() == null) {
            progressionStatus.setLastName("SCAN");
            progressionStatus.setNextName("TASK");
        }
        else if(progressionStatus.getLastTask().getCheckpointId() == progressionStatus.getLastClue().getCheckpointId()) {
            progressionStatus.setLastName("TASK");
            progressionStatus.setNextName("CLUE");
        } else {
            progressionStatus.setLastName("CLUE");
            progressionStatus.setNextName("TASK");
        }

        return progressionStatus;
    }

    public UserEntity getUserEntity(Long userId) {
        return userRepository.findOne(userId);
    }

    public UserEntity getUserEntity(User user) {
        return userRepository.findOneByAuth0Id(user.getAuth0Id());
    }
}
