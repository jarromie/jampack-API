package tech.whocodes.jampack.UtilityApi.Reflection;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginManager;

import com.google.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
@Singleton
public class DiscordNotifier {

    @Inject
    private PluginManager pluginManager;

    private Object instance = null;
    private HashMap<String, Method> discordMethods = null;
    private boolean discordNotifierInstalled = true;

    public void webhook(String text) {
        performReflection("webhook1", text);
    }

    private Object performReflection(String methodName, Object... args) {
        if (checkReflection() && discordMethods.containsKey(methodName = methodName.toLowerCase()))
            try {
                return discordMethods.get(methodName).invoke(instance, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        return null;
    }

    private boolean checkReflection() {

        if (!discordNotifierInstalled) {
            return false;
        }

        if (discordMethods != null && instance != null) {
            return true;
        }

        discordMethods = new HashMap<>();
        for (Plugin p : pluginManager.getPlugins()) {
            if (p.getClass().getSimpleName().toLowerCase().equals("discordnotifierplugin")) {
                for (Field f : p.getClass().getDeclaredFields()) {
                    if (f.getName().toLowerCase().equals("discordnotifier")) {
                        f.setAccessible(true);
                        try {
                            instance = f.get(p);
                            for (Method m : instance.getClass().getDeclaredMethods()) {
                                m.setAccessible(true);
                                discordMethods.put(m.getName().toLowerCase() + m.getParameterCount(), m);
                            }
                            return true;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }
            }
        }
        discordNotifierInstalled = false;
        return false;
    }

}