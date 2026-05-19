package mk.finki.ukim.mk.lab_emt.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab_emt.event.AccommodationRentedEvent;
import mk.finki.ukim.mk.lab_emt.model.domain.*;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationResponseDto;
import mk.finki.ukim.mk.lab_emt.model.dto.HostStatsDto;
import mk.finki.ukim.mk.lab_emt.model.exception.AccommodationNotFoundException;
import mk.finki.ukim.mk.lab_emt.model.exception.HostNotFoundException;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationDetailsProjection;
import mk.finki.ukim.mk.lab_emt.model.projection.AccommodationShortProjection;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab_emt.repository.HostRepository;
import mk.finki.ukim.mk.lab_emt.repository.ReservationRepository;
import mk.finki.ukim.mk.lab_emt.service.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationalSeviceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ReservationRepository reservationRepository;

    public AccommodationalSeviceImpl(AccommodationRepository accommodationRepository,
                                     HostRepository hostRepository,
                                     ApplicationEventPublisher eventPublisher,
                                     ReservationRepository reservationRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.eventPublisher = eventPublisher;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationNotFoundException(id));
    }

    @Override
    public Accommodation create(AccommodationRequestDto dto) {
        Host host = hostRepository.findById(dto.hostId())
                .orElseThrow(() -> new HostNotFoundException(dto.hostId()));
        Accommodation accommodation = new Accommodation(
                dto.name(), dto.category(), host, dto.numRooms());
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation update(Long id, AccommodationRequestDto dto) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationNotFoundException(id));
        Host host = hostRepository.findById(dto.hostId())
                .orElseThrow(() -> new HostNotFoundException(dto.hostId()));
        accommodation.setName(dto.name());
        accommodation.setCategory(dto.category());
        accommodation.setNumRooms(dto.numRooms());
        accommodation.setHost(host);
        return accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteById(Long id) {
        if (!accommodationRepository.existsById(id)) {
            throw new AccommodationNotFoundException(id);
        }
        reservationRepository.deleteAll(
                reservationRepository.findAllByAccommodationId(id)
        );
        accommodationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Accommodation markAsRented(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationNotFoundException(id));
        accommodation.setRented(true);
        accommodation.setCondition(Condition.GOOD);
        Accommodation saved = accommodationRepository.save(accommodation);
        reservationRepository.save(new Reservation(saved));
        eventPublisher.publishEvent(new AccommodationRentedEvent(saved));
        return saved;
    }

    @Override
    public HostStatsDto getHostStats(Long hostId) {
        List<Accommodation> accommodations = accommodationRepository.findAllByHostId(hostId);

        Long totalAccommodations = accommodations.stream()
                .mapToLong(Accommodation::getNumRooms)
                .sum();

        Long totalRoomsGood = accommodationRepository
                .findAllByHostIdAndCondition(hostId, Condition.GOOD)
                .stream()
                .mapToLong(Accommodation::getNumRooms)
                .sum();

        Long totalRoomsBad = accommodationRepository
                .findAllByHostIdAndCondition(hostId, Condition.BAD)
                .stream()
                .mapToLong(Accommodation::getNumRooms)
                .sum();

        Long totalRentedRooms = accommodationRepository
                .findAllByHostIdAndIsRented(hostId, true)
                .stream()
                .mapToLong(Accommodation::getNumRooms)
                .sum();

        return new HostStatsDto(
                totalAccommodations,
                new HostStatsDto.ConditionStats(totalRoomsGood, totalRoomsBad),
                totalRentedRooms
        );
    }

    @Override
    public Page<AccommodationResponseDto> findAll(
            int page, int size, String sortBy,
            Category category, Long hostId, Long countryId,
            Integer minRooms, Boolean hasAvailableRooms) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<AccommodationResponseDto> filtered = accommodationRepository
                .findAll(pageable)
                .stream()
                .filter(a -> category == null || a.getCategory() == category)
                .filter(a -> hostId == null || a.getHost().getId().equals(hostId))
                .filter(a -> countryId == null || a.getHost().getCountry().getId().equals(countryId))
                .filter(a -> minRooms == null || a.getNumRooms() >= minRooms)
                .filter(a -> hasAvailableRooms == null || !a.isRented())
                .map(AccommodationResponseDto::from)
                .toList();

        return new PageImpl<>(filtered, pageable, filtered.size());
    }

    @Override
    public List<AccommodationShortProjection> findAllSummary() {
        return accommodationRepository.findAllProjectedBy();
    }

    @Override
    public List<AccommodationDetailsProjection> findAllDetailed() {
        return accommodationRepository.findAllDetailedProjectedBy();
    }

    @Override
    public List<Accommodation> findAllWithHostAndCountry() {
        return accommodationRepository.findAllBy();
    }

}
