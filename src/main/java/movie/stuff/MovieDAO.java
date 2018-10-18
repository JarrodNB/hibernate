package movie.stuff;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieDAO {

    //?username=root&amp;password=Medic68w!

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
        return  entityManager.createQuery("Select * From Movie Where title = " + title, Movie.class).getSingleResult();

    }

    public Movie[] getAll(){
        if (entityManager == null){
            System.out.println("is null");
        }
        List<Movie> results = entityManager.createQuery("From Movie", Movie.class).getResultList();
        return (Movie[]) results.toArray();
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
