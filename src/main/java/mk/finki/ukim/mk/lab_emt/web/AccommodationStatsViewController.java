package mk.finki.ukim.mk.lab_emt.web;

import mk.finki.ukim.mk.lab_emt.model.views.AccommodationStatsView;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationStatsViewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations/stats-view")
public class AccommodationStatsViewController {

    private final AccommodationStatsViewRepository accommodationStatsViewRepository;

    public AccommodationStatsViewController(AccommodationStatsViewRepository accommodationStatsViewRepository) {
        this.accommodationStatsViewRepository = accommodationStatsViewRepository;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationStatsView>> getStatsView() {
        return ResponseEntity.ok(accommodationStatsViewRepository.findAll());
    }
}