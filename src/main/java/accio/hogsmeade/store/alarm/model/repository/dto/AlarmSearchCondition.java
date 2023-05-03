package accio.hogsmeade.store.alarm.model.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AlarmSearchCondition {

    private String keyword;
    private Boolean open;

    @Builder
    public AlarmSearchCondition(String keyword, Boolean open) {
        this.keyword = keyword;
        this.open = open;
    }
}
