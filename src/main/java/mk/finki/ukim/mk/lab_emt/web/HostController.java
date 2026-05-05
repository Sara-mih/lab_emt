package mk.finki.ukim.mk.lab_emt.web;

import mk.finki.ukim.mk.lab_emt.model.domain.Host;
import mk.finki.ukim.mk.lab_emt.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public ResponseEntity<List<Host>> findAll() {
        return ResponseEntity.ok(hostService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hostService.findById(id));
    }
}