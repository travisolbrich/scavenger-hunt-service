package com.travisolbrich.scavenger.hunt.model;

import java.beans.Transient;

public class Clue {
    private Long clueId;
    private Long checkpointId;
    private String clue;

    public Long getClueId() {
        return clueId;
    }

    public void setClueId(Long clueId) {
        this.clueId = clueId;
    }

    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    @Transient
    public void setCheckpointEntityCheckpointId(Long checkpointId) {
        this.setCheckpointId(checkpointId);
    }
}
