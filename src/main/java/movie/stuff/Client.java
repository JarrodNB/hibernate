package movie.stuff;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class Client {

    private String dest;
    private RestTemplate restTemplate;

    public Client(){
        restTemplate = new RestTemplate();
    }

    private Movie getMovie(int id) throws MovieDoesNotExistException {
        try {
            dest = "http://localhost:8080/getMovie/";
            ResponseEntity<Movie> response = restTemplate.getForEntity(dest + id, Movie.class);
            Movie movie = response.getBody();
            return movie;
        } catch(HttpServerErrorException e){
            throw new MovieDoesNotExistException();
        }
    }

    private Movie getMovie(String title) throws MovieDoesNotExistException {
        try {
            dest = "http://localhost:8080/getMovieByTitle/";
            ResponseEntity<Movie> response = restTemplate.getForEntity(dest + title, Movie.class);
            Movie movie = response.getBody();
            return movie;
        } catch (HttpServerErrorException e) {
            throw new MovieDoesNotExistException();
        }
    }

    private int createMovie(Movie movie){
        dest = "http://localhost:8080/createMovie";
        HttpEntity<Movie> request = new HttpEntity<>(movie);
        ResponseEntity movieCreate = restTemplate.postForEntity(dest, request, Movie.class);
        return movieCreate.getStatusCodeValue();
    }

    private Movie[] getAll(){
        dest = "http://localhost:8080/getMovies";
        ResponseEntity<Movie[]> responses = restTemplate.getForEntity(dest, Movie[].class);
        Movie[] movies = responses.getBody();
        return movies;
    }

    private void deleteMovie(int id){
        dest = "http://localhost:8080/deleteMovie/" + id;
         restTemplate.delete(dest);
    }

    private void updateMovie(Movie movie){
        dest = "http://localhost:8080/updateMovie";
        HttpEntity<Movie> putRequest = new HttpEntity<>(movie);
        restTemplate.put(dest, putRequest);
    }

    private Movie inputMovie(){
        Movie movie = new Movie();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title");
        movie.setTitle(scanner.nextLine());
        System.out.println("Enter the description");
        movie.setDescription(scanner.nextLine());
        System.out.println("Enter the genre");
        movie.setGenre(scanner.nextLine());
        System.out.println("Enter the rating out of 10");
        movie.setRate(scanner.nextDouble());
        movie.setRateNum(10);
        return movie;
    }

    private void displayMovies() {
        Movie[] movies = getAll();
        for (Movie movie : movies){
            System.out.println(movie);
            System.out.println("---------------------------------------------------");
        }
    }

    private void userMovie(){
        Movie movie = inputMovie();
        createMovie(movie);
        System.out.println(movie);
        System.out.println("Creating.....");
    }

    private void update() throws MovieDoesNotExistException {
        int id = searchByTitle(true).getId();
        Movie movie = inputMovie();
        movie.setId(id);
        updateMovie(movie);
        System.out.println("Updating.....");
    }

    private void delete() throws MovieDoesNotExistException {
        deleteMovie(searchByTitle(true).getId());
        System.out.println("Deleting.....");
    }

    private Movie searchByTitle(boolean display) throws MovieDoesNotExistException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the title of the movie");
        String title = scanner.nextLine();
        Movie movie = getMovie(title);
        if (display){
            System.out.println(movie);
        }
        return movie;
    }

    public boolean ui() throws MovieDoesNotExistException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an option below. Input the number. Enter 0 to quit");
        System.out.println("1. Display all movies");
        System.out.println("2. Create Movie");
        System.out.println("3. Update Movie");
        System.out.println("4. Delete Movie");
        System.out.println("5. Search By Title");
        int input = scanner.nextInt();
        switch(input){
            case 0: return false;
            case 1:
                displayMovies();
                break;
            case 2:
                userMovie();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            case 5:
                searchByTitle(true);
                break;
            default:
                System.out.println("Please input just the number");
        }
        return true;
    }

    public static void main(String[] args){
        Client client = new Client();
        boolean trigger = true;
        while (trigger){
            try {
                trigger = client.ui();
            } catch (MovieDoesNotExistException e) {
                System.out.println("Movie does not exist");
            } catch (InputMismatchException e){
                System.out.println("That is not a valid entry");
            } catch (ResourceAccessException e){
                System.out.println("You are not connected to our server! Please connect");
            }
        }
    }

    private class MovieDoesNotExistException extends Throwable {
    }
}
