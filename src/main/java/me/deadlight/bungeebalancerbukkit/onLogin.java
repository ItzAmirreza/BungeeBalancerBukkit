package me.deadlight.bungeebalancerbukkit;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class onLogin implements Listener {

    Random random = new Random();

    //lollolooo player login shod hala azinja jer mikhorim
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
            player.sendPluginMessage(BungeeBalancerBukkit.getPlugin(), "BungeeCord", out.toByteArray());



        } else {
            player.sendMessage(utils.color("&c&lERROR: &7Lobbies are now offline. Try Again Later!"));
        }





    }

    private boolean checkIfOnline(String server) {
        boolean online = false;
        int port = getThePort(server);
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress("127.0.0.1", port), 20);
            // ONLINE
            s.close();
            online = true;
        } catch (UnknownHostException e) {
            // OFFLINE
        } catch (IOException e) {
            // OFFLINE
        }

        return online;
    }

    private int getThePort(String servername) {

        if (servername.equalsIgnoreCase("Lobby1")) {
            return 25598;
        } else if (servername.equalsIgnoreCase("Lobby2")) {
            return 25599;
        } else if (servername.equalsIgnoreCase("Lobby3")) {
            return 25597;
        } else {
            return 25595;
        }

    }




}
