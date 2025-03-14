package org.learning.bms.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookSeatRequestDTO {

    private Long showId;
    private List<Long> showSeatIds;
    private Long userId;
}
