package tech.whocodes.jampack.UtilityApi.Debugger;

import com.google.inject.Inject;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.WorldListLoad;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.util.HotkeyListener;
import tech.whocodes.jampack.Interaction.InventoryInteraction;
import tech.whocodes.jampack.InteractionApi.Collections.Inventory;
import tech.whocodes.jampack.InteractionApi.Collections.Widgets;
import tech.whocodes.jampack.InteractionApi.InteractionApiPlugin;
import tech.whocodes.jampack.PacketApi.WidgetInfoExtended;
import tech.whocodes.jampack.Packets.MousePackets;
import tech.whocodes.jampack.Packets.MovementPackets;
import tech.whocodes.jampack.Packets.WidgetPackets;
import tech.whocodes.jampack.UtilityApi.Debugger.events.DebuggerLogMessage;
import tech.whocodes.jampack.UtilityApi.Debugger.modules.KeyLogger;
import tech.whocodes.jampack.UtilityApi.Reflection.Walker;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

@PluginDescriptor(
        name = "<html><font color=\"#990099\">[JD]</font> [DEV] Debugger</html>",
        description = "{{plugin-description}}",
        tags = {"piggy", "ethan", "jampack", "jarromie", "automation", "debug", "debugger"})
@Slf4j
public class DebuggerPlugin extends Plugin {
    @Inject
    private Client client;
    @Inject
    private ClientThread clientThread;
    @Inject
    private PluginManager pluginManager;
    @Inject
    private KeyManager keyManager;
    @Inject
    private ConfigManager configManager;
    @Inject
    private DebuggerConfig config;
    @Inject
    private KeyLogger keyLogger;
    @Inject
    private Walker walker;

    private int regionId = 0;
    private int ticks = 0;

    @Provides
    protected DebuggerConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(DebuggerConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        keyManager.registerKeyListener(output);
        keyManager.registerKeyListener(keyLogger);
        walker.init();;
        // walker.setup();
    }

    @Override
    protected void shutDown() throws Exception {
        keyManager.unregisterKeyListener(output);
        keyManager.registerKeyListener(keyLogger);
    }

    @Subscribe
    public void onGameTick(GameTick event) {
//    if(regionId != client.getLocalPlayer().getWorldLocation().getRegionID() || (++ticks > 100)){
//      regionId = client.getLocalPlayer().getWorldLocation().getRegionID();
//      log("RegionID update: " + regionId);
//      ticks = 0;
//    }
    }

    @Subscribe
    public void onDebuggerLogMessage(DebuggerLogMessage event) {
        log(event.getMessage());
    }

    @Subscribe
    public void onCommandExecuted(CommandExecuted commandExecuted) {
        if (!commandExecuted.getCommand().equals("debugger")) return;
        String[] args = commandExecuted.getArguments();
        
        String command = args[0];

        if (command.equals("plugins")) {
            // SwingUtilities.invokeLater(() -> {
            for (Plugin plugin : pluginManager.getPlugins()) {
                log("Plugin \"" +plugin.getName() + "\""
                        + "(" + plugin.toString() + ") "
                        + (pluginManager.isPluginEnabled(plugin) ? "enabled" : "disabled"));
            }
        }
        if (command.equals("getvarbitvalue")) {
            int varbit = Integer.parseInt(args[1]);
            log("Varbit " + varbit + " value: " + client.getVarbitValue(varbit));
        }
        if (command.equals("setvarbit")) {
            int varbit = Integer.parseInt(args[1]);
            int value = Integer.parseInt(args[2]);
            client.setVarbit(varbit, value);

            log("Varbit " + varbit + " value has been set to " + value);
        }
        if (command.equals("walker")) {
            command = args[1];
            if(command.equals("toggle")){
                log(args[5]);
                walker.toggle();
            }

            if(command.equals("enable")){
                walker.enable();
            }

            if(command.equals("disable")){
                walker.disable();
            }

            if(command.equals("stop")){
                walker.stop();
            }
            
            if(command.equals("walk")){
                walker.walk(new WorldPoint(Integer.parseInt(args[2]), Integer.parseInt(args[3]), (args.length > 4 ? Integer.parseInt(args[4]) : client.getPlane())));
            }
        }
    }

