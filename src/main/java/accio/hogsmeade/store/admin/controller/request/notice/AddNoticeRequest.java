package accio.hogsmeade.store.admin.controller.request.notice;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddNoticeRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    @Size(max = 1)
    @Range(min = 0, max = 1)
    private String pin;
}
