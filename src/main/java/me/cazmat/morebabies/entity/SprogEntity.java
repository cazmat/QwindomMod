package me.cazmat.morebabies.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.properties.Property;
import me.cazmat.morebabies.config.ConfigManager;
import me.cazmat.morebabies.entity.ai.SprogAttackGoal;
import me.cazmat.morebabies.utils.PlayerSkinUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SprogEntity extends PathfinderMob implements IEntityAdditionalSpawnData {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(SprogEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Integer> AGGRO = SynchedEntityData.defineId(SprogEntity.class, EntityDataSerializers.INT);
    private String textureB64 = null;
    public SprogEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        setPersistenceRequired();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(1, new SprogAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
        this.entityData.define(AGGRO, -1);
    }
    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getSkinLocation() {
        if(getOwnerId() == null) {
            setOwnerId(Minecraft.getInstance().player.getUUID());
        }
        if(textureB64 == null) {
            textureB64 = PlayerSkinUtils.getHeadValue(getOwnerId());
        }
        if(textureB64.equals("nil")) {
            return DefaultPlayerSkin.getDefaultSkin(getOwnerId());
        }
        GameProfile gameProfile = new GameProfile(getOwnerId(), null);
        gameProfile.getProperties().put("textures", new Property("textures", textureB64));
        if(gameProfile.getProperties().get("textures") != null) {
            final SkinManager manager = Minecraft.getInstance().getSkinManager();
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = manager.getInsecureSkinInformation(gameProfile);
            if(map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                final MinecraftProfileTexture skin = map.get(MinecraftProfileTexture.Type.SKIN);
                return manager.registerTexture(skin, MinecraftProfileTexture.Type.SKIN);
            } else {
                UUID uuid = UUIDUtil.getOrCreatePlayerUUID(gameProfile);
                return DefaultPlayerSkin.getDefaultSkin(uuid);
            }
        } else {
            UUID uuid = UUIDUtil.getOrCreatePlayerUUID(gameProfile);
            return DefaultPlayerSkin.getDefaultSkin(uuid);
        }
    }
    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if(this.getOwnerId() != null) {
            nbt.putUUID("Owner", this.getOwnerId());
        }
        nbt.putInt("Hostile", getAggro());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        UUID uuid;
        if(nbt.hasUUID("Owner")) {
            uuid = nbt.getUUID("Owner");
        } else {
            String s = nbt.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        if(uuid != null) {
            try {
                this.setOwnerId(uuid);
            } catch(Throwable ignored) {}
        }
        if(nbt.contains("Hostile")) {
            this.setAggro(nbt.getInt("Hostile"));
        }
    }
    @Nullable
    public UUID getOwnerId() {
        return this.entityData.get(OWNER_UNIQUE_ID).orElse((UUID) null);
    }
    public void setOwnerId(@Nullable UUID uuid) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(uuid));
    }
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
    public boolean getHostile() {
        int aggro = getAggro();
        if(aggro == -1) {
            return ConfigManager.areSprogsHostile();
        }
        return aggro == 1;
    }
    public int getAggro() {
        return this.entityData.get(AGGRO);
    }
    public void setAggro(int aggro) {
        this.entityData.set(AGGRO, aggro);
    }
    @Override
    public void writeSpawnData(FriendlyByteBuf friendlyByteBuf) {}
    @Override
    public void readSpawnData(FriendlyByteBuf friendlyByteBuf) {}
}
