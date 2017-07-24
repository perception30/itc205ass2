package bcccp.carpark.paystation;

import bcccp.carpark.ICarpark;
import bcccp.tickets.adhoc.IAdhocTicket;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;

public class PaystationController 
		implements IPaystationController {
	
	private IPaystationUI ui;	
	private ICarpark carpark;

	private IAdhocTicket  adhocTicket = null;
	private float charge;
	
	

	public PaystationController(ICarpark carpark, IPaystationUI ui) {
		ui.registerController(this);
                this.ui = ui;
                this.carpark = carpark;
	}



	@Override
	public void ticketInserted(String barcode) {
		// Adhoc ticket number entered 
                String ticketNumber = barcode.substring(0, 10);
                System.out.println("the ticket number is " + ticketNumber);
                String carparkID = barcode.substring(10);
		System.out.println("the carpark number " + carparkID);
                
                ui.display("Outstanding amount: " + charge);
	}

        public void interpertBarcode(String barcode){
            
            String ticketNumber = barcode.substring(0, 10);
            String carparkID = barcode.substring(10);
            
            
            //Date date = new Date();
            String oldstring = ticketNumber;
            LocalDateTime datetime = LocalDateTime.parse(oldstring, DateTimeFormatter.ofPattern("HHmmssddMM"));
            
            String newString = datetime.format(DateTimeFormatter.ofPattern("dd"));
            System.out.println(newString);
            
            
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("MMM dd HH:mm:ss")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, Year.now().getValue())
                .toFormatter(Locale.ENGLISH);
            
            
        }

	@Override
	public void ticketPaid() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ticketTaken() {
		// TODO Auto-generated method stub
		
	}

	
	
}
