import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JSONPlaceholderFetcher {
    final HttpClient client = HttpClient.newBuilder().build();
    final private URI uri = URI.create("https://jsonplaceholder.typicode.com/posts/");

    public String getSinglePost(int id){
        try {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri.resolve(String.valueOf(id)))
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
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = getHttpResponse(request);
            return handleHttpResponse(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching the post: " + e.getMessage(), e);
        }
    }

    public boolean addPost(String post){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(post))
                    .build();

            HttpResponse<String> response = getHttpResponse(request);

            if (response.statusCode() == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Status code: " + response.statusCode());
                System.out.println("Post added successfully. Response Body:");
                System.out.println(response.body());
                return true;
            } else {
                throw new RuntimeException("Failed to fetch post. HTTP status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching the post: " + e.getMessage(), e);
        }
    }


    private HttpResponse<String> getHttpResponse(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String handleHttpResponse(HttpResponse<String> response) {
        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response Body:");
            return response.body();
        } else {
            throw new RuntimeException("Failed to fetch post. HTTP status code: " + response.statusCode());
        }
    }
}
