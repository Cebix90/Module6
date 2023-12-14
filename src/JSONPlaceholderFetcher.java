import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JSONPlaceholderFetcher {
    final HttpClient client = HttpClient.newBuilder().build();
    URI example = URI.create("https://jsonplaceholder.typicode.com/posts/");

    public String getSinglePost(int id){
        try {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(example.resolve(String.valueOf(id)))
                .GET()
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());

            System.out.println("Response Body:");
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public String getAllPosts(){
//
//    }
//
//    public boolean addPost(String post){
//
//    }
}
