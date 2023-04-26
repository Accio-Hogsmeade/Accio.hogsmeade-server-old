package accio.hogsmeade.store.member.model;

import accio.hogsmeade.store.common.exception.EditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("비밀번호 변경")
    void changeLoginPw() {
        //given
        Member member = Member.builder()
                .loginPw("abcd1234!")
                .build();
        //when
        member.changeLoginPw("abcd1234!", "qwer1234!");
        
        //then
        assertThat(member.getLoginPw()).isEqualTo("qwer1234!");
    }
    
    @Test
    @DisplayName("비밀번호 변경#불일치 예외")
    void notEqualLoginPw() {
        //given
        Member member = Member.builder()
                .loginPw("abcd1234!")
                .build();
        //when
        //then
        assertThatThrownBy(() -> member.changeLoginPw("abcd1234@", "qwer1234!"))
                .isInstanceOf(EditException.class);
    }
    
    @Test
    @DisplayName("연락처 변경")
    void changeTel() {
        //given
        Member member = Member.builder()
                .tel("077-1234-1234")
                .build();

        //when
        member.changeTel("077-1234-5678");
        
        //then
        assertThat(member.getTel()).isEqualTo("077-1234-5678");
    }
}