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
            outsideSensor = os;
            carpark = cpark;
            ui.registerController(this);
            this.ui = ui;
            //carpark.register(this);
            
            
		//TODO Implement constructor
	}

        public EntryController(){
            
        }
        
        public int createTicketNumber(){
            Date date = new Date();
            String oldstring = date.toString();
            LocalDateTime datetime = LocalDateTime.parse(oldstring, DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy"));
            String newString = datetime.format(DateTimeFormatter.ofPattern("HHmmssddMM"));
            int ticketNo = Integer.parseInt(newString);
            return ticketNo;
        }
        
        public String createBarcode(){
            String barcode = createTicketNumber() + carpark.getName();
            return barcode;
        }
        
	@Override
	public void buttonPushed() {
            if(!outsideSensor.carIsDetected()){
                System.out.println("no car detected");
            }
            else{
                System.out.println("car detected");
                AdhocTicket ticket = new AdhocTicket(carpark.getName(), createTicketNumber(), createBarcode());
               
                 System.out.println(carpark.getName());
                 System.out.println(ticket.getTicketNo());
                 System.out.println(ticket.getEntryDateTime());
                 System.out.println(ticket.getBarcode());
                 
                   
                ui.printTicket(carpark.getName(), ticket.getTicketNo(), ticket.getEntryDateTime(), ticket.getBarcode());
                //if(!ui.ticketPrinted()){
                 //ui.printTicket((carpark.getName(), adhocTicket.getTicketNo(), adhocTicket.getEntryDateTime(), adhocTicket.getBarcode()));
                 //ui.printTicket(carpark.getName(), 0, 10, adhocTicket.getBarcode());
                 
                 
                 
                 System.out.println("Ticket printed, please take your ticket");
                 //}
                 //else
                     System.out.println("Ticket already printed, please take your ticket.");
            }
            
        }



	@Override
	public void ticketInserted(String barcode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void notifyCarparkEvent() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void carEventDetected(String detectorId, boolean detected) {
		// TODO Auto-generated method stub
		
	}

	
	
}
