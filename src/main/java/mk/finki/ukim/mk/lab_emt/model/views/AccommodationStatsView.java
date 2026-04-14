package mk.finki.ukim.mk.lab_emt.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Getter
@Entity
@Immutable
@Table(name = "accommodation_stats_view")
public class AccommodationStatsView {

    @Id
    private String category;
    private Long totalAccommodations;
    private Long totalRooms;
    private BigDecimal avgRooms;
}