package bcccp.tickets.season;

import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.IUsageRecordFactory;
import java.util.ArrayList;
import java.util.List;

public class SeasonTicketDAO implements ISeasonTicketDAO {

	private IUsageRecordFactory factory;
        private ISeasonTicket seasonTicket; //had to add this...
        private List<ISeasonTicket> registeredTicketsList = new ArrayList <ISeasonTicket>();
	
	
	public SeasonTicketDAO(IUsageRecordFactory factory) {
		//TOD Implement constructor
                this.factory = factory;
	}



	@Override
	public void registerTicket(ISeasonTicket ticket) {
		// TODO Auto-generated method stub
               registeredTicketsList.add(ticket);
	}



	@Override
	public void deregisterTicket(ISeasonTicket ticket) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int getNumberOfTickets() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public ISeasonTicket findTicketById(String ticketId) {
		// TODO Auto-generated method stub
                
                for(int i = 0; i<registeredTicketsList.size(); i++){
                    if((registeredTicketsList.get(i) != null) && (registeredTicketsList.get(i).getId().matches(ticketId))){
                        return registeredTicketsList.get(i);
                    }
                    else{
                        System.out.println("Ticket not in system");


                    }
                }
                               
                return null;
	}



	@Override
	public void recordTicketEntry(String ticketId) {
		// TODO Auto-generated method stub
                registeredTicketsList.add(findTicketById(ticketId));
	}



	@Override
	public void recordTicketExit(String ticketId) {
		// TODO Auto-generated method stub
		registeredTicketsList.remove(findTicketById(ticketId));
	}
        
        public boolean isSeasonTicketInCarpark(String ticketId){
            
            return true;
        }
	
	
	
}
