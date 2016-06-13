package com.travisolbrich.scavenger.hunt.model;

public class ProgressionStatus {
    private Task lastTask;
    private Clue lastClue;
    private String lastName;
    private String nextName;
    private Long nextId;

    public Task getLastTask() {
        return lastTask;
    }

    public void setLastTask(Task lastTask) {
        this.lastTask = lastTask;
    }

    public Clue getLastClue() {
        return lastClue;
    }

    public void setLastClue(Clue lastClue) {
        this.lastClue = lastClue;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNextName() {
        return nextName;
    }

    public void setNextName(String nextName) {
        this.nextName = nextName;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }
}
