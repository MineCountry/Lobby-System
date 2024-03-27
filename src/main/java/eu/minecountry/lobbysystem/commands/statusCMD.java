package eu.minecountry.lobbysystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import eu.minecountry.lobbysystem.LobbySystem;
import eu.minecountry.lobbysystem.utils.Manager;
import eu.minecountry.lobbysystem.utils.intefaces.LobbyPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAlias("status")
@CommandPermission("lobbysystem.command.status")
@Description("Frage den Status mancher Funktionen der Lobby ab")
public class statusCMD extends BaseCommand {

    @Default
    @CommandCompletion("status")
    public void onDefault(Player player){
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        lobbyPlayer.sendWarning("Nutze \"/status help\" für mehr Informationen");
    }

    @Subcommand("rangetp")
    @Description("Zeigt den aktuellen Status der Einstellungen vom RangeTP")
    public void onStatus(Player player){
        Manager manager = LobbySystem.getInstance().getManager();
        LobbyPlayer lobbyPlayer = manager.getLobbyPlayer(player);

        player.sendMessage(Component.empty());

        boolean isEnabled = manager.getServerSettings().isRangetpEnabled();
        String locname = manager.getServerSettings().getRangetpLocationname();
        boolean existsLocation = manager.getLocationsManager().exists(locname);
        int range = manager.getServerSettings().getRangetp();

        if(isEnabled){
            lobbyPlayer.sendCustom("<gray>Der RangeTP ist</gray> <b><green>aktiviert</green></b>");
        }else {
            lobbyPlayer.sendCustom("<gray>Der RangeTP ist</gray> <b><red>deaktiviert</red></b>");
        }
        lobbyPlayer.sendCustom("<gray>Der Name der Middle-Location lautet <i><yellow>"+locname+"</yellow></i>");
        lobbyPlayer.sendCustom("<gray>Die maximale Distanz von der Middle-Location beträgt <i><yellow>"+range+" Blöcke</yellow></i>");
        if(existsLocation){
            lobbyPlayer.sendCustom("<gray>Die Location</gray> <b><green>existiert</green></b>");
        }else {
            lobbyPlayer.sendCustom("<gray>Die Location</gray> <b><red>existiert nicht</red></b>");
        }
    }

    @HelpCommand
    @Syntax("<Page>")
    @Description("Zeigt diese UI")
    public void onHelp(@NotNull CommandHelp help) {
        help.showHelp();
    }


}
