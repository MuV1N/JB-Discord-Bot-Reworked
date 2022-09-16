package de.muv1n.jbBot.command.util;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.translation.CommonTranslation;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.entities.TextChannel;
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

public class BanCommand extends CommandObject {

    public BanCommand(@NotNull JBBot jbBot){
        super(jbBot);
    }
    @Override
    public CommandData getCommand() {
        CommonTranslation common = jbBot.getCommon();
        // Register Command
        return Commands.slash("ban", common.get("command.ban.description"))
                .addOption(OptionType.USER, "name", common.get("command.ban.user.description", Locale.GERMAN), true, false)
                .addOption(OptionType.STRING, "reason", common.get("command.ban.reason.description"), false, false);
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {
        if (!e.getName().equals("blueeye"))return;
        //Respond Message
        MessageTranslation message = jbBot.getMessage();
        User user = e.getUser();
        TextChannel logs = Objects.requireNonNull(e.getGuild()).getTextChannelById(934237422785544242L);
        if (logs == null) return;

        if (e.getGuild() == null){
            e.replyEmbeds(message.getEmbed("default.unknown.error", Locale.GERMAN)
                    .setColor(Color.RED)
                    .setAuthor(user.getName(), null, user.getEffectiveAvatarUrl())
                    .build()).queue();
            return;
        }
        if (e.getOption("reason") != null){
            String reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
            User user1 = Objects.requireNonNull(e.getOption("name")).getAsUser();
            user1.openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessageEmbeds(message.getEmbed("command.ban.privateMessage.withReason.embed", Locale.GERMANY)
                            .setColor(Color.RED)
                            .setTitle(message.get("command.ban.privateMessage.withReason.embed.title"))
                            .addField("", message.get("command.ban.privateMessage.withReason.embed.description")
                                    .replace("[DISCORD_NAME]", e.getGuild().getName())
                                    .replace("[REASON]", reason), false)
                    .build())).queue();
            logs.sendMessageEmbeds(message.getEmbed("command.ban.log.withReason.embed", Locale.GERMANY)
                            .setColor(Color.GREEN)
                            .setTitle(message.get("command.ban.log.withReason.embed.title"))
                            .addField("", message.get("command.ban.log.withReason.embed.description")
                                    .replace("[BANED_USER_NAME]", Objects.requireNonNull(e.getOption("name")).getAsUser().getName())
                                    .replace("[USER_NAME]", e.getUser().getName())
                                    .replace("[REASON]", reason), false)
                    .build()).queue();
        }
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {
        return;
    }

    @Override
    public void interactionButton(ButtonInteractionEvent e) {
        return;
    }

}
