package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Patrik Brandell
 * This class is used to sort Ticketlist depending on input parameters
 */

public class Statistics {

    private TicketManager ticketmngr;
    private UserManager usermngr;
    private ArrayList<Ticket> sortedList;
    private ArrayList<Ticket> allTickets;
    private ArrayList<User> allUsers;

    public Statistics(TicketManager ticketmngr, UserManager usermngr) {
        this.ticketmngr = ticketmngr;
        this.usermngr = usermngr;
        allTickets = this.ticketmngr.getAllTickets();
        allUsers = this.usermngr.getAllUsers();
        sortedList = new ArrayList<>();
    }

    private void sortbyDate(Date startdate, Date enddate) {
        for (Ticket t : allTickets) {
            if (t.getStartdate().after(startdate) && t.getEnddate().before(enddate)) {
                sortedList.add(t);
            }
        }
        removeDuplicates();
    }

    public void sortbyUser(User u) {
        for (Ticket t : allTickets) {
            if (t.getOwner().equals(u)) {
                sortedList.add(t);
            }
        }
        removeDuplicates();
    }

    public void sortbyAgent(User agent) {
        if (agent.getRole().equals("Agent")) {
            for (Ticket t : allTickets) {
                for (User u : t.getAgent()) {
                    if (agent.equals(u)) {
                        sortedList.add(t);
                    }
                }
            }
        }
        removeDuplicates();
    }


    private void removeDuplicates() {
        for (int i = 0; i < sortedList.size(); i++) {
            for (int j = i + 1; j < sortedList.size() - 1; j++) {
                if (sortedList.get(i) == sortedList.get(j)) {
                    sortedList.remove(j);
                }
            }
        }
    }

    public void emptySortedList() {
        sortedList.clear();
    }

    public ArrayList<Ticket> getSortedList() {
        return sortedList;
    }
}
