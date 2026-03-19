package mk.finki.ukim.mk.lab_emt.web;

import jakarta.validation.Valid;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationResponseDto;
import mk.finki.ukim.mk.lab_emt.model.dto.HostStatsDto;
import mk.finki.ukim.mk.lab_emt.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationResponseDto>> findAll() {
        return ResponseEntity.ok(accommodationService.findAll()
                .stream()
                .map(AccommodationResponseDto::from)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                AccommodationResponseDto.from(accommodationService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<AccommodationResponseDto> create(
            @RequestBody @Valid AccommodationRequestDto dto) {
        return ResponseEntity.ok(
                AccommodationResponseDto.from(accommodationService.create(dto)));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<AccommodationResponseDto> update(
            @PathVariable Long id,
            @RequestBody @Valid AccommodationRequestDto dto) {
        return ResponseEntity.ok(
                AccommodationResponseDto.from(accommodationService.update(id, dto)));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accommodationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<AccommodationResponseDto> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(
                AccommodationResponseDto.from(accommodationService.markAsRented(id)));
    }
    @GetMapping("/host/{id}/stats")
    public ResponseEntity<HostStatsDto> getHostStats(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.getHostStats(id));
    }

}
