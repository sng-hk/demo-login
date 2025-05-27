package snghk.demologin.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSaveRequest {
    private String title;
    private String content;
}
