package tech.whocodes.jampack.UtilityApi.Strategy;


import net.runelite.api.NPC;
import net.runelite.api.TileObject;
import net.runelite.client.config.Config;
import net.runelite.client.plugins.Plugin;
import tech.whocodes.jampack.Interaction.NPCInteraction;
import tech.whocodes.jampack.Interaction.TileObjectInteraction;
import tech.whocodes.jampack.InteractionApi.Collections.NPCs;
import tech.whocodes.jampack.InteractionApi.Collections.TileObjects;
import tech.whocodes.jampack.InteractionApi.Collections.Queries.NPCQuery;
import tech.whocodes.jampack.InteractionApi.Collections.Queries.TileObjectQuery;

import java.util.Optional;
import java.util.function.Predicate;

public abstract class AbstractTask<T extends Plugin, V extends Config> implements TaskInterface {

    protected T plugin;
    protected V config;

    public AbstractTask(T plugin, V config) {
        this.plugin = plugin;
        this.config = config;
    }

    public abstract boolean validate();

    public abstract void execute();

    @Override
    public boolean interactObject(TileObject object, String action) {
        if (object == null) return false;
        return TileObjectInteraction.interact(object, action);
    }

    @Override
    public boolean interactObject(String objectName, String action, boolean nearest) {
        TileObjectQuery query = TileObjects.search().nameContains(objectName).withAction(action);
        Optional<TileObject> object = Optional.ofNullable(nearest ? query.nearestToPlayer().orElse(null) : query.first().orElse(null));
        return interactObject(object.orElse(null), action);
    }

    @Override
    public boolean interactObject(String objectName, String action, Predicate<TileObject> condition) {
        TileObject object = TileObjects.search().nameContains(objectName).withAction(action).filter(condition).first().orElse(null);
        return interactObject(object, action);
    }

    @Override
    public boolean interactNpc(NPC npc, String action) {
        if (npc == null) return false;
        return NPCInteraction.interact(npc, action);
    }

    @Override
    public boolean interactNpc(String npcName, String action, boolean nearest) {
        NPCQuery query = NPCs.search().nameContains(npcName).withAction(action);
        Optional<NPC> npc = Optional.ofNullable(nearest ? query.nearestToPlayer().orElse(null) : query.first().orElse(null));
        return interactNpc(npc.orElse(null), action);
    }

    @Override
    public boolean interactNpc(String npcName, String action, Predicate<NPC> condition) {
        NPC npc = NPCs.search().nameContains(npcName).withAction("Attack").filter(condition).first().orElse(null);
        return interactNpc(npc, action);
    }
}
