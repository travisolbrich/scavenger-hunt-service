package com.travisolbrich.scavenger.hunt.model;

import java.beans.Transient;

public class Task {
    private Long taskId;
    private Long checkpointId;
    private String question;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Transient
    public void setCheckpointEntityCheckpointId(Long checkpointId) {
        this.setCheckpointId(checkpointId);
    }
}
