import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImdbJsonParser {
    private String json;

    public ImdbJsonParser(String json){
        this.json = json;
    }

    public List<Movie> parse() {
        JSONArray moviesArray = parseJsonMovies(json);

        List<Movie> movies= new ArrayList<>();
        for (int i =0;i<moviesArray.length();i++){
            Movie movie = new Movie();
            movie.setTitle(moviesArray.getJSONObject(i).getString("fullTitle"));
            movie.setImage(moviesArray.getJSONObject(i).getString("image"));
            movie.setYear(moviesArray.getJSONObject(i).getString("year"));
            movie.setRating(moviesArray.getJSONObject(i).getString("imDbRating"));

            movies.add(movie);
        }
        return movies;
    }

    private JSONArray parseJsonMovies(String json) {
        JSONObject obj = new JSONObject(json);
        return obj.getJSONArray("items");
    }

    private List<String> parseTitles(JSONArray movies){
        List<String> titles = new ArrayList<>();
        for (int i =0;i<movies.length();i++) {
            titles.add(movies.getJSONObject(i).getString("fullTitle"));
        }
        return titles;
    }

    private List<String> parseUrlImages(JSONArray movies){
        List<String> urlImages = new ArrayList<>();
        for (int i =0;i<movies.length();i++){
            urlImages.add(movies.getJSONObject(i).getString("image"));
        }
        return urlImages;


    }
    private List<String> parseYears(JSONArray movies) {
        List<String> years = new ArrayList<>();
        for (int i = 0; i < movies.length(); i++) {
            years.add(movies.getJSONObject(i).getString("imDbRating"));
        }
        return years;
    }
    private List<String> parseRatings(JSONArray movies) {
        List<String> ratings = new ArrayList<>();
        for (int i = 0; i < movies.length(); i++) {
            ratings.add(movies.getJSONObject(i).getString("imDbRating"));
        }
        return ratings;
    }

/*
    public List<Movie> parse() {
        String[] moviesArray = parseJsonMovies(json);

        List<String> titles = parseTitles(moviesArray);
        List<String> urlImages = parseUrlImages(moviesArray);
        List<String> ratings = parseRatings(moviesArray);
        List<String> years = parseYears(moviesArray);

        List<Movie> movies = new ArrayList<>();

        for (int i =0; i < titles.size(); i++) {
            movies.add(new Movie(titles.get(i), urlImages.get(i) , ratings.get(i), years.get(i)));
        }
        return movies;
    }

    private  String[] parseJsonMovies(String json) {
        Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("no match in " + json);
        }

        String[] moviesArray = matcher.group(1).split("\\},\\{");
        moviesArray[0] = moviesArray[0].substring(1);
        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, lastString.length() - 1);
        return moviesArray;
    }

    private  List<String> parseTitles(String[] moviesArray) {
        return parseAttribute(moviesArray, 3);
    }

    private  List<String> parseUrlImages(String[] moviesArray) {
        return parseAttribute(moviesArray, 5);
    }

    private  List<String> parseRatings(String[] moviesArray) {
        return parseAttribute(moviesArray, 7);
    }

    private  List<String> parseYears(String[] moviesArray) {
        return parseAttribute(moviesArray, 4);
    }


    private  List<String> parseAttribute(String[] jsonMovies, int pos) {
        return Stream.of(jsonMovies)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e -> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }*/
}
