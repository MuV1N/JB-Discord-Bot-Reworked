package de.muv1n.jbBot.command.util;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.command.BlueEyeCommand;
import de.muv1n.jbBot.command.TheRealJ0shCommand;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
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

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        for (CommandObject object : list){
            object.interactionButton(e);
        }
    }
}
