package me.cazmat.qwindom.registry;

import me.cazmat.qwindom.Qwindom;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Qwindom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabsRegistry {
    public static final CreativeModeTab WALLS_TAB = new CreativeModeTab("ctvcore_walls") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.CALCITE_WALL.get());
        }
    };
}
