package tech.whocodes.jampack.UtilityApi.Debugger.modules;

import com.google.inject.Inject;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.input.KeyListener;
import tech.whocodes.jampack.UtilityApi.Debugger.DebuggerConfig;
import tech.whocodes.jampack.UtilityApi.Debugger.events.DebuggerLogMessage;

import java.awt.event.KeyEvent;

public class KeyLogger implements KeyListener {
    @Inject
    DebuggerConfig config;
    @Inject
    EventBus eventBus;

    @Override
    public void keyTyped(KeyEvent e) {
        if(config.enabled() && config.keylogging() && config.keyloggerKeyTyped()){
            log("keyTyped", e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(config.enabled() && config.keylogging() && config.keyloggerKeyPressed()){
            log("keyPressed", e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(config.enabled() && config.keylogging() && config.keyloggerKeyReleased()){
            log("keyReleased", e);
        }
    }

    private void log(String event, KeyEvent e){
        eventBus.post(new DebuggerLogMessage(event
                + "; ID: " + e.getID()
                + "; When: " + e.getWhen()
                + "; Mods: " + e.getModifiersEx()
                + "; Code: " + e.getKeyCode()
                + "; Char: (" + e.getKeyChar() + ")"
                + "; Loc: " + e.getKeyLocation()));
    }
}
