package de.muv1n.jbBot.command;

import de.muv1n.jbBot.JBBot;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import okhttp3.internal.http2.Http2Connection;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    private final List<CommandObject> list;
    public CommandManager(){
        this.list = new ArrayList<>();
    }

    public void load(@NotNull JBBot jbBot){

        list.add(new BlueEyeCommand(jbBot));
        list.add(new TheRealJ0shCommand(jbBot));

        CommandListUpdateAction commands = jbBot.getBot().updateCommands();
        for (CommandObject object: list){
            commands = commands.addCommands(object.getCommand());
        }
        commands.queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {

        for (CommandObject object : list){
            if (!object.getCommand().getName().equalsIgnoreCase(e.getInteraction().getName())){
                continue;
            }
            object.respond(e);
        }

    }

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent e) {
        for (CommandObject object : list){
            if (!object.getCommand().getName().equalsIgnoreCase(e.getInteraction().getName())){
                continue;
            }
            object.autoComplete(e);
        }
    }
}
