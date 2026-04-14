package mk.finki.ukim.mk.lab_emt.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_log")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accommodationName;
    private LocalDateTime eventTime;
    private String eventType;

    public ActivityLog(String accommodationName, LocalDateTime eventTime, String eventType) {
        this.accommodationName = accommodationName;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
}