package dtu.project.log4jboot2root;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class TicketDAO {
    //Make sure the ip is correct by doing docker exec log4j-boot2root_docker-mysql_1 cat /etc/hosts
    //To clear the database volume you can do docker-compose down -v
    private String url = "jdbc:mysql://172.19.0.2:3306/app";

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

    public List<Ticket> getActiveTickets() {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        String query = "SELECT TICKET_ID, CREATED_BY, DESCRIPTION FROM TICKETS GROUP BY TICKET_ID, CREATED_BY, DESCRIPTION";
        Connection conn = getConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketID(resultSet.getString(1));
                ticket.setCreatedBy(resultSet.getString(2));
                ticket.setDescription(resultSet.getString(3));
                ticketList.add(ticket);
            }

            statement.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT:");
            System.out.println(e.getMessage());
        }
        return ticketList;
    }

    public void addTicket(String ticketID, String creator, String description) {
        String query = String.format("INSERT INTO TICKETS(TICKET_ID, CREATED_BY, DESCRIPTION)"
                + "values('%1$s', '%2$s', '%3$s')", ticketID, creator, description);
        Connection conn = getConnection();

        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            statement.closeOnCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTicket(String ticketID) {
        String query = String.format("DELETE FROM TICKETS WHERE TICKET_ID = '%1$s'", ticketID);
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            statement.closeOnCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getLatestID() {
        String query = "SELECT MAX(TICKET_ID) FROM TICKETS";
        Connection conn = getConnection();
        int ID = 1;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);

            ID =  Integer.getInteger(resultSet.getString(1));

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketID(resultSet.getString(1));
            }

            statement.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT:");
            System.out.println(e.getMessage());
        }
        return ID;
    }


}
