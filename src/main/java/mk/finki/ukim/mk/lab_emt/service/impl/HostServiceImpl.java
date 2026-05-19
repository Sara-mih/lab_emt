package mk.finki.ukim.mk.lab_emt.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.lab_emt.model.domain.Country;
import mk.finki.ukim.mk.lab_emt.model.domain.Host;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab_emt.repository.CountryRepository;
import mk.finki.ukim.mk.lab_emt.repository.HostRepository;
import mk.finki.ukim.mk.lab_emt.repository.ReservationRepository;
import mk.finki.ukim.mk.lab_emt.service.HostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    private final AccommodationRepository accommodationRepository;

    private final ReservationRepository reservationRepository;

    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository,
                           AccommodationRepository accommodationRepository,
                           ReservationRepository reservationRepository,
                           CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.reservationRepository = reservationRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Host findById(Long id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Host not found: " + id));
    }

    @Override
    public Host update(Long id, String name, String surname, Long countryId) {
        Host existing = hostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Host not found: " + id));
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found: " + countryId));
        existing.setName(name);
        existing.setSurname(surname);
        existing.setCountry(country);
        return hostRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        List<Accommodation> accommodations = accommodationRepository.findAllByHostId(id);
        accommodations.forEach(a -> reservationRepository.deleteAll(
                reservationRepository.findAllByAccommodationId(a.getId())
        ));
        accommodationRepository.deleteAll(accommodations);
        hostRepository.deleteById(id);
    }
    @Override
    public Host create(Host host) {
        return hostRepository.save(host);
    }
}