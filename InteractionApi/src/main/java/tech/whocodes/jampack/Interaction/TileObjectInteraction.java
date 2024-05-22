package tech.whocodes.jampack.Interaction;

import tech.whocodes.jampack.Packets.MousePackets;
import tech.whocodes.jampack.Packets.ObjectPackets;
import tech.whocodes.jampack.InteractionApi.Collections.TileObjects;
import tech.whocodes.jampack.InteractionApi.Collections.Queries.TileObjectQuery;
import net.runelite.api.ObjectComposition;
import net.runelite.api.TileObject;

import java.util.Optional;

public class TileObjectInteraction {


    public static boolean interact(String name, String... actions) {
        return TileObjects.search().withName(name).first().flatMap(tileObject ->
        {
            MousePackets.queueClickPacket();
            ObjectPackets.queueObjectAction(tileObject, false, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static boolean interact(int id, String... actions) {
        return TileObjects.search().withId(id).first().flatMap(tileObject ->
        {
            MousePackets.queueClickPacket();
            ObjectPackets.queueObjectAction(tileObject, false, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static boolean interact(TileObject tileObject, String... actions) {
        if (tileObject == null) {
            return false;
        }
        ObjectComposition comp = TileObjectQuery.getObjectComposition(tileObject);
        if (comp == null) {
            return false;
        }
        MousePackets.queueClickPacket();
        ObjectPackets.queueObjectAction(tileObject, false, actions);
        return true;
    }
}
