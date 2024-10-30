package me.cazmat.qwindom.events;

import me.cazmat.qwindom.Qwindom;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.IModInfo;
import org.slf4j.Logger;

import java.util.Optional;

public class UpdateNotifier {
    @SubscribeEvent
    public void playerJoinEvent(EntityJoinLevelEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            return;
        }
        if(!event.getLevel().isClientSide) {
            return;
        }
        Player player = (Player) event.getEntity();
        Optional<? extends ModContainer> modContainer = ModList.get().getModContainerById(Qwindom.MOD_ID);
        IModInfo modInfo = modContainer.get().getModInfo();
        if(VersionChecker.getResult(modInfo).status() == VersionChecker.Status.OUTDATED) {
            player.displayClientMessage(Component.literal("[QwindomMod] The Qwindom mod has an update!"), false);
        }
    }
}
