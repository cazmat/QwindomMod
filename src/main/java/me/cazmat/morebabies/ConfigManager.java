package me.cazmat.morebabies;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.nio.file.Path;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Constants.MOD_ID)
public class ConfigManager {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push(Constants.MOD_ID);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
    @SubscribeEvent
    public static void onConfigEvent(ModConfigEvent event) {
        SPEC.setConfig(event.getConfig().getConfigData());
    }
}
