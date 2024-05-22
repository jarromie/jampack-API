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

/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         r
 * Reflection utility for Owain's Chin Break Handler:
 * https://github.com/Owain94/OpenOSRS-external-plugins/tree/master/chinbreakhandler
 * <p>
 * Let's you 'optionally' use it, without the dependency in your plugins code.
 * Author: Soxs
 */
@Slf4j
@Singleton
public class BreakingHandler {
    @Inject
    private PluginManager pluginManager;
    private ReflectionHandler reflectionHandler = null;
    
    private Object perform(String methodName, Object... args){
        if(reflectionHandler == null){
            reflectionHandler = new ReflectionHandler(pluginManager, "openbreakhandlerplugin", "openbreakhandler");
        }
        return reflectionHandler.perform(methodName, args);
    }

    public void registerPlugin(Plugin p, boolean configure) {
        perform("registerPlugin2", p, configure);
    }

    public void registerPlugin(Plugin p) {
        perform("registerPlugin1", p);
    }

    public void unregisterPlugin(Plugin p) {
        perform("unregisterPlugin1", p);
    }

    public void startPlugin(Plugin p) {
        perform("startPlugin1", p);
    }

    public void stopPlugin(Plugin p) {
        perform("stopPlugin1", p);
    }

    public boolean isBreakActive(Plugin p) {
        Object o = perform("isBreakActive1", p);
        if (o != null)
            return (boolean) o;
        return false;
    }

    public boolean shouldBreak(Plugin p) {
        Object o = perform("shouldBreak1", p);
        if (o != null) {
            boolean b = (boolean) o;
            return (boolean) o;
        }
        return false;
    }

    public boolean needsBankPin(Client c) {
        Object o = perform("needsBankPin1", c);
        if (o != null) {
            return (boolean) o;
        }

        return false;
    }

    public String getBankPin(ConfigManager configManager) {
        Object o = perform("getBankPin1", configManager);
        if (o != null) {
            return (String) o;
        }

        return null;
    }

    public void startBreak(Plugin p) {
        perform("startBreak1", p);
    }

}