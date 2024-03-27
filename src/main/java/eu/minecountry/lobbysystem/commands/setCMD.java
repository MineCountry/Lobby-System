package eu.minecountry.lobbysystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("set")
@CommandPermission("lobbysystem.command.set")
public class setCMD extends BaseCommand {

    @Default
    @Syntax("<Spawn/Citybuild>")
    @CommandCompletion("spawn|citybuild")
    @Description("Setzt eine neue Location für die Lobby")
    public void onDefault(@NotNull Player player, String arg){
        LobbyPlayer lPlayer = LobbySystem.getInstance().getManager().getLobbyPlayer(player);
        LobbySystem.getInstance().getManager().getLocationsManager().insert(arg, player.getLocation());

        String msg = "Die Location <b><i>%location%</i></b> wurde gesetzt!";

        msg = msg.replace("%location%", arg);

        lPlayer.sendCustom(msg);
    }

    @HelpCommand
    @Syntax("<Page>")
    @Description("Zeigt diese UI")
    public void onHelp(@NotNull CommandHelp help) {
        help.showHelp();
    }

}
