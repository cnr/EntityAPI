package io.snw.entityapi.entity;

import io.snw.entityapi.EntityManager;
import io.snw.entityapi.api.ControllableEntityType;
import io.snw.entityapi.api.EntitySound;
import org.bukkit.entity.Pig;

public class ControllablePig extends ControllableBaseEntity<Pig> {

    public ControllablePig(int id, EntityManager entityManager) {
        super(id, ControllableEntityType.PIG, entityManager);
    }

    public ControllablePig(int id, ControllablePigEntity entityHandle, EntityManager entityManager) {
        super(id, ControllableEntityType.PIG, entityManager);
        this.handle = entityHandle;
        this.loot = entityHandle.getDefaultMaterialLoot();
    }

    public void initSounds() {
        this.setSound(EntitySound.IDLE, "mob.pig.say");
        this.setSound(EntitySound.HURT, "mob.pig.say");
        this.setSound(EntitySound.DEATH, "mob.pig.death");
        this.setSound(EntitySound.STEP, "mob.pig.step");
    }
}