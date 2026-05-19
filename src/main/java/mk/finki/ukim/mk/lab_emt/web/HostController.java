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
    @PutMapping("/{id}/edit")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostUpdateRequest request) {
        return ResponseEntity.ok(hostService.update(id, request.name(), request.surname(), request.countryId()));
    }

    record HostUpdateRequest(String name, String surname, Long countryId) {}

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Host> create(@RequestBody Host host) {
        return ResponseEntity.ok(hostService.create(host));
    }
}