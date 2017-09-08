package be.ordina.rental.client;

import be.ordina.rental.resource.Movie;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient("movie")
public interface MovieClient {

	@RequestMapping(method = RequestMethod.GET, value = "/movies/search/findByRentalNull")
	Resources<Movie> getAvailableMovies();

	@RequestMapping(method = RequestMethod.PUT, value = "/movies/{uuid}")
	void updateMovie(@PathVariable("uuid") UUID uuid, Movie movie);

}
