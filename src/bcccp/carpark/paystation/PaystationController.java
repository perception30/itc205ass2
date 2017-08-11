package bcccp.carpark.paystation;

import bcccp.carpark.ICarpark;
import bcccp.tickets.adhoc.IAdhocTicket;
import bcccp.tickets.adhoc.IAdhocTicketDAO;




public class PaystationController 
		implements IPaystationController {
	
	private IPaystationUI ui;	
	private ICarpark carpark;
        private IAdhocTicketDAO AdhocTicketDAO;
	private IAdhocTicket adhocTicket = null;
	private float charge;
	
	

	public PaystationController(ICarpark carpark, IPaystationUI ui) {
		
                this.carpark = carpark;
                this.ui = ui;
		ui.display("Please insert ticket to begin payment");
	}



	@Override
	public void ticketInserted(String barcode) {
		
      
            adhocTicket = AdhocTicketDAO.findTicketByBarcode(barcode);
            if(adhocTicket != null)
            {
                if(adhocTicket.isCurrent())
                {
                    if(!adhocTicket.hasExited())
                    {
                        if(!adhocTicket.isPaid())
                        {
                            charge = carpark.calculateAddHocTicketCharge(adhocTicket.getEntryDateTime());
                            ui.display("Charge: " + String.valueOf(charge));

                        }
                        else
                        {
                            ui.display("Ticket has already been paid");
                            return;
                        }
                    }
                    else
                    {
                     ui.display("Ticket has already been used to exit carpark");
                     return;
                    }
                }
                ui.display("ticket is not current");
            }
            else 
            {
            ui.display("ticket is not registered for this carpark");
            }
 }



	@Override
	public void ticketPaid() {
		
            long currentTimeMillis = System.currentTimeMillis();
            charge = adhocTicket.getCharge();
            adhocTicket.pay(currentTimeMillis, charge);
            ui.printTicket(adhocTicket.getCarparkId(), adhocTicket.getTicketNo(), adhocTicket.getEntryDateTime(), adhocTicket.getPaidDateTime(), charge, adhocTicket.getBarcode());
            ui.display("Thank you ");
                    
		
	}



	@Override
	public void ticketTaken() {
            
            ui.display("Please insert ticket to begin payment");
            
		
		
	}

	
	
}

