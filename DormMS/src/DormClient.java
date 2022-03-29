/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roman Sepeda, Ezrael Powell, & Zach Reents
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class DormClient {
  
    public static void main(String[] args)
    {
        final String SERVER_NAME = "localhost";
        int choice;
        Scanner scan = new Scanner(System.in);
       
        try
        {
            Socket clientToServer = new Socket(SERVER_NAME, 38520);
            PrintWriter outToServer = new PrintWriter(
                clientToServer.getOutputStream());
            Scanner inFromServer = new Scanner(
                clientToServer.getInputStream());
            
            
            System.out.println("**Dorm MS Client Program**\n");
        
            do
            {
                System.out.println("");
                System.out.println("Please choose a command.");
                System.out.println("1) add Student ");
                System.out.println("2) remove Student ");
                System.out.println("3) sort by Gender ");
                System.out.println("4) sort by Major ");
                System.out.print("5) Quit \n");

                choice = scan.nextInt();

                while (choice < 1 || choice > 5)
                {
                    System.out.println("That is not a valid command!");
                    choice = scan.nextInt();
                }

                if (choice == 1)
                {
                    outToServer.println(choice);

                    System.out.print("Enter the first name: ");
                    String firstName = scan.next();
                    System.out.print("\nEnter the last name: ");
                    String lastName = scan.next();
                    System.out.print("\nEnter M or F for Gender: ");
                    char gender = scan.next().charAt(0);
                    System.out.print("\nEnter the Major: ");
                    String major = scan.next();

                    outToServer.println(firstName);
                    outToServer.println(lastName);
                    outToServer.println(gender);
                    outToServer.println(major);
                    outToServer.flush();

                    String studentInfo = inFromServer.nextLine();
                    System.out.println("From server: " + studentInfo);
                }
                else if (choice == 2)
                {
                    outToServer.println(choice);

                    System.out.print("Enter the first name: ");
                    String firstName = scan.next();
                    System.out.print("\nEnter the last name: ");
                    String lastName = scan.next();
                    System.out.print("\nEnter M or F for Gender: ");
                    char gender = scan.next().charAt(0);
                    System.out.print("\nEnter the Major: ");
                    String major = scan.next();

                    outToServer.println(firstName);
                    outToServer.println(lastName);
                    outToServer.println(gender);
                    outToServer.println(major);
                    outToServer.flush();

                    String studentInfo = inFromServer.nextLine();
                    System.out.println("From server: " + studentInfo);
                }
                else if (choice == 3)
                {
                    outToServer.println(choice);
                    outToServer.flush();

                    String genderSort = inFromServer.nextLine();
                    System.out.println("From server: " + genderSort);
                }

                else if (choice == 4)
                {
                    outToServer.println(choice);
                    outToServer.flush();

                    String info = inFromServer.nextLine();
                    System.out.println("From server: " + info);
                }

                else if (choice == 5)
                {
                    outToServer.println(choice);
                    outToServer.flush();

                    String info = inFromServer.nextLine();
                    System.out.println("From server: " + info);
                    System.exit(0);
                }   
            } while (choice != 5);


        clientToServer.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

