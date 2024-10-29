package me.cazmat.qwindom.registry;

import me.cazmat.qwindom.Qwindom;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Qwindom.MOD_ID);

    public static final RegistryObject<Block> BASALT_WALL = registerBlock("basalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.BASALT)), TabsRegistry.WALLS_TAB);
    public static final RegistryObject<Block> CALCITE_WALL = registerBlock("calcite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE)), TabsRegistry.WALLS_TAB);
    public static final RegistryObject<Block> OAK_WALL = registerBlock("oak_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD)), TabsRegistry.WALLS_TAB);
    public static final RegistryObject<Block> POLISHED_BASALT_WALL = registerBlock("polished_basalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT)), TabsRegistry.WALLS_TAB);
    public static final RegistryObject<Block> SMOOTH_BASALT_WALL = registerBlock("smooth_basalt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_BASALT)), TabsRegistry.WALLS_TAB);

    public static final RegistryObject<Block> CALCITE_STAIRS = registerBlock("calcite_stairs",
            () -> new StairBlock(Blocks.CALCITE.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.CALCITE)), TabsRegistry.STAIRS_TAB);
    public static final RegistryObject<Block> POLISHED_BASALT_STAIRS = registerBlock("polished_basalt_stairs",
            () -> new StairBlock(Blocks.POLISHED_BASALT.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT)), TabsRegistry.STAIRS_TAB);
    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS = registerBlock("smooth_basalt_stairs",
            () -> new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.SMOOTH_BASALT)), TabsRegistry.STAIRS_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
