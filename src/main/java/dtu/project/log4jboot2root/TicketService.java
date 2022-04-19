package dtu.project.log4jboot2root;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TicketService {
    private int ticketINT = 2;

    private TicketDAO ticketDAO = new TicketDAO();

    public List getActiveTickets() {
        return ticketDAO.getActiveTickets();
    }

    public void addTicket(String creator, String description) {
        //String ticketID = String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999));
        String ticketID = String.valueOf(ticketINT);
        ticketINT = ++ticketINT;
        ticketDAO.addTicket(ticketID, creator, description);
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
