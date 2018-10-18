package movie.stuff;

import java.util.List;

public class Client {

    public static void main(String[] args){
        MovieController controller = new MovieController();
        List<Movie> movies = controller.getMovies();
//        MovieDAO dao = new MovieDAO();
//        //System.out.println(dao.getAll());
//        Movie movie = new Movie();
//        movie.setDescription("a movie");
//        movie.setTitle("a movie");
//        movie.setGenre("horror");
//        movie.setRate(3.3);
//        movie.setRateNum(10);
//        dao.createMovie(movie);
    }
}
