package dtu.project.log4jboot2root;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class TicketDAO {
    //Make sure the ip is correct by doing docker exec log4j-boot2root_docker-mysql_1 cat /etc/hosts
    //To clear the database volume you can do docker-compose down -v
    Connector connector = new Connector();

    public List<Ticket> getActiveTickets() {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        String query = "SELECT TICKET_ID, CREATED_BY, DESCRIPTION FROM TICKETS GROUP BY TICKET_ID, CREATED_BY, DESCRIPTION";

        try {
            Connection conn = connector.getConnection();
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


    public void addTicket(String creator, String description) {
        String query = String.format("INSERT INTO TICKETS( CREATED_BY, DESCRIPTION)"
                + "values('%1$s' ,'%2$s')", creator, description);
        Connection conn = connector.getConnection();

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
        Connection conn = connector.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            statement.closeOnCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
