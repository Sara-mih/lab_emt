package mk.finki.ukim.mk.lab_emt.repository;

import mk.finki.ukim.mk.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.lab_emt.model.domain.Condition;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationDetailsProjection;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationShortProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAllByHostId(Long hostId);

    List<Accommodation> findAllByHostIdAndCondition(Long hostId, Condition condition);

    List<Accommodation> findAllByHostIdAndIsRented(Long hostId, Boolean isRented);

    List<AccommodationShortProjection> findAllProjectedBy();
    List<AccommodationDetailsProjection> findAllDetailedProjectedBy();
    @EntityGraph(value = "Accommodation.withHostAndCountry", type = EntityGraph.EntityGraphType.FETCH)
    List<Accommodation> findAllBy();
}
