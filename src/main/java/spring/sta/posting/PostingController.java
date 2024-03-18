package spring.sta.posting;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.sta.posting.utils.Beer;

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
