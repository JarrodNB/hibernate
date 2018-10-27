package movie.stuff;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    /**
     *
     * POST /createMovie Creates a movie
     * GET /getMovies Returns all the movies
     * GET /getMovie/id Returns movie with matching ID
     * GET /getMovieByTitle Returns movie with matching title
     * PUT /updateMovie Updates the movie
     * DELETE /deleteMovie/id Deletes movie with matching ID
     *
     */




    @Autowired
    private MovieDAO movieDAO;

    @RequestMapping(value="/createMovie", method = RequestMethod.POST)
    public void createMovie(@RequestBody Movie movie){
        movieDAO.createMovie(movie);
    }

    @RequestMapping(value="/getMovies", method = RequestMethod.GET)
    public Object[] getMovies(){
        return movieDAO.getAll().toArray();
    }

    @RequestMapping(value = "/getMovie/{id}", method= RequestMethod.GET)
    public Movie getMovie(@PathVariable("id") int id){
        return movieDAO.getMovie(id);
    }

    @RequestMapping(value = "/getMovieByTitle/{title}", method= RequestMethod.GET)
    public Movie getMovie(@PathVariable("title") String title){
        return movieDAO.getMovie(title);
    }

    @RequestMapping(value="/updateMovie", method = RequestMethod.PUT)
    public void updateByGreeting(@RequestBody Movie movie) {
        movieDAO.updateMovie(movie);
    }

    @RequestMapping(value="/deleteMovie/{id}", method= RequestMethod.DELETE)
    public void deleteByID(@PathVariable("id") int id)
    {
        movieDAO.deleteMovie(id);
    }

}
