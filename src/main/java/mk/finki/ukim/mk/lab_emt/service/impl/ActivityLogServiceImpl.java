package mk.finki.ukim.mk.lab_emt.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab_emt.model.domain.ActivityLog;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularHostDto;
import mk.finki.ukim.mk.lab_emt.model.dto.PopularityDto;
import mk.finki.ukim.mk.lab_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab_emt.repository.ActivityLogRepository;
import mk.finki.ukim.mk.lab_emt.service.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final AccommodationRepository accommodationRepository;

    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository,
                                  AccommodationRepository accommodationRepository) {
        this.activityLogRepository = activityLogRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    @Transactional
    public ActivityLog save(String accommodationName, String eventType) {
        ActivityLog activityLog = new ActivityLog(accommodationName, LocalDateTime.now(), eventType);
        return activityLogRepository.save(activityLog);
    }

    @Override
    public Page<ActivityLog> findAll(int page, int size) {
        return activityLogRepository.findAll(
                PageRequest.of(page, size, Sort.by("eventTime").descending())
        );
    }

    @Override
    public List<PopularityDto> findMostRentedAccommodations() {
        return activityLogRepository.findAll()
                .stream()
                .filter(log -> log.getEventType().equals("RENTED"))
                .collect(Collectors.groupingBy(
                        ActivityLog::getAccommodationName,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .map(entry -> new PopularityDto(entry.getKey(), entry.getValue()))
                .sorted((a, b) -> Long.compare(b.rentCount(), a.rentCount()))
                .toList();
    }
    @Override
    public List<PopularHostDto> findMostPopularHosts() {
        return activityLogRepository.findAll()
                .stream()
                .filter(log -> log.getEventType().equals("RENTED"))
                .collect(Collectors.groupingBy(
                        ActivityLog::getAccommodationName,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .flatMap(entry -> accommodationRepository.findAll()
                        .stream()
                        .filter(a -> a.getName().equals(entry.getKey()))
                        .map(a -> new PopularHostDto(
                                a.getHost().getName(),
                                a.getHost().getSurname(),
                                entry.getValue()
                        ))
                )
                .collect(Collectors.groupingBy(
                        h -> h.hostName() + " " + h.hostSurname(),
                        Collectors.summingLong(PopularHostDto::rentCount)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    String[] parts = entry.getKey().split(" ");
                    return new PopularHostDto(parts[0], parts[1], entry.getValue());
                })
                .sorted((a, b) -> Long.compare(b.rentCount(), a.rentCount()))
                .toList();
    }
}