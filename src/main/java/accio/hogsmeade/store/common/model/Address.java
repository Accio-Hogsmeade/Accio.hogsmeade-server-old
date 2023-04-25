package accio.hogsmeade.store.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.*;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Address {

    @Column(nullable = false)
    private String mainAddress;
    @Column(nullable = false)
    private String detailAddress;

    @Builder
    public Address(String mainAddress, String detailAddress) {
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
    }
}
