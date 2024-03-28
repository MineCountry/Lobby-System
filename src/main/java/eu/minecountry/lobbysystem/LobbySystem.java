package eu.minecountry.lobbysystem;

import co.aikar.commands.PaperCommandManager;
import eu.minecountry.lobbysystem.commands.buildCMD;
import eu.minecountry.lobbysystem.commands.setCMD;
import eu.minecountry.lobbysystem.commands.statusCMD;
import eu.minecountry.lobbysystem.listener.*;
import eu.minecountry.lobbysystem.listener.lobbyitems.Teleporter;
import eu.minecountry.lobbysystem.utils.Manager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.Objects;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;
    private Manager manager;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();

        manager = new Manager();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        loadCommands();
        loadListeners();

    }

    private void loadCommands(){
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");
        commandManager.getLocales().setDefaultLocale(Locale.GERMAN);

        commandManager.registerCommand(new setCMD());
        commandManager.registerCommand(new buildCMD());
        commandManager.registerCommand(new statusCMD());
    }

    private void loadListeners(){
        Bukkit.getPluginManager().registerEvents(new ConnectionsListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectItems(), this);
        Bukkit.getPluginManager().registerEvents(new Teleporter(), this);
        Bukkit.getPluginManager().registerEvents(new eu.minecountry.lobbysystem.listener.guis.Teleporter(), this);
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("prefix", "<b><color:#48ff00>Mine</color><color:#31ad00>Country</color></b> <gray>»</gray>");
        getConfig().addDefault("locationspath", "Location.");
        getConfig().addDefault("joinmessage.message", "default");
        getConfig().addDefault("joinmessage.enabled", false);
        getConfig().addDefault("quitmessage.message", "default");
        getConfig().addDefault("quitmessage.enabled", false);
        getConfig().addDefault("rangetp.distance", 200);
        getConfig().addDefault("rangetp.enabled", true);
        getConfig().addDefault("rangetp.locationname", "test");
        getConfig().addDefault("defaultgamemode", "ADVENTURE");
        getConfig().addDefault("Descriptions.Spawn", null);
        getConfig().addDefault("Descriptions.Citybuild", "<i><gray>Das ist eine Beschreibung</gray></i>");
        saveConfig();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Component getPrefix(){
        return MiniMessage.miniMessage().deserialize(Objects.requireNonNull(instance.getConfig().getString("prefix"))+" ");
    }

    public static LobbySystem getInstance() {
        return instance;
    }

    public Manager getManager() {
        return manager;
    }
}
