/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ezrael Powell, Zach Reents, Roman Sepeda
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class DormServer {
    
    public static void main(String[] args)
    {
        ServerSocket welcomeSocket = null;
        int clientNumber = 1;
        
        try
        {
            welcomeSocket = new ServerSocket(38520);
        }
        catch (IOException e)
        {
            System.err.println("Could not listen on port 38520");
            System.exit(1);
        }
        System.out.println("Server listening on port 38520...");
        
        try
        {
            while (true)
            {
                DormServerTask task = new DormServerTask(
                    clientNumber, welcomeSocket.accept());
                new Thread(task).start();
                clientNumber++;
                System.out.println("Waiting for another client...");
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        
        

    }        
}
