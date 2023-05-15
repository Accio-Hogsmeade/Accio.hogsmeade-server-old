package accio.hogsmeade.store.client.board.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryService {

    Long addCategory(String loginId, String categoryName);
}
