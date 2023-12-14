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

            HttpResponse<String> response = getHttpResponse(request);
            return handleHttpResponse(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching the post: " + e.getMessage(), e);
        }
    }

    public String getAllPosts(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(example)
                    .GET()
                    .build();

            HttpResponse<String> response = getHttpResponse(request);
            return handleHttpResponse(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching the post: " + e.getMessage(), e);
        }
    }
//
//    public boolean addPost(String post){
//
//    }


    private HttpResponse<String> getHttpResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    private static String handleHttpResponse(HttpResponse<String> response) {
        System.out.println("Status code: " + response.statusCode());

        if (response.statusCode() == 200) {
            System.out.println("Response Body:");
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch post. HTTP status code: " + response.statusCode());
        }
    }
}
