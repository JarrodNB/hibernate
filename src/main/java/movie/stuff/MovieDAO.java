package movie.stuff;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MovieDAO(){}

    public void createMovie(Movie movie){
        entityManager.persist(movie);
    }

    public Movie getMovie(int id){
        return entityManager.find(Movie.class, id);
    }

    public Movie getMovie(String title){
        title = "'" + title + "'";
        return  entityManager.createQuery("Select m From Movie m Where title = " + title, Movie.class).getSingleResult();
    }

    public List<Movie> getAll(){
        Query query = entityManager.createQuery("Select m From Movie m", Movie.class);
        return query.getResultList();
    }

    public void updateMovie(Movie movie){
        entityManager.merge(movie);
    }

    public void deleteMovie(int id){
        entityManager.remove(getMovie(id));
    }

    public void deleteMovie(String title){
        entityManager.remove(getMovie(title));
    }


}
