package accio.hogsmeade.store.admin.notice.service;

import accio.hogsmeade.store.admin.notice.service.dto.EditNoticeDto;
import accio.hogsmeade.store.admin.notice.service.dto.AddNoticeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    Long registerNotice(String loginId, AddNoticeDto dto);

    Long editNotice(String loginId, Long noticeId, EditNoticeDto dto);
}
