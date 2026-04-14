package mk.finki.ukim.mk.lab_emt.repository;

import mk.finki.ukim.mk.lab_emt.model.views.AccommodationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationViewRepository extends JpaRepository<AccommodationView, Long> {
}