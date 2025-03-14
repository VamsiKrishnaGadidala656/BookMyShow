package org.learning.bms.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlockSeatRequestDTO {

    protected long userId;
    protected long showId;
    protected List<Long> seatIds;

}
