package me.cazmat.morebabies.entity.ai;

import me.cazmat.morebabies.entity.SprogEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class SprogAttackGoal extends Goal {
    protected final SprogEntity attacker;
    private final double speedTowardsTarget;
    private final boolean longMemory;
    private Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int delayCounter;
    private int ticksUntilNextAttack;
    private long lastCanUseCheck;
    private int failedPathFindingPenalty = 0;
    private final boolean canPenalize = false;
    public SprogAttackGoal(SprogEntity entity, double speedIn, boolean useLongMemory) {
        this.attacker = entity;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }
    @Override
    public boolean canUse() {
        if(!this.attacker.getHostile()) {
            return false;
        }
        long i = this.attacker.level.getGameTime();
        if(i - this.lastCanUseCheck < 20L) {
            return false;
        } else {
            this.lastCanUseCheck = i;
            LivingEntity targetEntity = this.attacker.getTarget();
            if(targetEntity == null) {
                return false;
            } else if(!targetEntity.isAlive()) {
                return false;
            } else {
                if(canPenalize) {
                    if(--this.delayCounter <= 0) {
                        this.path = this.attacker.getNavigation().createPath(targetEntity, 0);
                        this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
                        return this.path != null;
                    } else {
                        return true;
                    }
                }
                this.path = this.attacker.getNavigation().createPath(targetEntity, 0);
                if(this.path != null) {
                    return true;
                } else {
                    return this.getAttackReachSqr(targetEntity) >= this.attacker.distanceToSqr(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ());
                }
            }
        }
    }
    @Override
    public boolean canContinueToUse() {
        if(!this.attacker.getHostile()) {
            return false;
        }
        LivingEntity targetEntity = this.attacker.getTarget();
        if(targetEntity == null) {
            return false;
        } else if(!targetEntity.isAlive()) {
            return false;
        } else if(!this.longMemory) {
            return !this.attacker.getNavigation().isDone();
        } else if(!this.attacker.isWithinRestriction(targetEntity.blockPosition())) {
            return false;
        } else {
            return !(targetEntity instanceof Player) || !targetEntity.isSpectator() && ((Player) targetEntity).isCreative();
        }
    }
    @Override
    public void start() {
        this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
        this.attacker.setAggressive(true);
        this.delayCounter = 0;
        this.ticksUntilNextAttack = 0;
    }
    @Override
    public void stop() {
        LivingEntity targetEntity = this.attacker.getTarget();
        if(!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(targetEntity)) {
            this.attacker.setTarget(null);
        }
        this.attacker.setAggressive(false);
        this.attacker.getNavigation().stop();
    }
    @Override
    public void tick() {
        LivingEntity targetEntity = this.attacker.getTarget();
        if(targetEntity != null) {
            this.attacker.getLookControl().setLookAt(targetEntity, 30.0F, 30.0F);
            double d0 = this.attacker.distanceToSqr(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ());
            this.delayCounter = Math.max(this.delayCounter - 1, 0);
            if((this.longMemory || this.attacker.getSensing().hasLineOfSight(targetEntity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || targetEntity.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRandom().nextFloat() < 0.05F)) {
                this.targetX = targetEntity.getX();
                this.targetY = targetEntity.getY();
                this.targetZ = targetEntity.getZ();
                this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
                if(this.canPenalize) {
                    this.delayCounter += failedPathFindingPenalty;
                    if(this.attacker.getNavigation().getPath() != null) {
                        Node finalPathPoint = this.attacker.getNavigation().getPath().getEndNode();
                        if(finalPathPoint != null && targetEntity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1) {
                            failedPathFindingPenalty = 0;
                        } else {
                            failedPathFindingPenalty += 10;
                        }
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if(d0 > 1024.0D) {
                    this.delayCounter += 10;
                } else if(d0 > 256.0D) {
                    this.delayCounter += 5;
                }
                if(!this.attacker.getNavigation().moveTo(targetEntity, this.speedTowardsTarget)) {
                    this.delayCounter += 15;
                }
            }
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.checkAndPerformAttack(targetEntity, d0);
        }
    }
    protected void checkAndPerformAttack(LivingEntity target, double distanceToTarget) {
        double d0 = this.getAttackReachSqr(target);
        if(distanceToTarget <= d0 && this.ticksUntilNextAttack <= 0) {
            this.resetAttackCooldown();
            this.attacker.swing(InteractionHand.MAIN_HAND);
            this.attacker.doHurtTarget(target);
        }
    }
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = 20;
    }
    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth();
    }
}
