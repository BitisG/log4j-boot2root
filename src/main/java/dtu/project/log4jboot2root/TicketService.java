package dtu.project.log4jboot2root;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TicketService {
    private TicketDAO ticketDAO = new TicketDAO();

    public List getActiveTickets() {
        return ticketDAO.getActiveTickets();
    }

    public void addTicket(String creator, String description) {
        ticketDAO.addTicket(creator, description);
    }

    /*
    public boolean editTicket(String ticketID, String description) {
        return ticketDAO.editTicket(ticketID, description);
    }
     */
    public void deleteTicket(String ticketID) {
        ticketDAO.deleteTicket(ticketID);
    }

}
