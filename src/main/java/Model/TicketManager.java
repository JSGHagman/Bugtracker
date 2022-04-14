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

    /**
     * @Author Patrik Brandell
     * @param username - Username of current user
     * Method filters out tickets based on username input and return ArrayList
     * @return List of mytickets for both users and agents
     */

    public ArrayList getMyTickets (String username) {
        ArrayList myTickets = new ArrayList();
        for (Ticket t : tickets) {
            if (t.getUser().getUsername().equals(username)) {
                myTickets.add(t);
            }
            for (User u : t.getAgent()) {
                if (u.getUsername().equals(username)) {
                    myTickets.add(t);
                }
            }
        }
       return myTickets;
    }

    public ArrayList getUnassignedTickets() {
        ArrayList unassignedTickets = new ArrayList();
        for (Ticket t : tickets) {
            if (t.getAgent().size() == 0 || t.getAgent().equals(null)) {
                    unassignedTickets.add(t);
                }
            }

        return unassignedTickets;
    }

}
