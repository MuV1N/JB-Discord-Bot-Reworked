package de.muv1n.jbBot.events;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class QuitEvent extends ListenerAdapter {
    private final JBBot bot;

    public QuitEvent(JBBot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {

        TextChannel channel = e.getGuild().getTextChannelById(934200485542518855L);

        Random i = new Random();
        int upperbound = 13;
        int x = i.nextInt(upperbound);
        Color[] colors = {Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
                Color.RED, Color.WHITE, Color.YELLOW};

        MessageTranslation message = bot.getMessage();

        channel.sendMessageEmbeds(message.getEmbed("event.quit.embed", Locale.GERMANY)
                .setColor(colors[x])
                        .setTitle(message.get("event.quit.embed.title"))
                        .addField("", message.get("event.quit.embed.description")
                                .replace("[USER_NAME]", e.getUser().getName()), false)
                .build()).queue();


    }
}
