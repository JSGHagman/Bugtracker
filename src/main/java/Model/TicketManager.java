package Model;

import java.util.ArrayList;

public class TicketManager {

    private final ArrayList<Ticket> tickets;

    public TicketManager() {
        tickets = new ArrayList<>();
    }

    public void addTicketToList(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicketFromList(Ticket ticket) {
        tickets.remove(ticket);
    }
}
