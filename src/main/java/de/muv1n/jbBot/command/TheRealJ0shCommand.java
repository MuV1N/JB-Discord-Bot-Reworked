package de.muv1n.jbBot.command;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.command.util.CommandObject;
import de.muv1n.jbBot.translation.CommonTranslation;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

public class TheRealJ0shCommand extends CommandObject {

    public TheRealJ0shCommand(@NotNull JBBot jbBot){
        super(jbBot);
    }
    @Override
    public CommandData getCommand() {
        CommonTranslation common = jbBot.getCommon();
        // Register Command
        return Commands.slash("therealj0sh", common.get("command.j0sh.description"))
                .addOption(OptionType.STRING, "social", common.get("command.j0sh.social.description", Locale.GERMAN), true, true);
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {
        if (!e.getName().equals("therealj0sh"))return;
        //Respond Message
        MessageTranslation message = jbBot.getMessage();
        User user = e.getUser();

        if (e.getGuild() == null){
            e.replyEmbeds(message.getEmbed("default.unknown.error", Locale.GERMAN)
                    .setColor(Color.RED)
                    .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                    .build()).queue();
            return;
        }
        if (Objects.requireNonNull(e.getOption("social")).getAsString().equals("youtube")){
            e.replyEmbeds(message.getEmbed("command.j0sh.social", Locale.GERMAN)
                            .setColor(Color.RED)
                            .setTitle(jbBot.getCommon().get("command.j0sh.social.embed.youtube.title"))
                            .addField("", jbBot.getCommon().get("command.j0sh.social.embed.youtube.description"), false)
                    .build()).queue();
        } else if (Objects.requireNonNull(e.getOption("social")).getAsString().equals("twitch")) {
            e.replyEmbeds(message.getEmbed("command.j0sh.social", Locale.GERMAN)
                    .setColor(Color.RED)
                    .setTitle(jbBot.getCommon().get("command.j0sh.social.embed.twitch.title"))
                    .addField("", jbBot.getCommon().get("command.j0sh.social.embed.twitch.description"), false)
                    .build()).queue();
        }
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {
        if (!e.getName().equals("therealj0sh") || !e.getFocusedOption().getName().equals("social")) return;
        //Auto Completable words
        String[] words = new String[]{"youtube", "twitch"};

        List<Command.Choice> options = Stream.of(words)
                .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                .map(word -> new Command.Choice(word, word))
                .toList();
        e.replyChoices(options).queue();
    }
    @Override
    public void interactionButton(ButtonInteractionEvent e) {
        return;
    }
}
