package de.muv1n.jbBot.events;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class JoinEvent extends ListenerAdapter {
    private final JBBot bot;

    public JoinEvent(JBBot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {

        TextChannel channel = e.getGuild().getTextChannelById(934200485542518855L);

        Random i = new Random();
        int upperbound = 13;
        int x = i.nextInt(upperbound);
        Color[] colors = {Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
                Color.RED, Color.WHITE, Color.YELLOW};

        e.getGuild().addRoleToMember(e.getUser(), Objects.requireNonNull(e.getGuild().getRoleById(1017423926604877865L))).queue();

        MessageTranslation message = bot.getMessage();

        channel.sendMessageEmbeds(message.getEmbed("event.join.embed", Locale.GERMANY)
                .setColor(colors[x])
                        .setTitle(message.get("event.join.embed.title"))
                        .addField("", message.get("event.join.embed.description")
                                .replace("[USER_NAME]", e.getUser().getName())
                                .replace("[MEMBER_COUNT]", e.getGuild().getMemberCount() + ""), false)
                .build()).queue();


    }
}
