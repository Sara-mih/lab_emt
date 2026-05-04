package mk.finki.ukim.mk.lab_emt.web;

import mk.finki.ukim.mk.lab_emt.model.domain.Reservation;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularityDto;
import mk.finki.ukim.mk.lab_emt.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/accommodation/{id}")
    public ResponseEntity<List<Reservation>> findByAccommodationId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findByAccommodationId(id));
    }

    @PostMapping("/accommodation/{id}")
    public ResponseEntity<Reservation> rent(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.save(id));
    }
    @GetMapping("/most-rented")
    public ResponseEntity<List<PopularityDto>> findMostRentedAccommodations() {
        return ResponseEntity.ok(reservationService.findMostRentedAccommodations());
    }
}