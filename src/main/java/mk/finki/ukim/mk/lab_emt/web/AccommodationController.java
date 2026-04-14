package mk.finki.ukim.mk.lab_emt.web;

import jakarta.validation.Valid;
import mk.finki.ukim.mk.lab_emt.model.domain.Category;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationResponseDto;
import mk.finki.ukim.mk.lab_emt.model.dto.HostStatsDto;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationDetailsProjection;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationShortProjection;
import mk.finki.ukim.mk.lab_emt.model.views.AccommodationView;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationViewRepository;
import mk.finki.ukim.mk.lab_emt.service.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final AccommodationViewRepository accommodationViewRepository;

    public AccommodationController(AccommodationService accommodationService,
                                   AccommodationViewRepository accommodationViewRepository) {
        this.accommodationService = accommodationService;
        this.accommodationViewRepository = accommodationViewRepository;
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
    @GetMapping("/paged")
    public ResponseEntity<Page<AccommodationResponseDto>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Integer minRooms,
            @RequestParam(required = false) Boolean hasAvailableRooms
    ) {
        return ResponseEntity.ok(
                accommodationService.findAll(
                        page, size, sortBy,
                        category, hostId, countryId,
                        minRooms, hasAvailableRooms));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<AccommodationShortProjection>> findAllSummary() {
        return ResponseEntity.ok(accommodationService.findAllSummary());
    }

    @GetMapping("/detailed")
    public ResponseEntity<List<AccommodationDetailsProjection>> findAllDetailed() {
        return ResponseEntity.ok(accommodationService.findAllDetailed());
    }


    @GetMapping("/with-host-country")
    public ResponseEntity<List<AccommodationResponseDto>> findAllWithHostAndCountry() {
        return ResponseEntity.ok(accommodationService.findAllWithHostAndCountry()
                .stream()
                .map(AccommodationResponseDto::from)
                .toList());
    }

}
