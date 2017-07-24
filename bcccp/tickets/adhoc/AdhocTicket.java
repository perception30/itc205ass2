package bcccp.tickets.adhoc;

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


	@Override
	public int getTicketNo() {
		// TODO Auto-generated method stub
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