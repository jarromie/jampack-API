package tech.whocodes.jampack.UtilityApi.Reflection;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectionHandler {
    private PluginManager pluginManager;
    private Object instance = null;
    private HashMap<String, Method> methods = null;
    private boolean installed = true;
    String pluginName;
    String fieldName;

    ReflectionHandler(PluginManager pluginManager, String pluginName, String fieldName){
        this.pluginManager = pluginManager;
        this.pluginName = pluginName;
        this.fieldName = fieldName;
    }

    ReflectionHandler(PluginManager pluginManager, String pluginName){
        this.pluginManager = pluginManager;
        this.pluginName = pluginName;
    }

    public Object perform(String methodName, Object... args) {
        if (check() && methods.containsKey(methodName = methodName.toLowerCase()))
            try {
                return methods.get(methodName).invoke(instance, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        return null;
    }

    public Object perform(String methodName) {
        if (check() && methods.containsKey(methodName = methodName.toLowerCase()))
            try {
                return methods.get(methodName).invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        return null;
    }

    public boolean check() {
        if (!installed) {
            return false;
        }

        if (methods != null && instance != null) {
            return true;
        }

        methods = new HashMap<>();
        Plugin p = plugin();
        Method[] search = null;
        if(p != null){
            if(fieldName.length() > 0){
                for (Field f : p.getClass().getDeclaredFields()) {
                    if (f.getName().toLowerCase().equals(fieldName)) {
                        f.setAccessible(true);
                        try {
                            instance = f.get(p);
                        }catch(IllegalAccessException e){
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                instance = p;
            }

            if(instance == null){
                return false;
            }

            for (Method m : instance.getClass().getDeclaredMethods()){
                m.setAccessible(true);
                methods.put(m.getName().toLowerCase() + m.getParameterCount(), m);}
            return true;
        }
        installed = false;
        return false;
    }

    public boolean enabled(){
        Plugin p = plugin();

        if(p == null) {
            return false;
        }

        return (pluginManager.isPluginEnabled(p) && check());
    }

    public void enable(){
        if(!enabled()){
            return;
        }

        pluginManager.setPluginEnabled(plugin(), true);
    }

    public void disable(){
        if(plugin() == null){
            return;
        }

        pluginManager.setPluginEnabled(plugin(), false);
    }

    public void toggle(){
        if(plugin() == null){
            return;
        }

        if(enabled()){
            disable();
        }else{
            enable();
        }
    }

    public Plugin plugin(){
        for (Plugin p : pluginManager.getPlugins()) {
            if (p.getClass().getSimpleName().toLowerCase().equals(pluginName)) {
                return p;
            }
        }
        return null;
    }
}
