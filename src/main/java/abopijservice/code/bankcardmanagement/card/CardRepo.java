package abopijservice.code.bankcardmanagement.card;

import abopijservice.code.bankcardmanagement.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepo extends JpaRepository<Card, UUID>, JpaSpecificationExecutor<Card>, PagingAndSortingRepository<Card, UUID> {

    List<Card> getAllByUserId(UUID userId);

    List<Card> getAllByUserUsername(String username);
}
