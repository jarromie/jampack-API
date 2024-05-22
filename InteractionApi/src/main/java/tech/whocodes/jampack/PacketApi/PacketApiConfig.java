package tech.whocodes.jampack.PacketApi;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("jampackPacketApi")
public interface PacketApiConfig extends Config {
    @ConfigItem(
            keyName = "alwaysOn",
            name = "Always Enabled",
            description = "Makes this plugin and any other [API] plugins always enabled on startup (unless outdated)"
    )
    default boolean alwaysOn() {
        return false;
    }

}