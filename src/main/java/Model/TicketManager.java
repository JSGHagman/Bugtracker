package Model;

import java.util.ArrayList;

public class TicketManager {
    private final ArrayList<Ticket> tickets;
    private static TicketManager instance = new TicketManager();

    private TicketManager() {
        tickets = new ArrayList<>();
    }

    public static TicketManager getInstance() {
        return instance;
    }

    public void addTicketToList(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicketFromList(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void printAllTickets() {
        for (Ticket t : tickets) {
            System.out.println(t.toString());
        }
    }

    /**
     * @param username - Username of current user
     *                 Method filters out tickets based on username input and return ArrayList
     * @return List of mytickets for both users and agents
     * @author Patrik Brandell
     */
    public ArrayList getMyTickets(String email) {
        ArrayList myTickets = new ArrayList();
        for (Ticket t : tickets) {
            if (t.getOwner() != null) {
                if (t.getOwner().getEmail().equals(email)) {
                    myTickets.add(t);
                }
                for (User u : t.getAgent()) {
                    if (u.getEmail().equals(email)) {
                        myTickets.add(t);
                    }
                }
            }
        }
        return myTickets;
    }

    /**
     * @return ArrayList with Tickets with no agent
     * @author Patrik Brandell
     */
    public ArrayList getUnassignedTickets() {
        ArrayList unassignedTickets = new ArrayList();
        for (Ticket t : tickets) {
            if (t.getAgent().size() == 0 || t.getAgent().equals(null)) {
                unassignedTickets.add(t);
                System.out.print(t);
            }
        }
        return unassignedTickets;
    }

    public ArrayList getAllTickets() {
        return tickets;
    }

    /**
     * @param id - ID from GUI sent from controller
     * @return Ticket with that ID, if not exist null
     * @author Patrik Brandell
     */
    public Ticket getTicket(int id) {
        Ticket ticket = null;
        for (Ticket t : tickets) {
            if (t.getId() == id) {
                ticket = t;
            }
        }
        return ticket;
    }
}
