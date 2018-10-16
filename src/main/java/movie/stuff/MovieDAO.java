package movie.stuff;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MovieDAO(){}

    public void create(Movie movie){
        entityManager.persist(movie);
    }

    public Movie getMovie(int id){
        return entityManager.find(Movie.class, id);
    }

    public Movie getMovie(String title){
        return  entityManager.createQuery("Select * From Movie Where title = " + title, Movie.class).getSingleResult();
    }

    public List<Movie> getAll(){
        return entityManager.createQuery("Select * From Movie", Movie.class).getResultList();
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