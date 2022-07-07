package dtu.project.log4jboot2root;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    public Connector(){};

    private String url = "jdbc:mysql://docker-mysql:3306/app";

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "peter", "strongpassword");
        } catch (Exception e) {
            System.out.println("Exception caught while creating db connection:");
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
