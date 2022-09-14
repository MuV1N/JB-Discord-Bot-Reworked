package de.muv1n.jbBot;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        new JBBot(Token.getTestToken());
    }

}
