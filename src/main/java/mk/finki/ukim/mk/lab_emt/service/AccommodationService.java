package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.lab_emt.model.domain.Category;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationRequestDto;

import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationResponseDto;
import mk.finki.ukim.mk.lab_emt.model.dto.HostStatsDto;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationDetailsProjection;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccommodationService {

    List<Accommodation> findAll();

    Accommodation findById(Long id);

    Accommodation create(AccommodationRequestDto dto);

    Accommodation update(Long id, AccommodationRequestDto dto);

    void deleteById(Long id);

    Accommodation markAsRented(Long id);

    HostStatsDto getHostStats(Long hostId);

    Page<AccommodationResponseDto> findAll(
            int page, int size, String sortBy,
            Category category, Long hostId, Long countryId,
            Integer minRooms, Boolean hasAvailableRooms
    );

    List<AccommodationShortProjection> findAllSummary();
    List<AccommodationDetailsProjection> findAllDetailed();

    List<Accommodation> findAllWithHostAndCountry();
}
