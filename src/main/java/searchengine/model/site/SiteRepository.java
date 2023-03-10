package searchengine.model.site;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SiteRepository extends CrudRepository<SiteEntity, Integer> {
    int countAllBy();
    List<SiteEntity> findAllByStatus(IndexingStatus status);
    List<SiteEntity> findAllBy();
    SiteEntity findByUrl(String url);
    @Modifying
    void deleteAllByUrl(String url);

}
