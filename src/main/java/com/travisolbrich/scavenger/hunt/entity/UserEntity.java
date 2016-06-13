package com.travisolbrich.scavenger.hunt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    private long userId;
    private String auth0Id;
    private String givenName;
    private String familyName;
    private TeamEntity team;
    private List<UnlockedClueEntity> unlockedClueEntityList;
    private List<UnlockedTaskEntity> unlockedTaskEntityList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    @Column(name = "auth0_id")
    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

//    @OneToMany(targetEntity = UnlockedClueEntity.class, mappedBy = "clueEntity")
//    @OrderBy("unlockedAt DESC")
//    public List<UnlockedClueEntity> getUnlockedClueEntityList() {
//        return unlockedClueEntityList;
//    }
//
//    public void setUnlockedClueEntityList(List<UnlockedClueEntity> unlockedClueEntityList) {
//        this.unlockedClueEntityList = unlockedClueEntityList;
//    }
//
//    @OneToMany(targetEntity = UnlockedTaskEntity.class, mappedBy = "taskEntity")
//    @OrderBy("unlockedAt DESC")
//    public List<UnlockedTaskEntity> getUnlockedTaskEntityList() {
//        return unlockedTaskEntityList;
//    }

    public void setUnlockedTaskEntityList(List<UnlockedTaskEntity> unlockedTaskEntityList) {
        this.unlockedTaskEntityList = unlockedTaskEntityList;
    }
}
