package de.muv1n.jbBot.command.util;

import de.muv1n.jbBot.JBBot;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

public abstract class CommandObject {

    protected final JBBot jbBot;
    public CommandObject(@NotNull JBBot jbBot){
        this.jbBot = jbBot;
    }

    public abstract CommandData getCommand();
    public abstract void respond(SlashCommandInteractionEvent e);
    public abstract void autoComplete(CommandAutoCompleteInteraction e);
    public abstract void interactionButton(ButtonInteractionEvent e);

}
