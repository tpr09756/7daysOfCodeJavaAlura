import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.*;


public class Main {

    private static final String KEY = "";
    private static final URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + KEY);

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        System.out.println("IMDB Top 250 Movies json");

        HttpClient client = HttpClient
                .newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(apiIMDB)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        String json = response.body();

        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("items");

        List<String> titles = new ArrayList<>();
        for (int i =0;i<arr.length();i++){
            titles.add(arr.getJSONObject(i).getString("fullTitle"));
        }
        //titles.forEach(System.out::println);
        System.out.println(titles);
        //System.out.println(arr.getJSONObject(0).getString("title"));
        List<String> urlImages = new ArrayList<>();
        for (int i =0;i<arr.length();i++){
            urlImages.add(arr.getJSONObject(i).getString("image"));
        }
        System.out.println(urlImages);
/*
        String[] moviesArray = parseJsonMovies(json);

        List<String> titles = parseTitles(moviesArray);
        titles.forEach(System.out::println);

        List<String> urlImages = parseUrlImages(moviesArray);
        urlImages.forEach(System.out::println);

        //outras listas para rating e years

 */
    }


        // Parse jsonMovies
        private static String[] parseJsonMovies(String json) {
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

        private static List<String> parseTitles(String[] moviesArray) {
            return parseAttribute(moviesArray, 3);
        }

        private static List<String> parseUrlImages(String[] moviesArray) {
            return parseAttribute(moviesArray, 5);
        }

        private static List<String> parseAttribute(String[] moviesArray, int pos) {
            return Stream.of(moviesArray)
                    .map(e -> e.split("\",\"")[pos])
                    .map(e -> e.split(":\"")[1])
                    .map(e -> e.replaceAll("\"", ""))
                    .collect(Collectors.toList());
        }
}