package tech.whocodes.jampack.Interaction;

import tech.whocodes.jampack.InteractionApi.InteractionApiPlugin;
import tech.whocodes.jampack.Packets.MousePackets;
import tech.whocodes.jampack.Packets.WidgetPackets;
import tech.whocodes.jampack.InteractionApi.Collections.Bank;
import net.runelite.api.widgets.Widget;

import java.util.Optional;
import java.util.function.Predicate;

public class BankInteraction {
    private static final int WITHDRAW_QUANTITY = 3960;
    public static boolean useItem(String name, String... actions) {
        return Bank.search().withName(name).first().flatMap(item ->
        {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(item, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static boolean useItem(int id, String... actions) {
        return Bank.search().withId(id).first().flatMap(item ->
        {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(item, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static boolean useItem(Predicate<? super Widget> predicate, String... actions) {
        return Bank.search().filter(predicate).first().flatMap(item ->
        {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(item, actions);
            return Optional.of(true);
        }).orElse(false);
    }
    public static void withdrawX(Widget item, int amount){
        if(InteractionApiPlugin.getClient().getVarbitValue(WITHDRAW_QUANTITY)==amount){
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetActionPacket(5,item.getId(),item.getItemId(), item.getIndex());
            return;
        }
        BankInteraction.useItem(item,"Withdraw-X");
        InteractionApiPlugin.getClient().setVarcStrValue(359,Integer.toString(amount));
        InteractionApiPlugin.getClient().setVarcIntValue(5,7);
        InteractionApiPlugin.getClient().runScript(681);
        InteractionApiPlugin.getClient().setVarbit(WITHDRAW_QUANTITY,amount);
    }

    public static boolean useItemIndex(int index, String... actions) {
        return Bank.search().indexIs(index).first().flatMap(item ->
        {
            MousePackets.queueClickPacket();
            WidgetPackets.queueWidgetAction(item, actions);
            return Optional.of(true);
        }).orElse(false);
    }

    public static boolean useItem(Widget item, String... actions) {
        if (item == null) {
            return false;
        }
        MousePackets.queueClickPacket();
        WidgetPackets.queueWidgetAction(item, actions);
        return true;
    }
}
