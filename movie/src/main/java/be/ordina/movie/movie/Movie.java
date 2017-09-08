package be.ordina.movie.movie;

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
public class Movie implements Serializable {

	@Id
	@Column(name = "uuid")
	private UUID uuid;

	@Column(unique = true)
	private String title;
	private int runtime;
	private String format;
	private String plot;
	private LocalDate release;
	private UUID rental;

	@PrePersist
	public void ensureUuid() {
		if (getUuid() == null) {
			setUuid(UUID.randomUUID());
		}
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

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

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public LocalDate getRelease() {
		return release;
	}

	public void setRelease(LocalDate release) {
		this.release = release;
	}

	public UUID getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getUuid())
				.toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Movie) {
			final Movie other = (Movie) obj;
			return new EqualsBuilder()
					.append(getUuid(), other.getUuid())
					.isEquals();
		} else {
			return false;
		}
	}
}