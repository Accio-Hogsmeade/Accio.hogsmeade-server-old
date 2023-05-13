package accio.hogsmeade.store.admin.notice;

import accio.hogsmeade.store.admin.notice.Notice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NoticeTest {

    @Test
    @DisplayName("공지사항 수정")
    void changeNotice() {
        //given
        Notice notice = Notice.builder()
                .title("title")
                .content("content")
                .pin("0")
                .build();
        //when
        notice.changeNotice("newTitle", "newContent", "1");

        //then
        assertThat(notice.getTitle()).isEqualTo("newTitle");
    }
}