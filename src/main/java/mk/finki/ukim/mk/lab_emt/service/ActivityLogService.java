package mk.finki.ukim.mk.lab_emt.service;

import mk.finki.ukim.mk.lab_emt.model.domain.ActivityLog;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularHostDto;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularityDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActivityLogService {
    ActivityLog save(String accommodationName, String eventType);
    Page<ActivityLog> findAll(int page, int size);
    List<PopularityDto> findMostRentedAccommodations();
    List<PopularHostDto> findMostPopularHosts();
}