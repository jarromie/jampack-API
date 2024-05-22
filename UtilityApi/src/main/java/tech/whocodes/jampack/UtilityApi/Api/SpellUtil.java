package tech.whocodes.jampack.UtilityApi.Api;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import tech.whocodes.jampack.PacketApi.WidgetInfoExtended;

public class SpellUtil {
    public static WidgetInfoExtended parseStringForWidgetInfoExtended(String input) {
        for (WidgetInfoExtended value : WidgetInfoExtended.values()) {
            if (value.name().equalsIgnoreCase("SPELL_" + input.replace(" ", "_"))) {
                return value;
            }
        }
        return null;
    }

    public static Widget getSpellWidget(Client client, String input) {
        return client.getWidget(parseStringForWidgetInfoExtended(input).getPackedId());
    }
}
