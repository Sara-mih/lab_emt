package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.Reservation;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularityDto;

import java.util.List;

public interface ReservationService {
    Reservation save(Long accommodationId);
    List<Reservation> findAll();
    List<Reservation> findByAccommodationId(Long accommodationId);
    List<PopularityDto> findMostRentedAccommodations();
}