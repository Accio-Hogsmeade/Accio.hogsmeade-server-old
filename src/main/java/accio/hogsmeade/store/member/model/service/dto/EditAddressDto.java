package accio.hogsmeade.store.member.model.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditAddressDto {

    private String mainAddress;
    private String detailAddress;

    @Builder
    public EditAddressDto(String mainAddress, String detailAddress) {
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
    }
}
