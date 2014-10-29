package my.pack.dataAccessTier.domain.subfacilities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Embeddable
public class Economic_facilities {

	@Column(name="Ticket_Price")
	private int ticket_price;
	@Column(name="Tickets_Sold")
	private int tickets_sold;
	@Column(name="People_out")
	private int people_out;

	public Economic_facilities() {}

	public Economic_facilities(int ticket_price) {
		super();
		this.ticket_price = ticket_price;
		this.tickets_sold = 0;
		this.people_out=0;
	}

	/*	@Transactional(
			isolation=Isolation.SERIALIZABLE,
			propagation=Propagation.REQUIRES_NEW
			)*/
	public void add_or_sub_ticket(char oper) {

		if (oper=='A') {
			/*		synchronized(this) {
		this.tickets_sold++;
		}*/
			this.tickets_sold++;
			System.out.println("Ticket sold!!");
		}

		if (oper=='S') {
			this.tickets_sold--;
			System.out.println("Ticket subtract!!");
		}


	}
	
	public void add_or_sub_people_out(char oper) {

		if (oper=='A') {
			
			this.people_out++;
			System.out.println("Person out!!");
		}

		if (oper=='S') {
			this.people_out--;
			System.out.println("OOps, no person out!!");
		}


	}

	public int getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(int ticket_price) {
		this.ticket_price = ticket_price;
	}

	public int getTickets_sold() {
		return tickets_sold;
	}

	public void setTickets_sold(int tickets_sold) {
		this.tickets_sold = tickets_sold;
	}

	public int getPeople_out() {
		return people_out;
	}

	public void setPeople_out(int people_out) {
		this.people_out = people_out;
	}



}
