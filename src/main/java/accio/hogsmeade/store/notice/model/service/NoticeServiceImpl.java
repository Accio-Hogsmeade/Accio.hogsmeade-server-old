package accio.hogsmeade.store.notice.model.service;

import accio.hogsmeade.store.notice.model.repository.NoticeRepository;
import accio.hogsmeade.store.notice.model.service.dto.AddNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Long registerNotice(String loginId, AddNoticeDto dto) {
        return null;
    }
}
