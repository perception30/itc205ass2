package bcccp.tickets.adhoc;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AdhocTicket implements IAdhocTicket {
	
	private String carparkId;
	private int ticketNo;
	private long entryDateTime;
	private long paidDateTime;
	private long exitDateTime;
	private float charge;
	private String barcode;
        
        private boolean isCurrent;
        private boolean hasPaid;

	
	
	public AdhocTicket(String carparkId, int ticketNo, String barcode) {
            this.carparkId = carparkId;
            this.ticketNo = ticketNo;
            this.barcode = barcode;
            this.isCurrent = true; //is this doing anything??
            this.hasPaid = false;
	}
        
        public AdhocTicket(String carparkId, int ticketNo) {
            this.carparkId = carparkId;
            this.ticketNo = ticketNo;
            this.barcode = setEntryDateTime()+ticketNo+"A";
	}


	@Override
	public int getTicketNo() {
            return this.ticketNo;
	}


	@Override
	public String getBarcode() {
            return this.barcode;
	}


	@Override
	public String getCarparkId() {
		return this.carparkId;
	}


	@Override
	public void enter(long dateTime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getEntryDateTime() {
            
            return entryDateTime;
	}
        
        private long setEntryDateTime(){
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
            entryDateTime = Long.parseLong(sdf.format(cal.getTime()));
            return entryDateTime;
        }


	@Override
	public boolean isCurrent() {
            return this.isCurrent;
	}


	@Override
	public void pay(long dateTime, float charge) {
		// TODO Auto-generated method stub
		
	}
        @Override
        public void pay() {
		this.hasPaid = true;
		
	}


	@Override
	public long getPaidDateTime() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isPaid() {
                return this.hasPaid;
                
	}


	@Override
	public float getCharge() {
		// TODO Auto-generated method stub
                float shortStayCharge = 5;
                float longStayCharge = 5000;
                if(setEntryDateTime() - this.getEntryDateTime() < 1000){
                    System.out.println(setEntryDateTime() + " - " + this.getEntryDateTime() +" = " + (setEntryDateTime() - this.getEntryDateTime()));
                    System.out.println("charged custmer LONG stay amount");
                    return longStayCharge;
                }
                else{
                    System.out.println(setEntryDateTime() + " - " + this.getEntryDateTime() +" = " + (setEntryDateTime() - this.getEntryDateTime()));
                    System.out.println("charged custmer SHORT stay amount");
                    return shortStayCharge;
                }
	
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
