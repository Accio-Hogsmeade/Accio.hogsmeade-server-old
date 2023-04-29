package accio.hogsmeade.store.board.model;

import accio.hogsmeade.store.common.model.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Category extends TimeBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    @Column(unique = true, nullable = false, length = 10)
    private String name;

    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
