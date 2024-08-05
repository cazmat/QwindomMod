package me.cazmat.morebabies.registry;

import me.cazmat.morebabies.QwindomMod;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = QwindomMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, QwindomMod.MOD_ID);
    @SubscribeEvent
    public static void entityAttributeCreation(EntityAttributeCreationEvent event) {
        
    }
}
