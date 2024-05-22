package tech.whocodes.jampack.Interaction;

import tech.whocodes.jampack.Packets.MousePackets;
import tech.whocodes.jampack.Packets.PlayerPackets;
import tech.whocodes.jampack.InteractionApi.Collections.Players;
import net.runelite.api.Player;

import java.util.Optional;
import java.util.function.Predicate;

public class PlayerInteractionHelper {
    public static boolean interact(Player player, String... actions) {
        if (player == null) {
            return false;
        }
        MousePackets.queueClickPacket();
        PlayerPackets.queuePlayerAction(player, actions);
        return true;
    }

    public static boolean interact(String name, String... actions) {
        return Players.search().withName(name).first().flatMap(Player ->
        {
            MousePackets.queueClickPacket();
            PlayerPackets.queuePlayerAction(Player, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static boolean interact(Predicate<? super Player> predicate, String... actions) {
        return Players.search().filter(predicate).first().flatMap(Player ->
        {
            MousePackets.queueClickPacket();
            PlayerPackets.queuePlayerAction(Player, actions);
            return Optional.of(true);
        }).orElse(false);
    }
}
