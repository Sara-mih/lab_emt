package mk.finki.ukim.mk.lab_emt.service.impl;

import mk.finki.ukim.mk.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.lab_emt.model.domain.Condition;
import mk.finki.ukim.mk.lab_emt.model.domain.Host;
import mk.finki.ukim.mk.lab_emt.model.dto.AccommodationRequestDto;
import mk.finki.ukim.mk.lab_emt.model.dto.HostStatsDto;
import mk.finki.ukim.mk.lab_emt.model.exception.AccommodationNotFoundException;
import mk.finki.ukim.mk.lab_emt.model.exception.HostNotFoundException;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab_emt.repository.HostRepository;
import mk.finki.ukim.mk.lab_emt.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationalSeviceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationalSeviceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
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
        accommodationRepository.deleteById(id);
    }

    @Override
    public Accommodation markAsRented(Long id) {
        Accommodation accommodation=accommodationRepository.findById(id).orElseThrow(()->new AccommodationNotFoundException(id));
        accommodation.setRented(true);
        accommodation.setCondition(Condition.GOOD);
        return accommodationRepository.save(accommodation);
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
}
