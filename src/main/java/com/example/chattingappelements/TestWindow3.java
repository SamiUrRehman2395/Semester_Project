package com.example.chattingappelements;

import java.io.*;

public class TestWindow3 {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File data = new File("tesr.ser");

        if (!data.exists()){
            data.createNewFile();
            System.out.println("File created");
        }




        Contact contact1 = new Contact("Eman", "0311245454");
        contact1.getChatHistory().add(new Message("Hello", "you"));
        contact1.getChatHistory().add(new Message("Hi", "you"));


        Contact ret = read(data);
        for (int i = 0; i < contact1.getChatHistory().size(); i++) {
            System.out.println(contact1.getChatHistory().get(i));
        }


//        write(data,contact1);
    }


    public static void write(File file, Contact contact) {
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contact);
            oos.close();
            fos.close();
            System.out.println("Contact Added succesfully to ser file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static Contact read(File file) {

        Contact contact;
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            contact = (Contact) ois.readObject();
            System.out.println("Contact Read succesfully");

        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return null;
    }


}
