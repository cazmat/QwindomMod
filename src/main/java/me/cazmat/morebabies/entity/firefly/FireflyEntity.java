package me.cazmat.morebabies.entity.firefly;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class FireflyEntity extends Animal implements FlyingAnimal, IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public FireflyEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                .add(Attributes.FLYING_SPEED, 0.6000000238418579)
                .add(Attributes.MOVEMENT_SPEED, 0.30000001192092896)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.firefly.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.firefly.idle", true));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround;
    }
    @Override
    public @NotNull MobType getMobType() {
        return MobType.ARTHROPOD;
    }
}
