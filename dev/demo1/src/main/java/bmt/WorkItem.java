package bmt;


import okhttp3.*;
import java.io.IOException;

public class WorkItem {

    public static void main(String[] args) {
        createWorkItem("Work Item Title", "Work Item Description");
    }

    public static void createWorkItem(String title, String description) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("title", title)
                .add("description", description)
                .build();

        Request request = new Request.Builder()
                .url("https://app.devrev.ai/manjula12345/work-items")
                .post(requestBody)
                .header("Authorization", "Bearer Mytoken")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("Work item created successfully!");
            } else {
                System.err.println("Failed to create work item: " + response.body().string());
            }
        } catch (IOException e) {
            System.err.println("Error creating work item: " + e.getMessage());
        }
    }
}

