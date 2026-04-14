package mk.finki.ukim.mk.lab_emt.web;

import mk.finki.ukim.mk.lab_emt.model.views.AccommodationView;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationViewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations/view")
public class AccommodationViewController {

    private final AccommodationViewRepository accommodationViewRepository;

    public AccommodationViewController(AccommodationViewRepository accommodationViewRepository) {
        this.accommodationViewRepository = accommodationViewRepository;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationView>> getView() {
        return ResponseEntity.ok(accommodationViewRepository.findAll());
    }
}