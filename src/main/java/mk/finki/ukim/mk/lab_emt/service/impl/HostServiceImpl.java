package mk.finki.ukim.mk.lab_emt.service.impl;

import mk.finki.ukim.mk.lab_emt.model.domain.Host;
import mk.finki.ukim.mk.lab_emt.repository.HostRepository;
import mk.finki.ukim.mk.lab_emt.service.HostService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
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
}