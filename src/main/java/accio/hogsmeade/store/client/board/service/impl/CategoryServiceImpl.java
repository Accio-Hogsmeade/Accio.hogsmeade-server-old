package accio.hogsmeade.store.client.board.service.impl;

import accio.hogsmeade.store.client.board.Category;
import accio.hogsmeade.store.client.board.repository.CategoryRepository;
import accio.hogsmeade.store.client.board.service.CategoryService;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.repository.MemberRepository;
import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.common.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;


    @Override
    public Long addCategory(String loginId, String categoryName) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        if (!member.getRoles().get(0).equals("ADMIN")) {
            throw new AuthorityException();
        }

        Optional<Category> findCategory = categoryRepository.findByName(categoryName);
        if (findCategory.isPresent()) {
            throw new DuplicateException();
        }

        Category category = Category.builder()
                .name(categoryName)
                .build();
        Category save = categoryRepository.save(category);
        return save.getId();
    }

    @Override
    public List<Category> searchAll() {
        return categoryRepository.findAll();
    }
}
