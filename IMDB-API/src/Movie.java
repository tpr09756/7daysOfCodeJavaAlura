import java.net.URL;

public class Movie{
    String title;
    String image;
    String year;
    String rating;



    public void setTitle(String fullTitle) {
        this.title = fullTitle;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public void setRating(String imDbRating) {
        this.rating = imDbRating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", year='" + year + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }


}
