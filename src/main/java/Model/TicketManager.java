package Model;

import java.util.ArrayList;
import java.util.Collections;

public class TicketManager {
    private final ArrayList<Ticket> tickets;
    private static TicketManager instance = new TicketManager();

    private TicketManager() {
        tickets = new ArrayList<>();
    }

    public static TicketManager getInstance(){
        return instance;
    }

    public void addTicketToList(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicketFromList(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void printAllTickets(){
        for(Ticket t : tickets){
            System.out.println(t.toString());
        }
    }

    /**
     * @author Patrik Brandell
     * @param username - Username of current user
     * Method filters out tickets based on username input and return ArrayList
     * @return List of mytickets for both users and agents
     */
    public ArrayList getMyTickets (String email) {
        ArrayList myTickets = new ArrayList();
        for (Ticket t : tickets) {
            if (t.getUser() != null) {
                if (t.getUser().getEmail().equals(email)) {
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
     * @author Patrik Brandell
     * @return ArrayList with Tickets with no agent
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

    public ArrayList getAllTickets(){

        return tickets;
    }

    /**
     * @author Patrik Brandell
     * @param id - ID from GUI sent from controller
     * @return Ticket with that ID, if not exist null
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
