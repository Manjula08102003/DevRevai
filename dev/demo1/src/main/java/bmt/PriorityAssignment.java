package bmt;


import java.io.IOException;
import okhttp3.*;

public class PriorityAssignment {

    public static void main(String[] args) {
        PriorityAssignment app = new PriorityAssignment();
        app.createDevRevIssue("New Issue Title", "This is the body of the issue.");
    }

    public void createDevRevIssue(String title, String body) {
        String priority = assignPriority(body);

        try {
            sendIssueToDevRev(title, body, priority);
            System.out.println("New issue created with priority: " + priority);
        } catch (IOException e) {
            System.err.println("Error creating issue: " + e.getMessage());
        }
    }

    private String assignPriority(String body) {
        if (body.contains("urgent")) {
            return "High";
        } else if (body.contains("important")) {
            return "Medium";
        } else {
            return "Low";
        }
    }

    private void sendIssueToDevRev(String title, String body, String priority) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Construct the request body according to DevRev API specifications
        // Modify this based on the actual requirements of the DevRev API
        RequestBody requestBody = new FormBody.Builder()
                .add("title", title)
                .add("body", body + "\n\nPriority: " + priority)
                .build();

        // Modify the API endpoint to the appropriate DevRev API endpoint
        Request request = new Request.Builder()
                .url("https://app.devrev.ai/manjula12345/works?quickAccessId=don%3Acore%3Advrv-us-1%3Adevo%2F1U4WAnUU33%3Avista%2F11&stage=triage%2Cbacklog%2Cprioritized%2Cin_development%2Cin_review%2Cin_testing%2Cin_deployment&type=issue")
                .post(requestBody)
                .header("Authorization", "Bearer Mytoken")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code() + ", Message: " + response.message());
            }
        }
    }
}

