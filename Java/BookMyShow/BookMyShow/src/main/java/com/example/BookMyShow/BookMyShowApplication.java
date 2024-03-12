package com.example.BookMyShow;

import com.example.BookMyShow.controllers.TicketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
	private TicketController ticketController;

	@Autowired
	public BookMyShowApplication(TicketController ticketController) {
		this.ticketController = ticketController;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
