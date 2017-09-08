package be.ordina.movie.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie,UUID> {

	List<Movie> findByRentalNull();
	List<Movie> findMoviesByRentalNullAndTitleIn(@Param("titles") String[] titles);
	List<Movie> findMoviesByTitleIn(@Param("titles")List<String> titles);
	List<Movie> findMoviesByRental(@Param("rental") UUID rental);
}
