package com.epam.mjc.io;

import java.io.*;

public class FileReader {

    public Profile getDataFromFile(File file) {
        try (FileInputStream in = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = Integer.parseInt(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = Long.parseLong(value);
                            break;
                        default:
                            System.out.println("Unexpected key: " + key);
                    }
                }
            }

            return new Profile(name, age, email, phone);

        } catch (FileNotFoundException e) {
            // Handle the case where the specified file is not found
            System.err.println("File not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            // Handle general IOException
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Profile profile = fileReader.getDataFromFile(new File("src/main/resources/Profile.txt"));

        if (profile != null) {
            System.out.println("Profile created successfully:");
            System.out.println("Name: " + profile.getName());
            System.out.println("Age: " + profile.getAge());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Phone: " + profile.getPhone());
        } else {
            System.out.println("No data read from the file.");
        }
    }
}
