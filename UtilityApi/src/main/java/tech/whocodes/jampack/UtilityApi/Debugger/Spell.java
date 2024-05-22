package tech.whocodes.jampack.UtilityApi.Debugger;


import lombok.Getter;
import net.runelite.api.SpriteID;

public enum Spell {
    VARROCK_TELEPORT("Varrock Teleport", 14286871, SpriteID.SPELL_VARROCK_TELEPORT, SpriteID.SPELL_VARROCK_TELEPORT_DISABLED, 25, -1, "Grand Exchange"),
    LUMBRIDGE_TELEPORT("Lumbridge Teleport", 14286874, SpriteID.SPELL_LUMBRIDGE_TELEPORT, SpriteID.SPELL_LUMBRIDGE_TELEPORT_DISABLED, 31, -1),
    FALADOR_TELEPORT("Falador Teleport", 14286877,  SpriteID.SPELL_FALADOR_TELEPORT, SpriteID.SPELL_FALADOR_TELEPORT_DISABLED, 37, -1),
    TELEPORT_TO_HOUSE("Teleport to House", 14286879, SpriteID.SPELL_TELEPORT_TO_HOUSE, SpriteID.SPELL_TELEPORT_TO_HOUSE_DISABLED, 40, -1),
    CAMELOT_TELEPORT("Camelot Teleport", 14286882, SpriteID.SPELL_CAMELOT_TELEPORT, SpriteID.SPELL_CAMELOT_TELEPORT_DISABLED, 45, -1, "Seers'"),
    KOUREND_CASTLE_TELEPORT("Kourend Castle Teleport", 14286884, SpriteID.SPELL_TELEPORT_TO_KOUREND, SpriteID.SPELL_TELEPORT_TO_KOUREND_DISABLED, 48, -1),
    ARDOUGNE_TELEPORT("Ardougne Teleport", 14286889, SpriteID.SPELL_ARDOUGNE_TELEPORT, SpriteID.SPELL_ARDOUGNE_TELEPORT_DISABLED, 51, -1),
    WATCHTOWER_TELEPORT("Watchtower Teleport", 14286895, SpriteID.SPELL_WATCHTOWER_TELEPORT, SpriteID.SPELL_WATCHTOWER_TELEPORT_DISABLED, 58, -1),
    TROLLHEIM_TELEPORT("Trollheim Teleport", 14286902, SpriteID.SPELL_TROLLHEIM_TELEPORT, SpriteID.SPELL_TROLLHEIM_TELEPORT_DISABLED, 61, -1),
    CIVITAS_ILLA_FORTIS_TELEPORT("Civitas illa Fortis Teleport", 14286891, SpriteID.SPELL_CIVITAS_ILLA_FORTIS_TELEPORT, 417, 54, -1),
    WIND_STRIKE("Wind Strike", 14286856, SpriteID.SPELL_WIND_STRIKE, SpriteID.SPELL_WIND_STRIKE_DISABLED, 1, 1),
    WATER_STRIKE("Water Strike", 14286859, SpriteID.SPELL_WATER_STRIKE, SpriteID.SPELL_WATER_STRIKE_DISABLED, 5, 2),
    EARTH_STRIKE("Earth Strike", 14286862, SpriteID.SPELL_EARTH_STRIKE, SpriteID.SPELL_EARTH_STRIKE_DISABLED, 9, 3),
    FIRE_STRIKE("Fire Strike", 14286864, SpriteID.SPELL_FIRE_STRIKE, SpriteID.SPELL_FIRE_STRIKE_DISABLED, 13, 4),
    WIND_BOLT("Wind Bolt", 14286866, SpriteID.SPELL_WIND_BOLT, SpriteID.SPELL_WIND_BOLT_DISABLED, 17, 5),
    WATER_BOLT("Water Bolt", 14286870, SpriteID.SPELL_WATER_BOLT, SpriteID.SPELL_WATER_BOLT_DISABLED, 23, 6),
    EARTH_BOLT("Earth Bolt", 14286873, SpriteID.SPELL_EARTH_BOLT, SpriteID.SPELL_EARTH_BOLT_DISABLED, 29, 7),
    FIRE_BOLT("Fire Bolt", 14286876, SpriteID.SPELL_FIRE_BOLT, SpriteID.SPELL_FIRE_BOLT_DISABLED, 35, 8),
    WIND_BLAST("Wind Blast", 14286880, SpriteID.SPELL_WIND_BLAST, SpriteID.SPELL_WIND_BLAST_DISABLED, 41, 9),
    WATER_BLAST("Water Blast", 14286883, SpriteID.SPELL_WATER_BLAST, SpriteID.SPELL_WATER_BLAST_DISABLED, 47, 10),
    EARTH_BLAST("Earth Blast", 14286890, SpriteID.SPELL_EARTH_BLAST, SpriteID.SPELL_EARTH_BLAST_DISABLED, 53, 11),
    FIRE_BLAST("Fire Blast", 14286896, SpriteID.SPELL_FIRE_BLAST, SpriteID.SPELL_FIRE_BOLT_DISABLED, 59, 12),
    WIND_WAVE("Wind Wave", 14286903, SpriteID.SPELL_WIND_WAVE, SpriteID.SPELL_WIND_WAVE_DISABLED, 62, 13),
    WATER_WAVE("Water Wave", 14286906, SpriteID.SPELL_WATER_WAVE, SpriteID.SPELL_WATER_WAVE_DISABLED, 65, 14),
    EARTH_WAVE("Earth Wave", 14286910, SpriteID.SPELL_EARTH_WAVE, SpriteID.SPELL_EARTH_WAVE_DISABLED, 70, 15),
    FIRE_WAVE("Fire Wave", 14286913, SpriteID.SPELL_FIRE_WAVE, SpriteID.SPELL_FIRE_WAVE_DISABLED, 75, 16),
    WIND_SURGE("Wind Surge", 14286917, SpriteID.SPELL_WIND_SURGE, SpriteID.SPELL_WIND_SURGE_DISABLED, 81, 48),
    WATER_SURGE("Water Surge", 14286919, SpriteID.SPELL_WATER_SURGE, SpriteID.SPELL_WATER_SURGE_DISABLED, 85, 49),
    EARTH_SURGE("Earth Surge", 14286924, SpriteID.SPELL_EARTH_SURGE, SpriteID.SPELL_EARTH_SURGE_DISABLED, 90, 50),
    FIRE_SURGE("Fire Surge", 14286926, SpriteID.SPELL_FIRE_SURGE, SpriteID.SPELL_FIRE_SURGE_DISABLED, 95, 51);
    // i am completely guessing the varbit 276 value for fire surge; the others are tested and verified manually, but i dont have 95 mage to check fire surge

