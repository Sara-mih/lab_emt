package mk.finki.ukim.mk.lab_emt.model.dto;

public record HostStatsDto(
        Long totalAccommodations,
        ConditionStats condition,
        Long totalRentedRooms

) {
    public record ConditionStats(
            Long good,
            Long bad
    ) {}
}