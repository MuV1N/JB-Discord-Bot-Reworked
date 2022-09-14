package de.muv1n.jbBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stop {
    public Stop(JDA jda, JDABuilder builder) throws IOException {
        while(true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (reader.readLine().equals("stop")){
                reader.close();
                builder.setStatus(OnlineStatus.OFFLINE);
                jda.shutdown();
                System.exit(0);
            }
        }
    }
}