    private static final int SPELLBOOK_WIDGET_ID = 14286851;

    @Getter
    private final String name;
    @Getter
    private final String formattedName;
    @Getter
    private final int widgetId;
    @Getter
    private final int parentId;
    @Getter
    private final int spriteId;
    @Getter
    private final int disabledSpriteId;
    @Getter
    private final int levelRequirement;
    @Getter
    private final int varbit276Value;
    @Getter
    private final String alternateAction;

    Spell(String name, int widgetId, int spriteId, int disabledSpriteId, int levelRequirement, int varbit276Value, String alternateAction){
        this.name = name;
        this.formattedName = "<col=00ff00>" + name + "</col>";
        this.widgetId = widgetId;
        this.parentId = SPELLBOOK_WIDGET_ID;
        this.spriteId = spriteId;
        this.disabledSpriteId = disabledSpriteId;
        this.levelRequirement = levelRequirement;
        this.varbit276Value = varbit276Value;
        this.alternateAction = alternateAction;
    }

    Spell(String name, int widgetId, int spriteId, int disabledSpriteId, int levelRequirement, int varbit276Value){
        this.name = name;
        this.formattedName = "<col=00ff00>" + name + "</col>";
        this.widgetId = widgetId;
        this.parentId = SPELLBOOK_WIDGET_ID;
        this.spriteId = spriteId;
        this.disabledSpriteId = disabledSpriteId;
        this.levelRequirement = levelRequirement;
        this.varbit276Value = varbit276Value;
        this.alternateAction = null; // we could set this to `Cast` to avoid possible errors i guess
    }
}
