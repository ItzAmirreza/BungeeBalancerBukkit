package me.deadlight.bungeebalancerbukkit.Events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.xephi.authme.events.LoginEvent;
import me.deadlight.bungeebalancerbukkit.Main;
import me.deadlight.bungeebalancerbukkit.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class LoginListener implements Listener {

    Random random = new Random();

    @EventHandler
    public void onPlayerLogin(LoginEvent event) {
        sendPlayerToLobbys(event.getPlayer());
    }

    private void sendPlayerToLobbys(Player player) {

        String[] list = {"Lobby1", "Lobby2", "Lobby3"};
        ArrayList<String> pservers = new ArrayList<>();

        for (String sname : list) {
            if (checkIfOnline(sname)) {
                pservers.add(sname);
            }
        }

        if (pservers.size() != 0) {
            int rand = random.nextInt(pservers.size());
            String server = list[rand];
            final ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server);
            System.out.println("Sending " + player.getName() + " to the " + server);
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
        } else {
            player.sendMessage(Utils.colorize("&c&lERROR: &7Lobbies are now offline. Try Again Later!"));
        }

    }

    private boolean checkIfOnline(String server) {

        boolean online = false;
        int port = getThePort(server);

        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", port), 20);
            s.close();
            online = true;
        } catch (UnknownHostException e) {

        } catch (IOException e) {

        }

        return online;

    }

    private int getThePort(String serverName) {
        return Integer.parseInt(Utils.getConfig(serverName));
    }

}
