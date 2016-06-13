package com.travisolbrich.scavenger.hunt.service;

import com.travisolbrich.scavenger.hunt.entity.ClueEntity;
import com.travisolbrich.scavenger.hunt.entity.TeamEntity;
import com.travisolbrich.scavenger.hunt.entity.UnlockedClueEntity;
import com.travisolbrich.scavenger.hunt.entity.UserEntity;
import com.travisolbrich.scavenger.hunt.model.Clue;
import com.travisolbrich.scavenger.hunt.repository.ClueRepository;
import com.travisolbrich.scavenger.hunt.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public TeamEntity getUnassignedTeam()
    {
        return teamRepository.findOneByName("Unassigned");
    }
}
