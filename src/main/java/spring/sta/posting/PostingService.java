package spring.sta.posting;

import org.springframework.stereotype.Service;
import spring.sta.dto.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PostingService {

    private  final PostingRepository postingRepository;

    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    public List<Beer> getAll() {
        return postingRepository.findAll();
    }

    public Optional<Beer> getById(Long id) {
        return  postingRepository.findById(id);
    }

    public Beer add(Beer posting) {
        return postingRepository.save(posting);
    }
    public Optional<Beer> upDate(Long id, Beer newPosting) {
        return postingRepository.findById(id)
                .map(posting -> {
                    newPosting.setId(posting.getId());
                    return postingRepository.save(newPosting);
                });
    }

    public void deleteById(Long id) {
        postingRepository.deleteById(id);
    }

    public List<Beer> getBeersByFirstBrewedAfter(String date) {
        String[] parts = date.split("/");
        int targetMonth = Integer.parseInt(parts[0]);
        int targetYear = Integer.parseInt(parts[1]);

        List<Beer> allBeers = postingRepository.findAll();
        List<Beer> filteredBeers = new ArrayList<>();


        for (Beer beer : allBeers) {
            String[] beerParts = beer.getFirst_brewed().split("/");
            int beerMonth = Integer.parseInt(beerParts[0]);
            int beerYear = Integer.parseInt(beerParts[1]);

            if (beerYear > targetYear || (beerYear == targetYear && beerMonth > targetMonth)) {
                filteredBeers.add(beer);
            }
        }
        return filteredBeers;
    }
}
