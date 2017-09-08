package be.ordina.movie.movie;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieRepository repository;

	public MovieController(MovieRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "", produces = {"application/json", "application/hal+json"})
	public HttpEntity<List<Movie>> getAll() {
		List<Movie> movies = repository.findAll();
		return ok(movies);
	}

	@PutMapping(value = "", produces = {"application/json", "application/hal+json"})
	public HttpEntity<Void> update(@RequestBody List<Movie> movies) {
		List<String> titles = new ArrayList<>();
		movies.forEach(movie -> titles.add(movie.getTitle()));
		UUID rentalUUID;
		if(movies.get(0).getRental()!=null){
			rentalUUID = movies.get(0).getRental();
		}else{
			rentalUUID=null;
		}
		List<Movie> foundMovies = repository.findMoviesByTitleIn(titles);
		foundMovies.forEach(movie -> movie.setRental(rentalUUID));
		repository.save(foundMovies);
		return ResponseEntity.ok().build();
	}


}
