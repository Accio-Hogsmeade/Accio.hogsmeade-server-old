package accio.hogsmeade.store.notice.model.service;

import accio.hogsmeade.store.notice.model.service.dto.AddNoticeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    Long registerNotice(String loginId, AddNoticeDto dto);
}
