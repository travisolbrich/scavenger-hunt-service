package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.*;
import com.travisolbrich.scavenger.hunt.entity.ClueEntity;
import com.travisolbrich.scavenger.hunt.exception.BadRequestException;
import com.travisolbrich.scavenger.hunt.model.Clue;
import com.travisolbrich.scavenger.hunt.model.TaskResponse;
import com.travisolbrich.scavenger.hunt.repository.ClueRepository;
import com.travisolbrich.scavenger.hunt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClueService {
    @Autowired
    ClueRepository clueRepository;

    @Autowired
    UnlockedClueService unlockedClueService;

    public List<ClueEntity> getCluesForUser(UserEntity userEntity) {
        return userEntity.getTeam().getClueEntityList();
    }

    public ClueEntity getClue(Long clueId) {
        return clueRepository.findOne(clueId);
    }

    public ClueEntity getLatestClueForUser(UserEntity userEntity) {
        List<ClueEntity> cluesForUser = getCluesForUser(userEntity);

        if(cluesForUser.size() == 0) {
            throw new BadRequestException("NO_UNLOCKS");
        }

        return  cluesForUser.get(0);
    }

}
