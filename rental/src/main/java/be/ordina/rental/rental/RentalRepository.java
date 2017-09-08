package be.ordina.rental.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental,UUID> {

}
