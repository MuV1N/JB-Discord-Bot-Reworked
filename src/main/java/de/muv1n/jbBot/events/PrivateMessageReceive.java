package de.muv1n.jbBot.events;

import de.muv1n.jbBot.JBBot;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Locale;
import java.util.Random;

public class PrivateMessageReceive extends ListenerAdapter {

    private final JBBot bot;

    public PrivateMessageReceive(JBBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {

        TextChannel channel = e.getGuild().getTextChannelById(1007036471028498532L);
        if (channel == null)return;
        if (e.getAuthor().isBot()) return;


        MessageTranslation message = bot.getMessage();

        Random i = new Random();
        int upperbound = 13;
        int x = i.nextInt(upperbound);
        Color[] colors = {Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
                Color.RED, Color.WHITE, Color.YELLOW};

        if (e.isFromType(ChannelType.PRIVATE)){

             String message1 = e.getMessage().getContentStripped();
            channel.sendMessageEmbeds(message.getEmbed("event.privateMessage.embed", Locale.GERMANY)
                     .setColor(colors[x])
                             .setTitle(message.get("event.privateMessage.embed.title"))
                             .addField("", message.get("event.quit.privateMessage.description")
                                     .replace("[USER_NAME]", e.getAuthor().getName())
                                     .replace("[MESSAGE]", message1), false)
                     .build()).queue();

        }
    }
}
