package eu.minecountry.lobbysystem.utils.manager;

import eu.minecountry.lobbysystem.LobbySystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;


public class ServerSettings {

    private final FileConfiguration configuration = LobbySystem.getInstance().getConfig();

    public boolean isJoinmessageEnabled(){
        return configuration.getBoolean("joinmessage.enabled");
    }
    public boolean isQuitmessageEnabled(){
        return configuration.getBoolean("quitmessage.enabled");
    }
    public boolean isRangetpEnabled(){
        return configuration.getBoolean("rangetp.enabled");
    }
    public Component getJoinmessage(){
        return MiniMessage.miniMessage().deserialize(Objects.requireNonNull(configuration.getString("joinmessage.message")));
    }
    public Component getQuitmessage(){
        return MiniMessage.miniMessage().deserialize(Objects.requireNonNull(configuration.getString("quitmessage.message")));
    }
    public GameMode getDefaultGamemode(){
        return GameMode.valueOf(configuration.getString("defaultgamemode"));
    }
    public Integer getRangetp(){
        return configuration.getInt("rangetp.distance");
    }
    public String getRangetpLocationname(){
        return configuration.getString("rangetp.locationname");
    }

}
