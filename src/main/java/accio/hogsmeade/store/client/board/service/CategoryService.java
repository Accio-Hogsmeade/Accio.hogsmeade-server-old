package accio.hogsmeade.store.client.board.service;

import accio.hogsmeade.store.client.board.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CategoryService {

    Long addCategory(String loginId, String categoryName);

    List<Category> searchAll();
}
