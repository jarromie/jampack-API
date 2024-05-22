package tech.whocodes.jampack.UtilityApi.Debugger;

import net.runelite.client.config.*;
import tech.whocodes.jampack.UtilityApi.Debugger.enums.DebuggerLogType;

@ConfigGroup("jampack-{{plugin-key}}")
public interface DebuggerConfig extends Config {
    @ConfigSection(
            name = "General Plugin Settings",
            description = "Settings for general plugin functionality",
            position = 1000,
            closedByDefault = false
    )
    String generalSection = "General Plugin Settings";

    @ConfigItem(
            keyName = "Enabled",
            name = "Enable Debugging",
            description = "Enable/Disable plugin",
            position = 1001,
            section = generalSection
    )
    default boolean enabled(){ return false; }

    @ConfigItem(
            keyName = "LogType",
            name = "Logging Method",
            description = "How should we log data?\nConsole Debug - log.debug\nConsole Info - log.info\nChat - Chatbox game messages",
            position = 1001,
            section = generalSection
    )
    default DebuggerLogType logType(){ return DebuggerLogType.CONSOLE_DEBUG; }



    @ConfigSection(
            name = "Keybind (Hotkey) Logging Settings",
            description = "Settings for logger output from hotkey",
            position = 2000,
            closedByDefault = false
    )
    String keybindSection = "Keybind (Hotkey) Logging Settings";

    @ConfigItem(
            keyName = "KeybindEnabled",
            name = "Enabled/Disabled",
            description = "Enable or disable keybind logging",
            position = 1,
            section = keybindSection
    )
    default boolean keybind(){ return false; }


    @ConfigItem(
            keyName = "hotkey",
            name = "Hotkey/Keybind",
            description = "Keybind to use for logging",
            position = 2,
            section = keybindSection
    )
    default Keybind keybindKey(){ return Keybind.NOT_SET; }

    @ConfigItem(
            keyName = "keybindCamera",
            name = "Log Camera Positions",
            description = "Log camera positions when keybind is pressed",
            position = 3,
            section = keybindSection
    )
    default boolean keybindCamera(){ return false; }

    @ConfigItem(
            keyName = "keybindLocation",
            name = "Log Location Details",
            description = "Log WorldPoint/LocalPoint and region details",
            position = 4,
            section = keybindSection
    )
    default boolean keybindLocation(){ return false; }

    @ConfigItem(
            keyName = "keybindWorldPoint",
            name = "Log WorldPoint Data",
            description = "Log WorldPoint data for your current location",
            position = 4,
            section = keybindSection
    )
    default boolean keybindWorldPoint(){ return false; }


    @ConfigItem(
            keyName = "keybindLocalPoint",
            name = "Log LocalPoint Details",
            description = "Log LocalPoint data for your current location",
            position = 5,
            section = keybindSection
    )
    default boolean keybindLocalPoint(){ return false; }

    @ConfigItem(
            keyName = "keybindRegions",
            name = "Log Regions Details",
            description = "Log regions for your current location",
            position = 6,
            section = keybindSection
    )
    default boolean keybindRegions(){ return false; }






    @ConfigSection(
            name = "Key Logging Settings",
            description = "Settings for logging key presses",
            position = 3000,
            closedByDefault = true
    )
    String keyloggerSection = "Key Logging Settings";

    @ConfigItem(
            keyName = "Key Logger Description",
            name = "",
            description = "Key Logging Description",
            position = 0,
            section = keyloggerSection
    )
    default String keyloggerDescription() {
        return "When enabled, a KeyListener interface logs all available data from KeyPressed, KeyTyped, and KeyReleased. "
                + "This is useful if you would like to recreate these key presses within your plugin, and want to make sure "
                + "that you are sending the correct (fake) data to the client. "
                + "To see more info about the data that is being logged, check out these links:\n\n"
                + "https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyListener.html \n"
                + "https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyEvent.html\n\n\n"
                + "To see an example of a key press being emulated (faked) from within a plugin, check out the `PressKey` "
                + "method from the NeverLog plugin (the version in jampack is done correctly, the version in Piggy is not)";
    }

    @ConfigItem(
            keyName = "KeyLogging",
            name = "Enable/Disabled",
            description = "Enable/Disable logging KeyPressed, KeyReleased, KeyTyped",
            position = 1,
            section = keyloggerSection
    )
    default boolean keylogging(){ return true; }

    @ConfigItem(
            keyName = "KeyPressed",
            name = "KeyPressed",
            description = "Enable/Disable logging onKeyPressed events",
            position = 2,
            section = keyloggerSection
    )
    default boolean keyloggerKeyPressed(){ return true; }

    @ConfigItem(
            keyName = "KeyTyped",
            name = "KeyTyped",
            description = "Enable/Disable logging onKeyTyped events",
            position = 3,
            section = keyloggerSection
    )
    default boolean keyloggerKeyTyped(){ return true; }

    @ConfigItem(
            keyName = "KeyReleased",
            name = "KeyReleased",
            description = "Enable/Disable logging onKeyReleased events",
            position = 4,
            section = keyloggerSection
    )
    default boolean keyloggerKeyReleased(){ return true; }
}
