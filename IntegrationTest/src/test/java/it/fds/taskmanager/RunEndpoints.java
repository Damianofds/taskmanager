package it.fds.taskmanager;

import it.fds.taskmanager.scheduler.SchedulerNewTasks;
import it.fds.taskmanager.scheduler.SchedulerPostponedChecks;

import com.google.gson.JsonElement;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.json.JsonParser;

import java.io.IOException;

public class RunEndpoints {

    public static final String url = "http://localhost:8080/task";



    @Test
    public void listOfTasks() throws IOException {

        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get("/task");
        String token = response.getBody().asString();
        System.out.println(token);

    }

    @Test

    public void detailsofUuid() throws IOException {

        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get("/{uuid}");
        String token = response.getBody().asString();
        System.out.println(token);

    }

    @Test

    public void createNewUuid() throws IOException {

        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.put("/create");
        SchedulerNewTasks schedulerNewTasks = new SchedulerNewTasks();
        schedulerNewTasks.generateTask();
        String token = response.getBody().asString();
        System.out.println(token);

    }

    @Test

    public void updateExistingUuid() throws IOException {

        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.post("/{uuid}");
        String token = response.getBody().asString();
        System.out.println(token);

    }

    @Test

    public void postponeTask() throws IOException {

        RestAssured.baseURI = url;
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.post("/{uuid}/postpone/");
        SchedulerPostponedChecks schedulerPostponedChecks = new SchedulerPostponedChecks();
        schedulerPostponedChecks.checkPostponed();
        String token = response.getBody().asString();
        System.out.println(token);

        Response response1 = requestSpecification.post("/{uuid}/resolve");
        String token1 = response1.getBody().asString();
        System.out.println(token1);

    }

}



