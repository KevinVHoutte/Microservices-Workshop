package be.ordina.rental.client;

import be.ordina.rental.resource.Movie;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient("movie")
public interface MovieClient {

	@RequestMapping(method = RequestMethod.GET, value = "/movies/search/findMoviesByRentalNullAndTitleIn")
	Resources<Movie> getAvailableMoviesWithTitleIn(@RequestParam("titles") String[] titles);

	@RequestMapping(method = RequestMethod.PUT, value = "/movies")
	ResponseEntity updateMovies(@RequestBody List<Movie> movies);

	@RequestMapping(method = RequestMethod.GET, value = "/movies/search/findMoviesByRental")
	Resources<Movie> getMoviesByRental(@RequestParam("rental") UUID rental);


}
