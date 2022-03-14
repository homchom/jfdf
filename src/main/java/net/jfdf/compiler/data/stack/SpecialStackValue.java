package net.jfdf.compiler.data.stack;

import net.jfdf.jfdf.values.CodeValue;

public class SpecialStackValue implements IStackValue {
    private final SpecialValueType type;

    public SpecialStackValue(SpecialValueType type) {
        this.type = type;
    }

    public SpecialValueType getType() {
        return type;
    }

    @Override
    public CodeValue getTransformedValue() {
        throw new IllegalStateException("Couldn't get transformed special value.");
    }

    @Override
    public String getDescriptor() {
        return null;
    }

    public enum SpecialValueType {
        SYSTEM_OUT,
        SYSTEM_ERR,

        // Player Selection
        PLAYER_CURRENT_SELECTION,
        PLAYER_DEFAULT,
        PLAYER_KILLER,
        PLAYER_DAMAGER,
        PLAYER_SHOOTER,
        PLAYER_VICTIM,
        PLAYER_ALL_PLAYERS,

        // Entity Selection
        ENTITY_CURRENT_SELECTION,
        ENTITY_DEFAULT,
        ENTITY_KILLER,
        ENTITY_DAMAGER,
        ENTITY_SHOOTER,
        ENTITY_VICTIM,
        ENTITY_PROJECTILE,
        ENTITY_ALL_ENTITIES,
        ENTITY_ALL_MOBS,
        ENTITY_LAST_ENTITY_SPAWNED,
        ENTITY_LAST_MOB_SPAWNED
    }
}
