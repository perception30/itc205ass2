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
		
	}


	@Override
	public long getEntryDateTime() {
            long currentTimeMillis = System.currentTimeMillis();
            return currentTimeMillis;
	}


	@Override
	public boolean isCurrent() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void pay(long dateTime, float charge) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getPaidDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isPaid() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public float getCharge() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void exit(long dateTime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getExitDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean hasExited() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
