package de.muv1n.jbBot.command;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.translation.CommonTranslation;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
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

public class BlueEyeCommand extends CommandObject{

    public BlueEyeCommand(@NotNull JBBot jbBot){
        super(jbBot);
    }
    @Override
    public CommandData getCommand() {
        CommonTranslation common = jbBot.getCommon();
        // Register Command
        return Commands.slash("blueeye", common.get("command.blueEye.description"))
                .addOption(OptionType.STRING, "social", common.get("command.blueEye.social.description", Locale.GERMAN), true, true);
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {
        if (!e.getName().equals("blueeye"))return;
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
            e.replyEmbeds(message.getEmbed("command.blueeye.social", Locale.GERMAN)
                            .setColor(Color.RED)
                            .setTitle(jbBot.getCommon().get("command.blueEye.social.embed.youtube.title"))
                            .addField("", jbBot.getCommon().get("command.blueEye.social.embed.youtube.description"), false)
                    .build()).queue();
        } else if (Objects.requireNonNull(e.getOption("social")).getAsString().equals("twitch")) {
            e.replyEmbeds(message.getEmbed("command.blueeye.social", Locale.GERMAN)
                    .setColor(Color.RED)
                    .setTitle(jbBot.getCommon().get("command.blueEye.social.embed.twitch.title"))
                    .addField("", jbBot.getCommon().get("command.blueEye.social.embed.twitch.description"), false)
                    .build()).queue();
        } else if (Objects.requireNonNull(e.getOption("social")).getAsString().equals("website")) {
            e.replyEmbeds(message.getEmbed("command.blueeye.social", Locale.GERMAN)
                    .setColor(Color.RED)
                    .setTitle(jbBot.getCommon().get("command.blueEye.social.embed.website.title"))
                    .addField("", jbBot.getCommon().get("command.blueEye.social.embed.website.description"), false)
                    .build()).queue();
        }
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {
        if (!e.getName().equals("blueeye") || !e.getFocusedOption().getName().equals("social")) return;
        //Auto Completable words
        String[] words = new String[]{"youtube", "twitch", "website"};

        List<Command.Choice> options = Stream.of(words)
                .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                .map(word -> new Command.Choice(word, word))
                .toList();
        e.replyChoices(options).queue();
    }
}
