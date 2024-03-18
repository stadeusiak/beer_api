package spring.sta.posting;

import org.springframework.stereotype.Repository;
import spring.sta.posting.utils.Beer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class PostingRepository {

    private final Map<Long, Beer> storage = new ConcurrentHashMap<>();
    private final AtomicLong SEQ = new AtomicLong(0L);

    public Optional<Beer> findById(Long id) {
       return Optional.ofNullable(storage.get(id));
    }

    public List<Beer> findAll() {
        return List.copyOf(storage.values());
    }

    public Beer save(Beer posting) {
        if (posting.getId() == null) {
            posting.setId(SEQ.incrementAndGet());
        }
        storage.put(posting.getId(), posting);
        return posting;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }
}
