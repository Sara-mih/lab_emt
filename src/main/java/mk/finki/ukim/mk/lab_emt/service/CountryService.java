package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.Country;
import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country update(Long id, Country country);
    void deleteById(Long id);
    Country create(Country country);
}