    //        private final KeyListener keylog = new KeyListener() {
    //                @Override
    //                public void keyTyped(KeyEvent e) {
    //                        if(config.enabled() && config.keylogging() &&
    // config.keyloggerKeyTyped()){
    //                                logKeyEvent("keyTyped", e);
    //                        }
    //                }
    //
    //                @Override
    //                public void keyPressed(KeyEvent e) {
    //                        if(config.enabled() && config.keylogging() &&
    // config.keyloggerKeyPressed()){
    //                                logKeyEvent("keyPressed", e);
    //                        }
    //                }
    //
    //                @Override
    //                public void keyReleased(KeyEvent e) {
    //                        if(config.enabled() && config.keylogging() &&
    // config.keyloggerKeyReleased()){
    //                                logKeyEvent("keyReleased", e);
    //                        }
    //                }
    //        };

    private final HotkeyListener output = new HotkeyListener(() -> config.keybindKey()) {
        @Override
        public void hotkeyPressed() {
            //log(client.getWorldList().toString());
            if (config.enabled() && config.keybind() && client.getGameState() == GameState.LOGGED_IN) {
                if (config.keybindCamera()) {
                    log(
                            "Camera - "
                                    + "Yaw: "
                                    + client.getCameraYaw()
                                    + " "
                                    + "[Target: "
                                    + client.getCameraYawTarget()
                                    + "] - "
                                    + "Pitch: "
                                    + client.getCameraPitch()
                                    + " "
                                    + "[Target: "
                                    + client.getCameraPitchTarget()
                                    + "]");
                }

                if (config.keybindLocation()) {
                    if (config.keybindWorldPoint()) {
                        //                                                WorldPoint worldPoint =
                        // client.getLocalPlayer().getWorldLocation();
                        //                                                log("World Location details - "
                        //                                                        + worldPoint.getX() + ", "
                        //                                                        + worldPoint.getY() + ", "
                        //                                                        + worldPoint.getPlane()
                        //                                                        + " - region: "
                        //                                                        + worldPoint.getRegionX()
                        // + ", "
                        //                                                        + worldPoint.getRegionY()
                        //                                                        + " (id: "
                        //                                                        + worldPoint.getRegionID()
                        //                                                        + ")");
                        log(client.getLocalPlayer().getWorldLocation().toString() + " ; getRegionID() - " + client.getLocalPlayer().getWorldLocation().getRegionID());
                    }

                    if (config.keybindLocalPoint()) {
                        //                                                LocalPoint localPoint =
                        // client.getLocalPlayer().getLocalLocation();
                        //                                                log("Local Location details - "
                        //                                                        + localPoint.getX() + ", "
                        //                                                        + localPoint.getY()
                        //                                                        + " - scene "
                        //                                                        + localPoint.getSceneX() +
                        // ", "
                        //                                                        + localPoint.getSceneY());
                        log(client.getLocalPlayer().getLocalLocation().toString());
                    }

                    if (config.keybindRegions()) {
                        // log(client.getMapRegions().toString());
                        log("Map regions: " + Arrays.toString(client.getMapRegions()));
                    }

                    // Widgets.search().filter(widget -> {
                    //        widget.getSpriteId()
                    // });

                }
                // walker.walkTo(new WorldPoint(1758, 3582, 0));
            }

//                                  clientThread.invokeLater(() -> {
//
//           Widgets.search().withId(WidgetInfoExtended.INVENTORY.getPackedId()).first().ifPresent(widget -> {
//                                                  MousePackets.queueClickPacket();
//                                                  WidgetPackets.queueWidgetActionPacket(1,
//           WidgetInfoExtended.INVENTORY.getPackedId(), -1, -1);
//                                          });
//                                  });
        }
    };

    private void log(String s) {
        switch (config.logType()) {
            case CONSOLE_INFO:
                log.info(s);
                break;
            case CHAT:
                clientThread.invoke(() -> {
                    if (client.getGameState() == GameState.LOGGED_IN) InteractionApiPlugin.sendClientMessage(s);
                    else log.info(s);
                });
                break;
            default:
                log.debug(s);
                break;
        }
    }
}
