package com.travisolbrich.scavenger.hunt.entity;

import java.util.List;
import java.util.Random;

public abstract class RandomUnlockerEntity<T> {

    abstract List<T> getUnlockableEntityList();
    public abstract boolean isKeyValid(String key);

    public T getRandomUnlockableEntity(){
        List<T> unlockableEntities = this.getUnlockableEntityList();

        Random random = new Random();
        int index = random.nextInt(unlockableEntities.size());

        return unlockableEntities.get(index);
    };

}
