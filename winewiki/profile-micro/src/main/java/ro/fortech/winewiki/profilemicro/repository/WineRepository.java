package ro.fortech.winewiki.profilemicro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.fortech.winewiki.profilemicro.model.Wine;

public interface WineRepository extends JpaRepository<Wine, Long>, JpaSpecificationExecutor<Wine> {

    Wine findByTitle(String title);

    Page<Wine> findAll(Pageable pageRequest);

    Page<Wine> findByDescriptionContainsOrTitleContainsAllIgnoreCase(String descriptionPart,
                                                                     String titlePart,
                                                                     Pageable pageReguest);
}
