package tech.whocodes.jampack.UtilityApi.Api;

import tech.whocodes.jampack.InteractionApi.Collections.Bank;
import tech.whocodes.jampack.InteractionApi.Collections.BankInventory;
import tech.whocodes.jampack.InteractionApi.Collections.Queries.ItemQuery;
import tech.whocodes.jampack.InteractionApi.InteractionApiPlugin;
import tech.whocodes.jampack.Packets.MousePackets;
import tech.whocodes.jampack.Packets.WidgetPackets;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

import java.util.Collection;

public class BankUtil {

    public static void depositAll(){
        Widget depositInventory = InteractionApiPlugin.getClient().getWidget(WidgetInfo.BANK_DEPOSIT_INVENTORY);
        if (depositInventory != null) {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(depositInventory, "Deposit inventory");
        }
    }

    public static ItemQuery nameContainsNoCase(String name) {
        return Bank.search().filter(widget -> widget.getName().toLowerCase().contains(name.toLowerCase()));
    }
    public static int getItemAmount(int itemId) {
        return getItemAmount(itemId, false);
    }

    public static int getItemAmount(int itemId, boolean stacked) {
        return stacked ?
                Bank.search().withId(itemId).first().map(Widget::getItemQuantity).orElse(0) :
                Bank.search().withId(itemId).result().size();
    }

    public static int getItemAmount(String itemName) {
        return nameContainsNoCase(itemName).result().size();
    }


    public static boolean hasItem(int id) {
        return hasItem(id, 1, false);
    }

    public static boolean hasItem(int id, int amount) {
        return getItemAmount(id, false) >= amount;
    }

    public static boolean hasItem(int id, int amount, boolean stacked) {
        return getItemAmount(id, stacked) >= amount;
    }

    public static boolean hasAny(int ...ids) {
        for (int id : ids) {
            if (getItemAmount(id) > 0) {
                return true;
            }
        }
        return false;
    }

    //good idea, credit to marcojacobsNL
    public static boolean containsExcept(Collection<Integer> ids) {
        if (!Bank.isOpen()) {
            return false;
        }
        Collection<Widget> inventoryItems = BankInventory.search().result();

        for (Widget item : inventoryItems) {
            if (!ids.contains(item.getItemId())) {
                return true;
            }
        }
        return false;
    }

}
