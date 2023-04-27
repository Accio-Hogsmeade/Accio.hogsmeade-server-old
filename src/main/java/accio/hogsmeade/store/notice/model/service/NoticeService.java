package accio.hogsmeade.store.notice.model.service;

import accio.hogsmeade.store.notice.model.service.dto.AddNoticeDto;
import accio.hogsmeade.store.notice.model.service.dto.EditNoticeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    Long registerNotice(String loginId, AddNoticeDto dto);

    Long editNotice(String loginId, Long noticeId, EditNoticeDto dto);
}
