package com.company;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException{

        Connection con = null;

        //Connecting to the User on my Google Cloud
        try {
            con = ConnectToDB.getMySqlConnection("35.247.2.240", "lukebergerA3", "berge156");

            System.out.println("You have connected to the SQL Server");

            if (con.isClosed()) {
                con = ConnectToDB.getMySqlConnection("35.247.2.240", "lukebergerA3", "berge156");
            }

//File that You upload into the program
            String fileName = "/FakerData.csv";
            CSVtoDB csv = new CSVtoDB(fileName, con);
            csv.readFile(fileName);
            csv.initDatabase();
            csv.ParseToCSV();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            con.close();
        }
    }
}