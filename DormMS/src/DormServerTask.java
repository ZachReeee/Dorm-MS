/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zach Reents, Ezrael Powell, Roman Sepeda
 */

import java.io.*;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.ArrayList;

public class DormServerTask implements Runnable
{
    private final Socket socket;
    public ArrayList<Student> studentList = new ArrayList<Student>(20);
    
    public DormServerTask(int clientNumber, Socket socket)
    {
        this.socket = socket;
    }
    
    @Override
    public void run()
    {
        try
        {
            //Client/server stuff
            InetAddress clientAddress = socket.getInetAddress();
            PrintWriter outToClient = new PrintWriter(
                socket.getOutputStream());
            Scanner inFromClient = new Scanner(
                socket.getInputStream());
            
            //This is where we put our logic
            //We can sort and add/remove students here
            
            int choice;
            
            do
            {
                choice = inFromClient.nextInt();
                
                if (choice == 1)
                {

                    String firstName = inFromClient.next();
                    String lastName = inFromClient.next();
                    char gender = inFromClient.next().charAt(0);
                    String major = inFromClient.next();

                    Student student = new Student(firstName, lastName, 
                            gender, major);
                    studentList.add(student);


                    //outToClient.println(studentList);
                    
                    //WRITING TO FILE
                    try 
                    {
                        FileOutputStream fileOut =
                        new FileOutputStream("/temp/students.txt");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(studentList);
                        out.close();
                        fileOut.close();
                    } 
                    catch (IOException i) 
                    {
                        i.printStackTrace();
                    }

                    
                    
                    //READING FROM FILE
                    try 
                    {
                        FileInputStream fileIn = new FileInputStream("/temp/students.txt");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        studentList = (ArrayList) in.readObject();
                        in.close();
                        fileIn.close();
                    }              
                    catch (IOException i) 
                    {
                        i.printStackTrace();
                        return;
                    } 
                    catch (ClassNotFoundException c) 
                    {
                    System.out.println("Student class not found");
                    c.printStackTrace();
                    return;
                    }
                    
                    System.out.println("Serialized student...");
                    System.out.println("First Name: " + student.getFirstName());
                    System.out.println("Last Name: " + student.getLastName());
                    System.out.println("Gender: " + student.getGender());
                    System.out.println("Major: " + student.getMajor());
      
                    outToClient.println(studentList);
                    
                    
                    
                    
                    outToClient.flush();
                }

                else if (choice == 2)
                {
                    String firstName = inFromClient.next();
                    String lastName = inFromClient.next();
                    char gender = inFromClient.next().charAt(0);
                    String major = inFromClient.next();

                    if (studentList.isEmpty())
                    {
                        outToClient.println("There are no students");
                        outToClient.flush();
                    }

                    for (int i = 0; i < studentList.size(); i++)
                    {
                        if (studentList.get(i).getFirstName()
                                .equals(firstName))
                        {
                            if (studentList.get(i).getLastName()
                                    .equals(lastName))
                            {
                                if (studentList.get(i).getGender() == (gender))
                                {
                                    if (studentList.get(i).getMajor()
                                            .equals(major))
                                    {
                                        studentList.remove(i);
                                        outToClient.println(studentList);
                                    }
                                }
                            }
                        }
                        else 
                        {
                            outToClient.println("There is no student with" 
                                    + " that information");
                        }
                    }
                    outToClient.flush();
                }

                //Sort by gender
                else if (choice == 3)
                {
                    
                    for (int i = 0; i < studentList.size(); i++)
                    {
                        if (studentList.get(i).getGender() == 'M')
                        {
                            studentList.remove(studentList.indexOf(i));
                        }
//                        else
//                        {
//                            studentList.set(-1, studentList.get(i));
//                        }
                        
                    }
                    outToClient.println(studentList);
                    outToClient.flush();
                }

                else if (choice == 4)
                {
                    outToClient.println("Choice 4 has been chosen");
                    outToClient.flush();
                }
                
                else if (choice == 5)
                {
                    outToClient.println("Exiting program");
                    outToClient.flush();
                }

            } while (choice != 5);
            
            socket.close();   
        }
        catch(IOException e)
        {
            System.err.println(e);
        }    
    }
    
}
