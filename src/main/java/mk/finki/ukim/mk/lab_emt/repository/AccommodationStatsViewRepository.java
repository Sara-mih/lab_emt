package mk.finki.ukim.mk.lab_emt.repository;

import mk.finki.ukim.mk.lab_emt.model.views.AccommodationStatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatsViewRepository extends JpaRepository<AccommodationStatsView, String> {
}