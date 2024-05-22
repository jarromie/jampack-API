package tech.whocodes.jampack.UtilityApi.Debugger.enums;

public enum DebuggerLogType {
    CONSOLE_DEBUG("Console - Debug"),
    CONSOLE_INFO("Console - Info"),
    CHAT("Chat - Game Message");

    private final String name;

    DebuggerLogType(String name){
        this.name = name;
    }
}
