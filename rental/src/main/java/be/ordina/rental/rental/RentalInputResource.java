package be.ordina.rental.rental;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentalInputResource {

	private String name;
	private LocalDate rentDate;
	private LocalDate endDate;
	private String[] movies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String[] getMovies() {
		return movies;
	}

	public void setMovies(String[] movies) {
		this.movies = movies;
	}
}
