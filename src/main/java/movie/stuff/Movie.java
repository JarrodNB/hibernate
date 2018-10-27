package movie.stuff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "movie")
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String genre;
    private double rate;
    private String description;
    private int rateNum;

    public Movie(){

    }

    public Movie(int id, String title, String genre, double rate, String description, int rateNum){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rate = rate;
        this.description = description;
        this.rateNum = rateNum;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() > 45){
            title = title.substring(0, 45);
        }
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre.length() > 45){
            genre = genre.substring(0, 45);
        }
        this.genre = genre;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        if (rate < 0){
            rate = 0;
        } else if (rate > 10){
            rate = 10;
        }
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 1000){
            description = description.substring(0,1000);
        }
        this.description = description;
    }

    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }

    @Override
    public String toString(){
        return "ID: " + id + " Title: " + title + " Genre: " + genre + " Rating: " + rate + "\nDescription: " + description;
    }

}
