package bcccp.carpark;

import java.util.List;

import bcccp.tickets.adhoc.IAdhocTicket;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.ISeasonTicketDAO;
import java.util.Calendar;
import java.util.Date;

public class Carpark implements ICarpark {
	
	private List<ICarparkObserver> observers;
	private String carparkId;
	private int capacity;
	private int numberOfCarsParked;
	private IAdhocTicketDAO adhocTicketDAO;
	private ISeasonTicketDAO seasonTicketDAO;
        
        private ICarpark controller;
	
	
	
	public Carpark(String name, int capacity, 
			IAdhocTicketDAO adhocTicketDAO, 
			ISeasonTicketDAO seasonTicketDAO) {
		
            this.carparkId = name;
            this.capacity = capacity;
	}



	@Override
	public void register(ICarparkObserver observer) {
		
		
	}



	@Override
	public void deregister(ICarparkObserver observer) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getName() {
		return carparkId;
	}



	@Override
	public boolean isFull() {
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //returns day of week as int, 1 is Sunday and 7 is Saturday
            if(dayOfWeek != 1 || dayOfWeek != 7){ 
            //if it is not a weekend            
		if(numberOfCarsParked >= (capacity*.9))
                    return true;
                else
		return false;
            }
            if(dayOfWeek == 1 || dayOfWeek == 7){
            //if it is a weekend
                if(numberOfCarsParked >= capacity)
                    return true;
                else
                    return false;
            }
            else
		return false;
	}



	@Override
	public IAdhocTicket issueAdhocTicket() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void recordAdhocTicketEntry() {
            numberOfCarsParked++;
	}



	@Override
	public IAdhocTicket getAdhocTicket(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public float calculateAddHocTicketCharge(long entryDateTime) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void recordAdhocTicketExit() {
		numberOfCarsParked--;
		
	}



	@Override
	public void registerSeasonTicket(ISeasonTicket seasonTicket) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deregisterSeasonTicket(ISeasonTicket seasonTicket) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean isSeasonTicketValid(String ticketId) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isSeasonTicketInUse(String ticketId) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void recordSeasonTicketEntry(String ticketId) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void recordSeasonTicketExit(String ticketId) {
		// TODO Auto-generated method stub
		
	}

	
	

}
