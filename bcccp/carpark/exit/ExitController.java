package bcccp.carpark.exit;

import bcccp.carpark.Carpark;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.ICarSensorResponder;
import bcccp.carpark.ICarpark;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.IAdhocTicket;

public class ExitController 
		implements ICarSensorResponder,
		           IExitController {
	
	private IGate exitGate;
	private ICarSensor insideSensor;
	private ICarSensor outsideSensor; 
	private IExitUI ui;
	
	private ICarpark carpark;
	private IAdhocTicket  adhocTicket;
	private long exitTime;
	private String seasonTicketId;
	
	

	public ExitController(Carpark carpark, IGate exitGate, 
			ICarSensor is,
			ICarSensor os, 
			IExitUI ui) {
            this.exitGate = exitGate;
            os.registerResponder(this);
            is.registerResponder(this);
            outsideSensor = os;
            this.carpark = carpark;
            ui.registerController(this);
            this.ui = ui;
	}



	@Override
	public void ticketInserted(String barcode) {
		System.out.println("Ticket inserted to exit station, ticket number: " + barcode);
                adhocTicket = carpark.getAdhocTicket(barcode);
                if(carpark.isSeasonTicketInUse(barcode)){
                    ui.display("Take Ticket");
                }
                    
                if(adhocTicket != null){
                    System.out.println("ticket in system");                
                    if(adhocTicket.isPaid()){
                        System.out.println("ticket is paid");
                        carpark.recordAdhocTicketExit();
                        ui.display("Take Ticket");
                    }                        
                }
                else{
                    ui.display("Go to office");
                    System.out.println("ticket not in system");
                }
		
	}



	@Override
	public void ticketTaken() {
		exitGate.raise();		
	}



	@Override
	public void carEventDetected(String detectorId, boolean detected) {
		// TODO Auto-generated method stub
		
	}

	
	
}
