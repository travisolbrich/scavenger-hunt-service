package com.travisolbrich.scavenger.hunt.entity;

import org.aspectj.apache.bcel.classfile.Code;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "checkpoint")
public class CheckpointEntity {
    private Long checkpointId;
    private String description;

    private List<CodeTagEntity> codeTagEntityList;
    private List<TaskEntity> taskEntityList;
    private List<ClueEntity> clueEntityList;

    @Id
    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(targetEntity = CodeTagEntity.class, mappedBy = "checkpointEntity")
    public List<CodeTagEntity> getCodeTagEntityList() {
        return codeTagEntityList;
    }

    public void setCodeTagEntityList(List<CodeTagEntity> codeTagEntityList) {
        this.codeTagEntityList = codeTagEntityList;
    }

    @OneToMany(targetEntity = TaskEntity.class, mappedBy = "checkpointEntity")
    public List<TaskEntity> getTaskEntityList() {
        return taskEntityList;
    }

    public void setTaskEntityList(List<TaskEntity> taskEntityList) {
        this.taskEntityList = taskEntityList;
    }

    @OneToMany(targetEntity = ClueEntity.class, mappedBy = "checkpointEntity")
    public List<ClueEntity> getClueEntityList() {
        return clueEntityList;
    }

    public void setClueEntityList(List<ClueEntity> clueEntityList) {
        this.clueEntityList = clueEntityList;
    }
}
