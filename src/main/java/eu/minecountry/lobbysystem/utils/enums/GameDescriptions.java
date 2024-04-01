package eu.minecountry.lobbysystem.utils.enums;

import eu.minecountry.lobbysystem.LobbySystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public enum GameDescriptions {

    SPAWN("Spawn"),
    CITYBUILD("Citybuild");

    private String path;

    GameDescriptions(String path) {
        this.path = path;
    }

    public Component description(){
        String string = LobbySystem.getInstance().getConfig().getString("Descriptions."+path);
        if(string == null)return null;
        return MiniMessage.miniMessage().deserialize(string);
    }

}
