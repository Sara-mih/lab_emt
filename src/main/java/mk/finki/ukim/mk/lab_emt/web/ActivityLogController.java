package mk.finki.ukim.mk.lab_emt.web;

import mk.finki.ukim.mk.lab_emt.model.domain.ActivityLog;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularHostDto;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularityDto;
import mk.finki.ukim.mk.lab_emt.service.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-log")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public ResponseEntity<Page<ActivityLog>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(activityLogService.findAll(page, size));
    }

    @GetMapping("/most-rented")
    public ResponseEntity<List<PopularityDto>> findMostRentedAccommodations() {
        return ResponseEntity.ok(activityLogService.findMostRentedAccommodations());
    }

    @GetMapping("/most-popular-hosts")
    public ResponseEntity<List<PopularHostDto>> findMostPopularHosts() {
        return ResponseEntity.ok(activityLogService.findMostPopularHosts());
    }
}