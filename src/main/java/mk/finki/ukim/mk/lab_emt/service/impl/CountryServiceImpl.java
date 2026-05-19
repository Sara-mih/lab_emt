package mk.finki.ukim.mk.lab_emt.service.impl;

import mk.finki.ukim.mk.lab_emt.model.domain.Country;
import mk.finki.ukim.mk.lab_emt.repository.CountryRepository;
import mk.finki.ukim.mk.lab_emt.repository.HostRepository;
import mk.finki.ukim.mk.lab_emt.service.CountryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public CountryServiceImpl(CountryRepository countryRepository, HostRepository hostRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found: " + id));
    }

    @Override
    public Country update(Long id, Country country) {
        Country existing = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found: " + id));
        existing.setName(country.getName());
        existing.setContinent(country.getContinent());
        return countryRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found: " + id));
        hostRepository.deleteAll(hostRepository.findAllByCountryId(id));
        countryRepository.delete(country);
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }
}