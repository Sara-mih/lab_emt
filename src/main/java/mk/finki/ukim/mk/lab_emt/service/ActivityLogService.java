package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.ActivityLog;
import org.springframework.data.domain.Page;

public interface ActivityLogService {
    ActivityLog save(String accommodationName, String eventType);
    Page<ActivityLog> findAll(int page, int size);
}