package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.Host;
import java.util.List;

public interface HostService {
    List<Host> findAll();
    Host findById(Long id);
    Host update(Long id, String name, String surname, Long countryId);
    void deleteById(Long id);
    Host create(Host host);
}