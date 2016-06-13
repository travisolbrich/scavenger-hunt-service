package com.travisolbrich.scavenger.hunt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clue")
public class ClueEntity{
    private Long clueId;
    private String clue;
    private CheckpointEntity checkpointEntity;
    private CodeTagEntity targetCodeTag;
    private List<UnlockedClueEntity> unlockedClueEntityList;

    @Id
    public Long getClueId() {
        return clueId;
    }

    public void setClueId(Long clueId) {
        this.clueId = clueId;
    }

    @Basic
    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    public CheckpointEntity getCheckpointEntity() {
        return checkpointEntity;
    }

    public void setCheckpointEntity(CheckpointEntity checkpointEntity) {
        this.checkpointEntity = checkpointEntity;
    }

    @OneToOne
    @JoinColumn(name = "target_code_tag_id")
    public CodeTagEntity getTargetCodeTag() {
        return targetCodeTag;
    }

    public void setTargetCodeTag(CodeTagEntity targetCodeTag) {
        this.targetCodeTag = targetCodeTag;
    }

    @OneToMany(targetEntity = UnlockedClueEntity.class, mappedBy = "clueEntity")
    public List<UnlockedClueEntity> getUnlockedClueEntityList() {
        return unlockedClueEntityList;
    }

    public void setUnlockedClueEntityList(List<UnlockedClueEntity> unlockedClueEntityList) {
        this.unlockedClueEntityList = unlockedClueEntityList;
    }


}
