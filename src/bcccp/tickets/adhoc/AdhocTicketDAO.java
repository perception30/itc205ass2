package bcccp.tickets.adhoc;
import java.util.List;

public class AdhocTicketDAO  implements IAdhocTicketDAO  {

	private IAdhocTicketFactory factory;
	private int currentTicketNo;
        private ArrayList<IAdhocTicket> ticketList;

	
	
	public AdhocTicketDAO(IAdhocTicketFactory factory) {
		          
            this.factory = factory;
            
	}



	@Override
	public IAdhocTicket createTicket(String carparkId) {
		
            IAdhocTicket NewTicket = this.factory.make(carparkId, currentTicketNo);
            ticketList.add(NewTicket);
            
            currentTicketNo++;
            return NewTicket;
	}



	@Override
	public IAdhocTicket findTicketByBarcode(String barcode) {
            
                for(int i = 0; i < ticketList.size(); i++){
                    IAdhocTicket t = ticketList.get(i);
                    if(t.getBarcode().equals(barcode)){
                        return t;
                    
                    }
                }
		return null;
	}



	@Override
	public List<IAdhocTicket> getCurrentTickets() {
		
		return this.ticketList;
	}

	
	
}

