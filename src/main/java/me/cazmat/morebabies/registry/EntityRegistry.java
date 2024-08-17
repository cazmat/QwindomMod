package me.cazmat.morebabies.registry;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.entity.MindlessEntity;
import me.cazmat.morebabies.entity.SprogEntity;
import me.cazmat.morebabies.entity.firefly.FireflyEntity;
import me.cazmat.morebabies.entity.firefly.IceFireflyEntity;
import me.cazmat.morebabies.entity.firefly.LushFireflyEntity;
import me.cazmat.morebabies.entity.firefly.MagmaFireflyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MOD_ID);
    public static final RegistryObject<EntityType<FireflyEntity>> FIREFLY = DEF_REG.register("firefly",
            () -> EntityType.Builder.of(FireflyEntity::new, MobCategory.AMBIENT)
                    .sized(0.7F, 0.6F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(Constants.MOD_ID, "firefly").toString())
    );
    public static final RegistryObject<EntityType<IceFireflyEntity>> ICE_FIREFLY = DEF_REG.register("icefirefly",
            () -> EntityType.Builder.of(IceFireflyEntity::new, MobCategory.AMBIENT)
                    .sized(0.7F, 0.6F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(Constants.MOD_ID, "icefirefly").toString())
    );
    public static final RegistryObject<EntityType<LushFireflyEntity>> LUSH_FIREFLY = DEF_REG.register("lushfirefly",
            () -> EntityType.Builder.of(LushFireflyEntity::new, MobCategory.AMBIENT)
                    .sized(0.7F, 0.6F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(Constants.MOD_ID, "lushfirefly").toString())
    );
    public static final RegistryObject<EntityType<MagmaFireflyEntity>> MAGMA_FIREFLY = DEF_REG.register("magmafirefly",
            () -> EntityType.Builder.of(MagmaFireflyEntity::new, MobCategory.AMBIENT)
                    .sized(0.7F, 0.6F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(Constants.MOD_ID, "magmafirefly").toString())
    );
    public static final RegistryObject<EntityType<MindlessEntity>> MINDLESS = DEF_REG.register("mindless",
            () -> EntityType.Builder.of(MindlessEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(Constants.MOD_ID, "mindless").toString())
    );
    public static final RegistryObject<EntityType<SprogEntity>> SPROG = DEF_REG.register("sprog",
            () -> EntityType.Builder.of(SprogEntity::new, MobCategory.AMBIENT)
                    .sized(0.3F, 1.0F)
                    .clientTrackingRange(8)
                    .build(new ResourceLocation(Constants.MOD_ID, "sprog").toString())
    );
    @SubscribeEvent
    public static void defineAttributes(EntityAttributeCreationEvent event) {
        event.put(FIREFLY.get(), FireflyEntity.createAttributes().build());
        event.put(ICE_FIREFLY.get(), IceFireflyEntity.createAttributes().build());
        event.put(LUSH_FIREFLY.get(), LushFireflyEntity.createAttributes().build());
        event.put(MAGMA_FIREFLY.get(), MagmaFireflyEntity.createAttributes().build());
        event.put(MINDLESS.get(), MindlessEntity.createAttributes().build());
        event.put(SPROG.get(), SprogEntity.createAttributes().build());
    }
}
