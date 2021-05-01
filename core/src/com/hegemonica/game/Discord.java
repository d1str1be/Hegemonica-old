package com.hegemonica.game;

import com.badlogic.gdx.Gdx;
import com.hegemonica.game.localization.LocalizationKeys;
import com.hegemonica.game.localization.LocalizationManager;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class Discord {
    final String appID = "837736520789262386";

    DiscordRPC rpc;
    LocalizationManager discordRPCLoc;
    DiscordRichPresence discordPresence;
    public Discord(){
        startup();
        discordRPCLoc = new LocalizationManager();
    }

    public void startup() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                Gdx.app.log(Log.Tags.HEGEMONICA, "Welcome " + user.username + "#" + user.discriminator + "!");
            }
        }).build();

        discordPresence = new DiscordRichPresence();

        rpc = new DiscordRPC();
        rpc.discordInitialize(appID, handlers, true);
    }

    public void onMainMenu(){
        rpc.discordClearPresence();
        discordPresence.state = discordRPCLoc.getString(LocalizationKeys.Keys.In_Main_Menu);
        discordPresence.startTimestamp = System.currentTimeMillis();
        discordPresence.largeImageKey = "hegemonicalogo";
        rpc.discordUpdatePresence(discordPresence);
    }

    public void onPlaying(){
        rpc.discordClearPresence();
        DiscordRichPresence discordPresence = new DiscordRichPresence();
        discordPresence.state = discordRPCLoc.getString(LocalizationKeys.Keys.Playing);
        discordPresence.startTimestamp = System.currentTimeMillis();
        discordPresence.largeImageKey = "hegemonicalogo";
        rpc.discordUpdatePresence(discordPresence);
    }

    public void stopRPC(){
        rpc.discordShutdown();
        rpc.discordClearPresence();
    }
}
