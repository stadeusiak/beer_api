package spring.sta.posting;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import spring.sta.dto.Beer;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class PostingController {

    private final PostingService postingService;

    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @GetMapping
    public List<Beer> getAll(){
        return postingService.getAll();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable Long id) {
        return postingService.getById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/brewedAfter")
    public List<Beer> getByFirstBrewedAfter(@RequestParam("date") String firstBrewedAfter) {
        return postingService.getBeersByFirstBrewedAfter(firstBrewedAfter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer add(@Valid @RequestBody Beer posting) {
        return postingService.add(posting);
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable Long id, @Valid @RequestBody Beer posting) {
        return postingService.upDate(id, posting).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
         postingService.deleteById(id);
    }
}
