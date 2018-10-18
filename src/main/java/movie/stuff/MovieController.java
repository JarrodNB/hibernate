package movie.stuff;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieDAO movieDAO;

    @RequestMapping(value="/createMovie", method = RequestMethod.POST)
    public void createMovie(@RequestBody Movie movie){
        movieDAO.createMovie(movie);
    }

    // format not being accepted?
    @ResponseBody
    @RequestMapping(value="/getMovies", method = RequestMethod.GET)
    public Movie[] getMovies(){
        if (movieDAO == null){
            System.out.println("dao is null");
        }
        return movieDAO.getAll();
    }

    @RequestMapping(value = "/getMovie/{id}", method= RequestMethod.GET)
    public Movie getMovie(@PathVariable("id") int id){
        return movieDAO.getMovie(id);
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
