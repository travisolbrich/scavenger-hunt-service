package com.travisolbrich.scavenger.hunt.entity;

import com.travisolbrich.scavenger.hunt.exception.BadRequestException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "code_tag")
public class CodeTagEntity extends RandomUnlockerEntity {
    private Long codeTagId;
    private String placementDescription;
    private double lat;
    private double lng;
    private String unlockCode;
    private ClueEntity referencingClue;
    private CheckpointEntity checkpointEntity;

    @Id
    public Long getCodeTagId() {
        return codeTagId;
    }

    public void setCodeTagId(Long codeTagId) {
        this.codeTagId = codeTagId;
    }

    @Basic
    public String getPlacementDescription() {
        return placementDescription;
    }

    public void setPlacementDescription(String placementDescription) {
        this.placementDescription = placementDescription;
    }

    @Basic
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Basic
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Basic
    public String getUnlockCode() {
        return unlockCode;
    }

    public void setUnlockCode(String unlockCode) {
        this.unlockCode = unlockCode;
    }

    @OneToOne(mappedBy = "targetCodeTag")
    public ClueEntity getReferencingClue() {
        return referencingClue;
    }

    public void setReferencingClue(ClueEntity referencingClue) {
        this.referencingClue = referencingClue;
    }

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    public CheckpointEntity getCheckpointEntity() {
        return checkpointEntity;
    }

    public void setCheckpointEntity(CheckpointEntity checkpointEntity) {
        this.checkpointEntity = checkpointEntity;
    }

    @Transient
    @Override
    public List<TaskEntity> getUnlockableEntityList() {
        return this.getCheckpointEntity().getTaskEntityList();
    }

    @Transient
    @Override
    public boolean isKeyValid(String key) {
        return (key.equals(this.getUnlockCode()));
    }
}
