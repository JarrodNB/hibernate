package movie.stuff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class Client {

    @Autowired
    public MovieController controller;

    public Movie getAll(){
        if (controller == null){
            System.out.println("controller is null");
        }
        return controller.getMovie(1);
    }

    public static void main(String[] args){
        // https://www.baeldung.com/rest-template
        // Get
        String dest = "http://localhost:8080/getMovie";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Movie> response = restTemplate.getForEntity(dest + "/2", Movie.class);
        Movie movie = response.getBody();
        System.out.println(movie.toString());
        //Post
        dest = "http://localhost:8080/createMovie";
        HttpEntity<Movie> request = new HttpEntity<>(new Movie("title", "genre", 1.0, "description", 1));
        ResponseEntity movieCreate = restTemplate.postForEntity(dest, request, Movie.class);
        // Get All
        dest = "http://localhost:8080/getMovies";
        ResponseEntity<Movie[]> responses = restTemplate.getForEntity(dest, Movie[].class);
        Movie[] movies = responses.getBody();
        for (Movie m : movies){
            System.out.println(m);
        }
        //Delete
        int idDelete = 9;
        dest = "http://localhost:8080/deleteMovie/" + 9;
        restTemplate.delete(dest);
        // Put
        movie.setTitle("OMG I changed the title!");
        dest = "http://localhost:8080/updateMovie";
        HttpEntity<Movie> putRequest = new HttpEntity<>(movie);
        restTemplate.put(dest, putRequest);

        // response.getStatusCode();
    }
}
