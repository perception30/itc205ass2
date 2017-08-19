package bcccp.carpark.paystation;

import bcccp.carpark.ICarpark;
import bcccp.tickets.adhoc.IAdhocTicket;

public class PaystationController 
		implements IPaystationController {
	
	private IPaystationUI ui;	
	private ICarpark carpark;

	private IAdhocTicket adhocTicket;
	private float charge;
	
	

	public PaystationController(ICarpark carpark, IPaystationUI ui) {
		//TODO Implement constructor
                this.carpark = carpark;
                this.ui = ui;
                ui.registerController(this);
	}



	@Override
	public void ticketInserted(String barcode) {
		// TODO Auto-generated method stub
                System.out.println("Ticket inserted to paystation, ticket number: " + barcode);
                adhocTicket = carpark.getAdhocTicket(barcode);
                if(adhocTicket != null){
                    System.out.println("ticket in system");                
                    charge = adhocTicket.getCharge();
                    ui.display("$" + Float.toString(charge));
                }                    
                else{
                    ui.display("Go to office");
                    System.out.println("ticket not in system");
                }
	}



	@Override
	public void ticketPaid() {
		// TODO Auto-generated method stub
		adhocTicket.pay();
                ui.printTicket(carpark.getName(), adhocTicket.getTicketNo(), adhocTicket.getEntryDateTime(), 0, charge, adhocTicket.getBarcode());
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}

	
	
}
