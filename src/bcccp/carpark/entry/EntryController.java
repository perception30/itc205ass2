package bcccp.carpark.entry;

import bcccp.carpark.CarSensor;
import bcccp.carpark.Carpark;
import bcccp.carpark.ICarSensor;
import bcccp.carpark.ICarSensorResponder;
import bcccp.carpark.ICarpark;
import bcccp.carpark.ICarparkObserver;
import bcccp.carpark.IGate;
import bcccp.tickets.adhoc.AdhocTicket;
import bcccp.tickets.adhoc.IAdhocTicket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EntryController 
		implements ICarSensorResponder,
				   ICarparkObserver,
		           IEntryController {
	
	private IGate entryGate;
	private ICarSensor outsideSensor; 
	private ICarSensor insideSensor;
	private IEntryUI ui;
	
	private Carpark carpark;
	private IAdhocTicket  adhocTicket;
        private long entryTime;
	private String seasonTicketId = null;
        
       
       //private AdhocTicket adHocTicket = new AdhocTicket(carpark.getName(), createTicketNumber(), createBarcode());
        
        
       public EntryController(Carpark cpark, IGate eGate, 
			ICarSensor os, 
			ICarSensor is,
			IEntryUI ui) {
            
            entryGate = eGate;
            os.registerResponder(this);
            is.registerResponder(this);
            outsideSensor = os;
            carpark = cpark;
            ui.registerController(this);
            this.ui = ui;
            //carpark.register(this);
            
            
		//TODO Implement constructor
	}

        
        
	@Override
	public void buttonPushed() {
            //button is pushed, check car is waiting            
            if(!outsideSensor.carIsDetected()){
                //button pushed, no car detected
                ui.display("No car detected");
            }
            //if car is waiting, check to see if carpark is full
            else{
                if(!carpark.isFull()){
                //carpark not full, check to see if ticket already printed                   
                    if(!ui.ticketPrinted()){
                    //car is waiting, carpark not full, ticket not already printed.
                        //create new ticket THIS TICKET HAS BEEN CREATED USING ADHOCTICKET CONSTUCTOR AND NOT THE TICKETFACTOR ???
                        AdhocTicket ticket = new AdhocTicket(carpark.getName()); 
                        //print the ticket details
                        ui.printTicket(carpark.getName(), ticket.getTicketNo(), ticket.getEntryDateTime(), ticket.getBarcode());
                        ui.display("Take Ticket");
                    }
                    else{
                    //car is waiting, carpark not full, ticket already printed
                        System.out.println("Ticket already printed, please take your ticket.");
                        ui.display("Ticket already printed");
                    }
                }
                else
                    //car is waiting, carpark full
                    while(carpark.isFull() && outsideSensor.carIsDetected())
                        ui.display("Full");
                //If a vehicle leaves the car park, then the ‘Press Button’ display is activated again where there is a vehicle waiting.
                //have not tested this, may need to add call back to buttonpushed() to start the process again ????
                }
                
            
        }



	@Override
	public void ticketInserted(String barcode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
            ui.display("Thank you");
            carpark.recordAdhocTicketEntry();
            entryGate.raise();
		
	}



	@Override
	public void notifyCarparkEvent() {
		// TODO Auto-generated method stub
                //
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean detected) {
		System.out.println(detectorId + " has a car at it: " + detected);
                if(detectorId == "Entry Outside Sensor"){
                //car is detected waiting outside entry
                    if(detected)
                        ui.display("Press Button");
                    else
                        ui.display("");
                }
                if(detectorId == "Entry Inside Sensor"){
                //car goes through gate
                if(entryGate.isRaised()){
                    ui.display(""); //clear thank you message from display
                    entryGate.lower();
                    //do we need to auto change the Entry Outside Sensor to false????
                }
                
	}

        }
	
}
