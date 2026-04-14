package mk.finki.ukim.mk.lab_emt.listener;

import lombok.extern.slf4j.Slf4j;
import mk.finki.ukim.mk.lab_emt.event.AccommodationRentedEvent;
import mk.finki.ukim.mk.lab_emt.service.ActivityLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class AccommodationRentedEventListener {

    private final ActivityLogService activityLogService;

    public AccommodationRentedEventListener(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void onAccommodationRented(AccommodationRentedEvent event) {
        log.info("Accommodation rented: [id={}, name='{}', category={}]",
                event.accommodation().getId(),
                event.accommodation().getName(),
                event.accommodation().getCategory());

        activityLogService.save(event.accommodation().getName(), "RENTED");

        if (event.accommodation().isRented()) {
            log.warn("Accommodation [id={}, name='{}'] is FULLY OCCUPIED!",
                    event.accommodation().getId(),
                    event.accommodation().getName());
            activityLogService.save(
                    event.accommodation().getName(),
                    "ACCOMMODATION_FULLY_OCCUPIED"
            );
        }
    }
}