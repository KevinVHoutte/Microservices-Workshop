package be.ordina.rental.rental;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Rental implements Serializable {

	@Id
	@Column(name = "uuid")
	private UUID uuid;

	private String name;
	private LocalDate rentDate;
	private LocalDate endDate;


	private void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@PrePersist
	public void ensureUuid() {
		if (getUuid() == null) {
			setUuid(UUID.randomUUID());
		}
	}

	public UUID getUuid() {
		return uuid;
	}

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

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getUuid())
				.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Rental) {
			final Rental other = (Rental) obj;
			return new EqualsBuilder()
					.append(getUuid(), other.getUuid())
					.isEquals();
		} else {
			return false;
		}
	}

}