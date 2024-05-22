package tech.whocodes.jampack.UtilityApi.Reflection;

import com.google.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginManager;

import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
@Singleton
public class Walker {
    @Inject
    private PluginManager pluginManager;
    @Getter
    private ReflectionHandler reflectionHandler;

    public void init(){
        reflectionHandler = new ReflectionHandler(pluginManager, "walkerplugin");
    }

    public void setup(){
        init();
    }

    public void walk(WorldPoint location){
        reflectionHandler.perform("walkTo1", location);
    }

    public void stop(){
        reflectionHandler.perform("stop0");
    }

    public boolean walking(){
        return (boolean) reflectionHandler.perform("walking0");
    }

    public boolean enabled(){
        return reflectionHandler.enabled();
    }

    public void enable(){
        reflectionHandler.enable();
    }

    public void disable(){
        reflectionHandler.disable();
    }

    public void toggle(){
        reflectionHandler.toggle();
    }

//    private Object instance = null;
//    private HashMap<String, Method> walkerMethods = null;
//    private boolean walkerInstalled = true;

//    public boolean setup(){ return checkReflection(); }
//    public boolean installed(){ return setup(); }
//    public boolean enabled(){
//        Plugin p = getPlugin();
//
//        if(p == null) {
//            return false;
//        }
//
//        return pluginManager.isPluginEnabled(p);
//    }
//    public boolean enable(boolean value){
//        if(!enabled()){
//            return false;
//        }
//
//        pluginManager.setPluginEnabled(getPlugin(), true);
//        return true;
//    }
//
//    public boolean walkTo(WorldPoint target) {
//        return (boolean) performReflection("walkto1", target);
//    }
//    public boolean walk(WorldPoint target){
//        return walkTo(target);
//    }
//    public boolean reset(){
//        return (boolean) performReflection("stop0");
//    }
//    public boolean stop(){
//        return reset();
//    }
//    public boolean walking(){
//        Object result = performReflection("walking0");
//        if(result == null) {
//            return false;
//        }
//
//        return (boolean) result;
//    }
//
//    private Object performReflection(String methodName, Object... args) {
//        if (checkReflection() && walkerMethods.containsKey(methodName = methodName.toLowerCase()))
//            try {
//                return walkerMethods.get(methodName).invoke(instance, args);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//        return null;
//    }
//
//    private Object performReflection(String methodName) {
//        if (checkReflection() && walkerMethods.containsKey(methodName = methodName.toLowerCase()))
//            try {
//                return walkerMethods.get(methodName).invoke(instance);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//        return null;
//    }
//
//    private boolean checkReflection() {
//
//        if (!walkerInstalled) {
//            return false;
//        }
//
//        if (walkerMethods != null && instance != null) {
//            return true;
//        }
//
//        walkerMethods = new HashMap<>();
//        Plugin p = getPlugin();
//        if(p != null){
//            instance = p;
//            for (Method m : p.getClass().getDeclaredMethods()){
//                m.setAccessible(true);
//                walkerMethods.put(m.getName().toLowerCase() + m.getParameterCount(), m);}
//            return true;
//        }
//        walkerInstalled = false;
//        return false;
//    }
//
//    private Plugin getPlugin(){
//        for (Plugin p : pluginManager.getPlugins()) {
//            if (p.getClass().getSimpleName().toLowerCase().equals("walkerplugin")) {
//                return p;
//            }
//        }
//        return null;
//    }

}