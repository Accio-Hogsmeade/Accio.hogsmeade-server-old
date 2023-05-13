package accio.hogsmeade.store.client.controller.request.board;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddBoardRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String content;
    private String uploadFileName;
    private Long categoryId;
}
