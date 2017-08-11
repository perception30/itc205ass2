package bcccp.tickets.season;

public class UsageRecordFactory implements IUsageRecordFactory {

	@Override
	public IUsageRecord make(String ticketId, long startDateTime) {
		
                UsageRecord ur = new UsageRecord(ticketId, startDateTime);
		return ur;
	}


}

