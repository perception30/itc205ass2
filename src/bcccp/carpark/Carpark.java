package bcccp.carpark;

import java.util.List;

import bcccp.tickets.adhoc.IAdhocTicket;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.ISeasonTicketDAO;

public class Carpark implements ICarpark {
	
	private List<ICarparkObserver> observers;
	private String carparkId;
	private int capacity;
	private int numberOfCarsParked;
	private IAdhocTicketDAO adhocTicketDAO;
	private ISeasonTicketDAO seasonTicketDAO;
        private IAdhocTicket ticket;
	
	
	
	public Carpark(String name, int capacity, 
			IAdhocTicketDAO adhocTicketDAO, 
			ISeasonTicketDAO seasonTicketDAO) {
            this.capacity = capacity;
            this.carparkId = name;
            this.adhocTicketDAO = adhocTicketDAO;
            this.seasonTicketDAO = seasonTicketDAO;
		
	}



	@Override
	public void register(ICarparkObserver observer) {
		// TODO Auto-generated method stub
                observers.add(observer);
	}



	@Override
	public void deregister(ICarparkObserver observer) {
		// TODO Auto-generated method stub
                observers.remove(observer);
		
	}



	@Override
	public String getName() {
		
		return this.carparkId;
        

	}



	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
                
		return (this.capacity >= this.numberOfCarsParked);
	}



	@Override
	public IAdhocTicket issueAdhocTicket() {
		// TODO Auto-generated method stub
                ticket = adhocTicketDAO.createTicket(carparkId);
                
		return ticket;
	}



	@Override
	public void recordAdhocTicketEntry() {
		// TODO Auto-generated method stub
            ticket.enter(System.currentTimeMillis());
		
	}



	@Override
	public IAdhocTicket getAdhocTicket(String barcode) {
		ticket = adhocTicketDAO.findTicketByBarcode(barcode);
		return ticket;
	}



	@Override
	public float calculateAddHocTicketCharge(long entryDateTime) {
		// TODO Auto-generated method stub
               
                
		return 0;
	}



	@Override
	public void recordAdhocTicketExit() {
		// TODO Auto-generated method stub
            ticket.exit(System.currentTimeMillis());
		
	}



	@Override
	public void registerSeasonTicket(ISeasonTicket seasonTicket) {
		// TODO Auto-generated method stub
		this.seasonTicketDAO.registerTicket(seasonTicket);
	}



	@Override
	public void deregisterSeasonTicket(ISeasonTicket seasonTicket) {
		this.seasonTicketDAO.deregisterTicket(seasonTicket);
		
	}



	@Override
	public boolean isSeasonTicketValid(String ticketId) {
		// TODO Auto-generated method stub
                ISeasonTicket s = seasonTicketDAO.findTicketById(ticketId);
                
		return (s.getEndValidPeriod() > System.currentTimeMillis());
	}



	@Override
	public boolean isSeasonTicketInUse(String ticketId) {
		// TODO Auto-generated method stub
                ISeasonTicket s = seasonTicketDAO.findTicketById(ticketId);
		return s.inUse();
	}



	@Override
	public void recordSeasonTicketEntry(String ticketId) {
		// TODO Auto-generated method stub
                
		seasonTicketDAO.recordTicketEntry(ticketId);
	}



	@Override
	public void recordSeasonTicketExit(String ticketId) {
		// TODO Auto-generated method stub
		seasonTicketDAO.recordTicketExit(ticketId);
               
	}

	
	

}
