package bcccp.tickets.season;

public class UsageRecord implements IUsageRecord {
	
	String ticketId;
	long startDateTime;
	long endDateTime;
	
	
	
	public UsageRecord(String ticketId, long startDateTime) {
		this.ticketId = ticketId;
                this.startDateTime = startDateTime;
                
	}



	@Override
	public void finalise(long endDateTime) {
		
		this.endDateTime = endDateTime;
	}



	@Override
	public long getStartTime() {
		
		return this.startDateTime;
	}



	@Override
	public long getEndTime() {
		
		return this.endDateTime;
	}



	@Override
	public String getSeasonTicketId() {
		
		return this.ticketId;
	}
	
	
	
}


