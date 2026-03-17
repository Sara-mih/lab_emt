package mk.finki.ukim.mk.lab_emt.model.dto;

import mk.finki.ukim.mk.lab_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.lab_emt.model.domain.Category;
import mk.finki.ukim.mk.lab_emt.model.domain.Condition;
import mk.finki.ukim.mk.lab_emt.model.domain.Host;

public record AccommodationResponseDto(
        Long id,
        String name,
        Category category,
        Condition condition,
        String hostName,
        String hostSurname,
        Integer numRooms,
        Boolean isRented
) {
    public static AccommodationResponseDto from(Accommodation accommodation) {
        return new AccommodationResponseDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getCondition(),
                accommodation.getHost().getName(),
                accommodation.getHost().getSurname(),
                accommodation.getNumRooms(),
                accommodation.isRented()
        );
    }
}