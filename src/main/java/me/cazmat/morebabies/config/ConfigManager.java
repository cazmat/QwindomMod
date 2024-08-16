package me.cazmat.morebabies.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import me.cazmat.morebabies.Constants;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.nio.file.Path;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Constants.MOD_ID)
public class ConfigManager {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    private static final ForgeConfigSpec.BooleanValue VILLAGER_SPAWNS_ENABLED;
    private static final ForgeConfigSpec.BooleanValue VILLAGER_SPAWNS_COMMAND;
    private static final ForgeConfigSpec.BooleanValue VILLAGER_SPAWNS_EGG;
    private static final ForgeConfigSpec.BooleanValue SPROG_HOSTILITY;

    static {
        BUILDER.push("Mob Spawns");
        VILLAGER_SPAWNS_ENABLED = BUILDER.comment("Do we allow villager spawns on the server?").define("villager_spawns_enabled", false);
        VILLAGER_SPAWNS_COMMAND = BUILDER.comment("Do we allow commands to spawn villagers? (Overrides villager spawn setting)").define("villager_spawns_command", true);
        VILLAGER_SPAWNS_EGG = BUILDER.comment("Do we allow spawn eggs to spawn villagers? (Overrides villager spawn setting)").define("villager_spawns_egg", true);
        BUILDER.pop();
        BUILDER.push("Sprog Settings");
        SPROG_HOSTILITY = BUILDER.comment("Should Sprogs be hostile to players by default?").define("sprog_hostility", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
    public static boolean allowVillagerSpawns() {
        return VILLAGER_SPAWNS_ENABLED.get();
    }
    public static boolean allowVillagerCommandSpawn() {
        return VILLAGER_SPAWNS_COMMAND.get();
    }
    public static boolean allowVillagerEggSpawn() {
        return VILLAGER_SPAWNS_EGG.get();
    }
    public static boolean areSprogsHostile() {
        return SPROG_HOSTILITY.get();
    }
}
