package eu.minecountry.lobbysystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("build")
@CommandPermission("lobbysystem.command.build")
public class buildCMD extends BaseCommand {

    @Default
    @Syntax("<Spielername>")
    @CommandCompletion("@players")
    @Description("aktiviere / deaktiviere den Baumodus von dir oder anderen Spielern")
    public void onDefault(Player player, String[] args){
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);
        if(args.length == 0){
            lobbyPlayer.toggleBuilding();
            if(lobbyPlayer.isBuilding()){
                lobbyPlayer.sendSuccess("Dein Baumodus wurde aktiviert!");
                player.setGameMode(GameMode.CREATIVE);
            }else {
                lobbyPlayer.sendSuccess("Dein Baumodus wurde deaktiviert!");
                player.setGameMode(manager.getServerSettings().getDefaultGamemode() != null ? manager.getServerSettings().getDefaultGamemode() : GameMode.SURVIVAL);
            }
        }else if(args.length == 1){
            String targetname = args[0];
            Player target = Bukkit.getPlayer(targetname);

            if(target == null){
                lobbyPlayer.sendError("Der Spieler " + targetname + " wurde nicht gefunden!");
                return;
            }

            LobbyPlayer lobbyTarget = manager.getLobbyPlayer(target);

            if(lobbyTarget == lobbyPlayer){
                lobbyPlayer.toggleBuilding();
                if(lobbyPlayer.isBuilding()){
                    lobbyPlayer.sendSuccess("Dein Baumodus wurde aktiviert!");
                    player.setGameMode(GameMode.CREATIVE);
                }else {
                    lobbyPlayer.sendSuccess("Dein Baumodus wurde deaktiviert!");
                    player.setGameMode(manager.getServerSettings().getDefaultGamemode() != null ? manager.getServerSettings().getDefaultGamemode() : GameMode.SURVIVAL);
                }
                return;
            }

            lobbyTarget.toggleBuilding();
            if(lobbyTarget.isBuilding()){
                lobbyPlayer.sendSuccess("Der Baumodus von "+targetname+" wurde aktiviert!");
                lobbyTarget.sendSuccess("Dein Baumodus wurde von " + player.getName() + " aktiviert!");
                player.setGameMode(GameMode.CREATIVE);
            }else {
                lobbyPlayer.sendSuccess("Der Baumodus von "+targetname+" wurde deaktiviert!");
                lobbyTarget.sendSuccess("Dein Baumodus wurde von " + player.getName() + " deaktiviert!");
                player.setGameMode(manager.getServerSettings().getDefaultGamemode() != null ? manager.getServerSettings().getDefaultGamemode() : GameMode.SURVIVAL);
            }

        }
    }

    @HelpCommand
    @Syntax("<Page>")
    @Description("Zeigt diese UI")
    public void onHelp(@NotNull CommandHelp help) {
        help.showHelp();
    }

}
