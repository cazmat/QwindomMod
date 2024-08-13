package me.cazmat.morebabies.registry;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.entity.MindlessEntity;
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
    public static final RegistryObject<EntityType<MindlessEntity>> MINDLESS = DEF_REG.register("mindless",
            () -> EntityType.Builder.of(MindlessEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(Constants.MOD_ID, "mindless").toString())
    );
    @SubscribeEvent
    public static void defineAttributes(EntityAttributeCreationEvent event) {
        event.put(MINDLESS.get(), MindlessEntity.createAttributes().build());
    }
}
