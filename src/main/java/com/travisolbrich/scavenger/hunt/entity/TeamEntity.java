package com.travisolbrich.scavenger.hunt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
public class TeamEntity {
    private long teamId;
    private String name;
    private String color;
    private List<UserEntity> users;
    private List<UnlockedClueEntity> unlockedClueEntityList;
    private List<UnlockedTaskEntity> unlockedTaskEntityList;
    private List<ClueEntity> clueEntityList;
    private List<TaskEntity> taskEntityList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long id) {
        this.teamId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @OneToMany(targetEntity = UserEntity.class, mappedBy = "team")
    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @OneToMany(targetEntity = UnlockedClueEntity.class, mappedBy = "clueEntity")
    public List<UnlockedClueEntity> getUnlockedClueEntityList() {
        return unlockedClueEntityList;
    }

    public void setUnlockedClueEntityList(List<UnlockedClueEntity> unlockedClueEntityList) {
        this.unlockedClueEntityList = unlockedClueEntityList;
    }

    @OneToMany(targetEntity = UnlockedTaskEntity.class, mappedBy = "taskEntity")
    public List<UnlockedTaskEntity> getUnlockedTaskEntityList() {
        return unlockedTaskEntityList;
    }

    public void setUnlockedTaskEntityList(List<UnlockedTaskEntity> unlockedTaskEntityList) {
        this.unlockedTaskEntityList = unlockedTaskEntityList;
    }

    @ManyToMany
    @JoinTable(name="unlocked_clue", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "clue_id"))
    @OrderBy("checkpointEntity.checkpointId DESC")
    public List<ClueEntity> getClueEntityList() {
        return clueEntityList;
    }

    public void setClueEntityList(List<ClueEntity> clueEntityList) {
        this.clueEntityList = clueEntityList;
    }

    @ManyToMany
    @JoinTable(name="unlocked_task", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
    @OrderBy("checkpointEntity.checkpointId DESC")
    public List<TaskEntity> getTaskEntityList() {
        return taskEntityList;
    }

    public void setTaskEntityList(List<TaskEntity> taskEntityList) {
        this.taskEntityList = taskEntityList;
    }
}
