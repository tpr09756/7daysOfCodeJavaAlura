import java.io.IOException;
import java.io.PrintWriter;
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




    public static void main(String[] args) throws Exception {

        System.out.println("IMDB Top 250 Movies json");
        String apiKey = "k_nwci51h1";


        String json = new ImdbApiClient(apiKey).getBody();
        System.out.println(json);

        List<Movie> movies = new ImdbJsonParser(json).parse();
        System.out.println(movies);

        PrintWriter writer = new PrintWriter("content.html");
        new HTMLGenerator(writer).generate(movies);
        writer.close();


    }
}