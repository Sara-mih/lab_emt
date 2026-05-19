package mk.finki.ukim.mk.lab_emt.repository;

import mk.finki.ukim.mk.lab_emt.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> {

    List<Host> findAllByCountryId(Long countryId);
}
