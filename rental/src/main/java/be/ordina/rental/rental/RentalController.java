package be.ordina.rental.rental;

import be.ordina.rental.client.MovieClient;
import be.ordina.rental.resource.Movie;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/rentals")
public class RentalController {

	private final RentalRepository repository;
	private final MovieClient movieClient;

	public RentalController(RentalRepository repository, MovieClient movieClient) {
		this.repository = repository;
		this.movieClient = movieClient;
	}

	@PostMapping(value = "", produces = {"application/json", "application/hal+json"})
	public HttpEntity<Void> save(@RequestBody RentalInputResource rental) {
		Resources<Movie> movies = movieClient.getAvailableMovies();


		return ResponseEntity.ok().build();

	}
}
