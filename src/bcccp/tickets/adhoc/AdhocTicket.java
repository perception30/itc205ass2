package bcccp.tickets.adhoc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AdhocTicket implements IAdhocTicket {
	
	private String carparkId;
	private int ticketNo;
	private long entryDateTime;
	private long paidDateTime;
	private long exitDateTime;
	private float charge;
	private String barcode;
        private Date date;

	
	
	public AdhocTicket(String carparkId, int ticketNo, String barcode) {
            this.carparkId = carparkId;
            this.ticketNo = ticketNo;
            this.barcode = barcode;
	}
        
        public AdhocTicket(String carparkId){
            this.carparkId = carparkId;
            this.ticketNo = createTicketNumber();
            this.barcode = createBarcode(ticketNo);
        }
        public AdhocTicket(String carparkId, int ticketNo) {
            this.carparkId = carparkId;
            this.ticketNo = ticketNo;
            this.barcode = createBarcode(this.ticketNo);
	}
        
        public String createBarcode(int ticketNumber){
            Date date = new Date();
            String oldstring = date.toString();
            LocalDateTime datetime = LocalDateTime.parse(oldstring, DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy"));
            String barcode = ticketNumber + datetime.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
            return barcode;
        }
        
        public int createTicketNumber(){
            ticketNo++;
            return ticketNo;
        }
                


	@Override
	public int getTicketNo() {
		return this.ticketNo;
	}


	@Override
	public String getBarcode() {
		// TODO Auto-generated method stub
		return this.barcode;
	}


	@Override
	public String getCarparkId() {
		// TODO Auto-generated method stub
		return this.carparkId;
	}


	@Override
	public void enter(long dateTime) {
		// TODO Auto-generated method stub
            this.entryDateTime = dateTime;
		
	}


	@Override
	public long getEntryDateTime() {
            long currentTimeMillis = System.currentTimeMillis();
            return currentTimeMillis;
	}


	@Override
	public boolean isCurrent() {
		// TODO Auto-generated method stub
            if (this.entryDateTime != 0){
            return this.exitDateTime > 0;
            }
            return false;
	}


	@Override
	public void pay(long dateTime, float charge) {
            this.charge = charge;
            paidDateTime = System.currentTimeMillis();
            
		
	}


	@Override
	public long getPaidDateTime() {
		return paidDateTime;
	}


	@Override
	public boolean isPaid() {
		 
                    return paidDateTime > 0;
               
	}


	@Override
	public float getCharge() {
		// TODO Auto-generated method stub
		return charge;
	}


	@Override
	public void exit(long dateTime) {
		// TODO Auto-generated method stub
            this.exitDateTime = dateTime;
		
	}


	@Override
	public long getExitDateTime() {
		// TODO Auto-generated method stub
		return exitDateTime;
	}


	@Override
	public boolean hasExited() {
		// TODO Auto-generated method stub
                if (exitDateTime > 0)
                {
                    return true;
                }
		return false;
	}

	
	
}
