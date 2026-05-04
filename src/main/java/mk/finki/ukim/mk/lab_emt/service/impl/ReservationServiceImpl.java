package mk.finki.ukim.mk.lab_emt.service.impl;

import mk.finki.ukim.mk.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.lab_emt.model.domain.Reservation;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularityDto;
import mk.finki.ukim.mk.lab_emt.model.exception.AccommodationNotFoundException;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab_emt.repository.ReservationRepository;
import mk.finki.ukim.mk.lab_emt.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  AccommodationRepository accommodationRepository) {
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public Reservation save(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
        return reservationRepository.save(new Reservation(accommodation));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findByAccommodationId(Long accommodationId) {
        return reservationRepository.findAllByAccommodationId(accommodationId);
    }
    @Override
    public List<PopularityDto> findMostRentedAccommodations() {
        return reservationRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        r -> r.getAccommodation().getName(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(entry -> new PopularityDto(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> Long.compare(b.rentCount(), a.rentCount()))
                .toList();
    }
}