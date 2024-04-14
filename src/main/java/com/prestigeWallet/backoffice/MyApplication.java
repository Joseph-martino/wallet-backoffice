package com.prestigeWallet.backoffice;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("com.prestigeWallet.backoffice");

        register(new MyBinder());
    }
}
