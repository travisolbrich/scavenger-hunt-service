package com.travisolbrich.scavenger.hunt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity extends RandomUnlockerEntity {
    private Long taskId;
    private String question;
    private String answer;
    private CheckpointEntity checkpointEntity;
    private List<UnlockedTaskEntity> unlockedTaskEntityList;

    @Id
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Basic
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    public CheckpointEntity getCheckpointEntity() {
        return checkpointEntity;
    }

    public void setCheckpointEntity(CheckpointEntity checkpointEntity) {
        this.checkpointEntity = checkpointEntity;
    }

    @OneToMany(targetEntity = UnlockedTaskEntity.class, mappedBy = "taskEntity")
    public List<UnlockedTaskEntity> getUnlockedTaskEntityList() {
        return unlockedTaskEntityList;
    }

    public void setUnlockedTaskEntityList(List<UnlockedTaskEntity> unlockedTaskEntityList) {
        this.unlockedTaskEntityList = unlockedTaskEntityList;
    }

    @Transient
    @Override
    public List<ClueEntity> getUnlockableEntityList() {
        return this.getCheckpointEntity().getClueEntityList();
    }

    @Transient
    @Override
    public boolean isKeyValid(String key) {
        return (key.equals(this.getAnswer()));
    }
}
