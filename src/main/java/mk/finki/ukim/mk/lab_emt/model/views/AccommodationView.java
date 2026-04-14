package mk.finki.ukim.mk.lab_emt.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@Table(name = "accommodation_view")
public class AccommodationView {

    @Id
    private Long id;
    private String name;
    private String category;
    private Integer numRooms;
    private String hostFullName;
    private String countryName;
}