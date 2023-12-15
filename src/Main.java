public class Main {
    public static void main(String[] args) {
        JSONPlaceholderFetcher jsonPlaceholderFetcher = new JSONPlaceholderFetcher();

//        System.out.println(jsonPlaceholderFetcher.getSinglePost(1));
//        System.out.println(jsonPlaceholderFetcher.getAllPosts());

        String body = "{\"userId\": 1 , \"title\": \"foo\", \"body\": \"bar\"}";
        jsonPlaceholderFetcher.addPost(body);
    }
}