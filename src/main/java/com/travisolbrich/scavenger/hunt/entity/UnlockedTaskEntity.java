package com.travisolbrich.scavenger.hunt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "unlocked_task")
public class UnlockedTaskEntity {
    private Long unlockedTaskId;
    private Date unlockedAt;
    private CheckpointEntity checkpointEntity;
    private TaskEntity taskEntity;
    private TeamEntity teamEntity;
    private UserEntity userEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUnlockedTaskId() {
        return unlockedTaskId;
    }

    public void setUnlockedTaskId(Long unlockedTaskId) {
        this.unlockedTaskId = unlockedTaskId;
    }

    @Basic
    public Date getUnlockedAt() {
        return unlockedAt;
    }

    public void setUnlockedAt(Date unlockedAt) {
        this.unlockedAt = unlockedAt;
    }

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    public CheckpointEntity getCheckpointEntity() {
        return checkpointEntity;
    }

    public void setCheckpointEntity(CheckpointEntity checkpointEntity) {
        this.checkpointEntity = checkpointEntity;
    }

    @ManyToOne
    @JoinColumn(name = "task_id")
    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    @ManyToOne
    @JoinColumn(name = "team_id")
    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
