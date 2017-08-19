package bcccp.carpark.paystation;

import bcccp.carpark.ICarpark;
import bcccp.tickets.adhoc.IAdhocTicket;

import bcccp.tickets.adhoc.IAdhocTicketDAO;

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
        private IAdhocTicketDAO AdhocTicketDAO;
	private IAdhocTicket adhocTicket = null;
	private float charge;
	
	

	public PaystationController(ICarpark carpark, IPaystationUI ui) {

                this.carpark = carpark;
                this.ui = ui;
		ui.registerController(this);
		ui.display("Please insert ticket to begin payment");


                this.ui = ui;
                this.carpark = carpark;

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
		else {
                ui.display("ticket is not current");
		}
            }
            else 
            {
            ui.display("ticket is not registered for this carpark");
            }
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

