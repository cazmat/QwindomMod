package me.cazmat.qwindom.registry;

import me.cazmat.qwindom.Qwindom;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Qwindom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabsRegistry {
    public static final CreativeModeTab WALLS_TAB = new CreativeModeTab("qwindom_walls") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.CALCITE_WALL.get());
        }
    };
    public static final CreativeModeTab STAIRS_TAB = new CreativeModeTab("qwindom_stairs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.CALCITE_STAIRS.get());
        }
    };
    public static final CreativeModeTab BUTTONS_TAB = new CreativeModeTab("qwindom_buttons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.CALCITE_BUTTON.get());
        }
    };
    public static final CreativeModeTab SLABS_TAB = new CreativeModeTab("qwindom_slabs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.DRIPSTONE_SLAB.get());
        }
    };
}
