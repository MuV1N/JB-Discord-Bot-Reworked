package de.muv1n.jbBot;

import de.muv1n.jbBot.command.util.CommandManager;
import de.muv1n.jbBot.events.JoinEvent;
import de.muv1n.jbBot.events.PrivateMessageReceive;
import de.muv1n.jbBot.events.QuitEvent;
import de.muv1n.jbBot.translation.CommonTranslation;
import de.muv1n.jbBot.translation.MessageTranslation;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;

public class JBBot {
    private final JDA bot;
    private final MessageTranslation message;
    private final CommonTranslation common;

    public JBBot(String key) throws LoginException, InterruptedException, IOException {
        JDA bot;
        JDABuilder builder = JDABuilder.createDefault(key);
        ArrayList<GatewayIntent> intent = new ArrayList<>();
        intent.add(GatewayIntent.DIRECT_MESSAGES);intent.add(GatewayIntent.DIRECT_MESSAGE_REACTIONS);intent.add(GatewayIntent.DIRECT_MESSAGE_TYPING);intent.add(GatewayIntent.GUILD_BANS);intent.add(GatewayIntent.GUILD_EMOJIS_AND_STICKERS);intent.add(GatewayIntent.GUILD_MEMBERS);intent.add(GatewayIntent.GUILD_INVITES);intent.add(GatewayIntent.GUILD_MESSAGE_REACTIONS);intent.add(GatewayIntent.GUILD_MESSAGE_TYPING);intent.add(GatewayIntent.GUILD_PRESENCES);intent.add(GatewayIntent.GUILD_VOICE_STATES);intent.add(GatewayIntent.GUILD_WEBHOOKS);intent.add(GatewayIntent.GUILD_MESSAGES);intent.add(GatewayIntent.MESSAGE_CONTENT);

        this.message = new MessageTranslation();
        this.common = new CommonTranslation();
        CommandManager commandManager = new CommandManager();

        builder.enableIntents(intent);

        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.playing(common.get("activity")));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(commandManager);
        builder.addEventListeners(new JoinEvent(this), new QuitEvent(this), new PrivateMessageReceive(this));

        bot = builder.build();
        bot.awaitReady();


        this.bot = bot;
        commandManager.load(this);

        new de.muv1n.jbBot.Stop(bot, builder);
    }

    public JDA getBot() {
        return bot;
    }

    public MessageTranslation getMessage() {
        return message;
    }

    public CommonTranslation getCommon() {
        return common;
    }

}
