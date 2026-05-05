package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.Country;
import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
}