package de.muv1n.jbBot.translation;

import net.dv8tion.jda.api.entities.Guild;

public class CommonTranslation extends Translation{

    public CommonTranslation(){
        super("common", CommonTranslation.class.getClassLoader());
    }

    public String get(String key, Guild guild, Object... format) {
        return super.get(key, guild.getLocale(), format);
    }
}
