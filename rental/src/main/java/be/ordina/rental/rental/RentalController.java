package be.ordina.rental.rental;

import be.ordina.rental.client.MovieClient;
import be.ordina.rental.resource.Movie;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

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
	public HttpEntity save(@RequestBody RentalInputResource rental) {
		Rental saveRental = mapRental(rental);
		saveRental = repository.save(saveRental);
		List<Movie> movies = mapMovies(saveRental.getUuid(),rental.getMovies());
		if(!movies.isEmpty()) {
			movieClient.updateMovies(movies);
		}
		return ok().build();
	}

	@DeleteMapping(value = "/{uuid}")
	public HttpEntity delete(@PathVariable("uuid") UUID uuid) {
		Rental rental = repository.findOne(uuid);
		Resources<Movie> foundMovies = movieClient.getMoviesByRental(rental.getUuid());
		if(!foundMovies.getContent().isEmpty()) {
			foundMovies.getContent().forEach(movie -> movie.setRental(null));
			List<Movie> listMovies = new ArrayList<>(foundMovies.getContent());
			movieClient.updateMovies(listMovies);
			repository.delete(uuid);
		}
		return ok().build();
	}

	@GetMapping(value = "", produces = {"application/json", "application/hal+json"})
	public HttpEntity<List<Rental>> getAll() {
		List<Rental> rentals = repository.findAll();
		return ok(rentals);
	}


	private List<Movie> mapMovies(UUID uuid, String[] movies) {
		Resources<Movie> movieResources = movieClient.getAvailableMoviesWithTitleIn(movies);
		if(movieResources!=null){
			movieResources.getContent().forEach(movie -> movie.setRental(uuid));
		}else{
			movieResources = new Resources<>(new ArrayList<>());
		}
		return new ArrayList<>(movieResources.getContent());
	}

	private Rental mapRental(RentalInputResource resource){
		Rental rental  = new Rental();
		if(resource.getName()!=null) {
			rental.setName(resource.getName());
		}
		if(resource.getEndDate()!=null){
			rental.setEndDate(resource.getEndDate());
		}
		if(resource.getRentDate()!=null){
			rental.setRentDate(resource.getRentDate());
		}
		return rental;
	}
}
