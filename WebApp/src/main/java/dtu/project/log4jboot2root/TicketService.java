package dtu.project.log4jboot2root;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();

    public List getActiveTickets() {
        List list = null;
        try {
            list = ticketDAO.getActiveTickets();
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT IN TICKETSERVICE: getActiveTickets():");
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addTicket(String creator, String description) {
        try {
            ticketDAO.addTicket(creator, description);
        } catch (SQLException e) {
            System.out.println("EXCEPTION CAUGHT IN TICKETSERVICE: getActiveTickets():");
            System.out.println(e.getMessage());
        }
    }

    /*
    public boolean editTicket(String ticketID, String description) {
        return ticketDAO.editTicket(ticketID, description);
    }
     */
    public void deleteTicket(String ticketID) {
        try {
            ticketDAO.deleteTicket(ticketID);
        } catch (SQLException e) {
            System.out.println("EXCEPTION CAUGHT IN TICKETSERVICE: getActiveTickets():");
            System.out.println(e.getMessage());
        }
    }

}
