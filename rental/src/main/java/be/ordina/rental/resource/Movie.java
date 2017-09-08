package be.ordina.rental.resource;

import java.util.UUID;

public class Movie {

	private String title;
	private UUID rental;

	public UUID getRental() {
		return rental;
	}

	public void setRental(UUID rental) {
		this.rental = rental;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
