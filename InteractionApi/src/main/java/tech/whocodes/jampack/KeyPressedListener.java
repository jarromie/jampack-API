package tech.whocodes.jampack;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.input.KeyListener;
import java.awt.event.KeyEvent;
@Slf4j
public class KeyPressedListener implements KeyListener
{
	@Override
	public void keyTyped(KeyEvent e) {
		logkey("keyTyped", e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		logkey("keyPressed", e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		logkey("keyReleased", e);
	}

	private void logkey(String event, KeyEvent e){
		log.debug(event
				+ "; ID: " + e.getID()
				+ "; When: " + e.getWhen()
				+ "; Mods: " + e.getModifiersEx()
				+ "; Code: " + e.getKeyCode()
				+ "; Char: (" + e.getKeyChar() + ")"
				+ "; Loc: " + e.getKeyLocation());
	}
}
