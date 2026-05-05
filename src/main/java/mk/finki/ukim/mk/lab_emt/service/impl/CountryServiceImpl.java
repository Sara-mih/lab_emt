package mk.finki.ukim.mk.lab_emt.service.impl;

import mk.finki.ukim.mk.lab_emt.model.domain.Country;
import mk.finki.ukim.mk.lab_emt.repository.CountryRepository;
import mk.finki.ukim.mk.lab_emt.service.CountryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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
}