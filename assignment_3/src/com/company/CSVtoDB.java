package com.company;

import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class CSVtoDB {

    private String userFileName;
    private Connection con;

    public CSVtoDB() {
        userFileName = null;
        con = null;
    }

    public CSVtoDB(String filename, Connection connection) {
        userFileName = filename;
        con = connection;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void initDatabase() throws SQLException{
        clearDatabase();

        //First Name Table
        PreparedStatement s = con.prepareStatement(
                "CREATE IF NOT EXISTS FirstName(" +
                        "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT," + //ID
                        "FName VARCHAR(20))" //FirstName

        );
        s.executeUpdate();

        //Last Name Table
        s = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS LastName(" +
                        "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT," + //ID
                        "LName VARCHAR(20))" //LastName

        );
        s.executeUpdate();

        //Age Table
        s = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Age(" +
                        "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT," + //ID
                        "Age INTEGER)" //Age

        );
        s.executeUpdate();

        //Resident Table
        s = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Resident(" +
                        "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT," + //ID
                        "FirstName VARCHAR(20)," + //FirstName
                        "LastName VARCHAR(20)," + //LastName
                        "Age INTEGER)" //Age

        );
        s.executeUpdate();

        //Email Table
        s = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Email(" +
                        "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                        "Email VARCHAR(20))"

        );
        s.executeUpdate();

        //Area Table (The Area that they are in)
        s = con.prepareStatement(
                "CREATE TABLE IF NOT EXISTS Area(" +
                        "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT," + //ID
                        "City VARCHAR(50)," + //City
                        "State VARCHAR(20))"

        );
        s.executeUpdate();
    }

// File Reader
    public static List<List<String>> readFile(String filename) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filename));
        s.useDelimiter(",");

        List<List<String>> col = new ArrayList<List<String>>();

// Makes sure to use a ',' as a way to differentiate
        while(s.hasNext()) {
            String temp = s.nextLine();
            List<String> row = Arrays.asList(temp.split(","));
            col.add(row);
        }
        s.close();

        return col;
    }

//Parsing Program
    public void ParseToCSV() {
        try {

            List<List<String>> records = readFile(getUserFileName());

            for (List<String> record : records.subList(1, records.size())) {
                String firstName = record.get(0);
                String lastName = record.get(1);
                String age = record.get(2);
                String city = record.get(3);
                String state = record.get(4);
                String email = record.get(5);


                int parameterindex = 1;
                int columnindex = 1;


                //First Name - Inserting Into Table
                String sql1 = "INSERT INTO FirstName(FName) VALUES (?)";
                PreparedStatement stmt = con.prepareStatement( sql1, Statement.RETURN_GENERATED_KEYS);


                stmt.setString(1, firstName);
                stmt.executeUpdate();
                stmt.clearParameters();


                //Last Name - Inserting Into Table
                String sql2 = "INSERT INTO LastName(lname) VALUES (?)";
                PreparedStatement stmt2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

                stmt2.setString(1, lastName);
                stmt2.executeUpdate();
                stmt2.clearParameters();


                //Age- Inserting Into Table
                String sql3 = "INSERT INTO Age(age) VALUES (?)";
                PreparedStatement stmt3 = con.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);

                stmt3.setString(1, age);
                stmt3.executeUpdate();
                stmt3.clearParameters();

                //Resident - Inserting Into Table
                String sql4 = "INSERT INTO Person(FirstName, LastName, Age) VALUES (?,?,?)";
                PreparedStatement stmt4 = con.prepareStatement(sql4);

                stmt4.setString(1, firstName);
                stmt4.setString(2, lastName);
                stmt4.setString(3, age);
                stmt4.executeUpdate();

                //Email - Inserting Into Table
                String sql5 = "INSERT INTO Email(Email) VALUES (?)";
                PreparedStatement stmt5 = con.prepareStatement(sql5);

                stmt5.setString(1, email);
                stmt5.executeUpdate();

                //Area - Inserting Into Table
                String sql6 = "INSERT INTO Area(City, State) VALUES (?,?)";
                PreparedStatement stmt6 = con.prepareStatement(sql6);

                stmt6.setString(1, city);
                stmt6.setString(2, state);
                stmt6.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Clearing the Database, in case of repeated values
    private void clearDatabase() throws SQLException {

        PreparedStatement s = con.prepareStatement("DROP TABLE IF EXISTS FirstName");
        s.executeUpdate();

        s = con.prepareStatement("DROP TABLE IF EXISTS LastName");
        s.executeUpdate();

        s = con.prepareStatement("DROP TABLE IF EXISTS Age");
        s.executeUpdate();

        s = con.prepareStatement("DROP TABLE IF EXISTS Resident");
        s.executeUpdate();

        s = con.prepareStatement("DROP TABLE IF EXISTS Email");
        s.executeUpdate();

        s = con.prepareStatement("DROP TABLE IF EXISTS Area");
        s.executeUpdate();

        s.close();
    }
}