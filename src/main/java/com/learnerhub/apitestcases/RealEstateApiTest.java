package com.learnerhub.apitestcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class RealEstateApiTest {

    private String token;      // Store the authentication token
    private String propertyId; // Store the property ID from the POST request
    private String postTitle ="Apartment for Rent";
    @BeforeClass
    public void setUp() {
        // Set the base URL for the API
        RestAssured.baseURI = "http://98.70.73.39:5000/api";
    }

    @Test(priority = 1)
    public void testGetAuthToken() {
        // Send the login request to fetch the auth token
        String requestBody = "{ \"email\": \"mohan.balakrishnan23@gmail.com\", \"password\": \"Test1234\", \"role\":\"owner\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth/login");

        // Print response
        System.out.println("Auth Token Response: " + response.asString());

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch the auth token!");

        // Extract the token from the response
        token = response.jsonPath().getString("accessToken");
        Assert.assertNotNull(token, "Auth token is null!");
        System.out.println("Token: " + token);
    }

    @Test(priority = 2, dependsOnMethods = "testGetAuthToken")
    public void testPostRealEstate() {
        // File to upload
        File imageFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\house.jpg");

        // Send the POST request with Authorization header
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "multipart/form-data")
                .multiPart("title", postTitle)
                .multiPart("description", "Modern 2-bedroom apartment available for rent in a prime location.")
                .multiPart("price", "15000")
                .multiPart("category", "Apartment")
                .multiPart("area", "900")
                .multiPart("floors", "1")
                .multiPart("facing", "West")
                .multiPart("location", "Indira Nagar")
                .multiPart("streetName", "Park Avenue, Block A")
                .multiPart("realEstateImages", imageFile) // Upload the binary file
                .when()
                .post("/owner/real-estate");


        // Print response
        System.out.println("POST Response: " + response.asString());

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 201, "POST API failed!");

        // Extract and store the property ID
        propertyId = response.jsonPath().getString("realEstate.slug");
        Assert.assertNotNull(propertyId, "ID not generated for the property!");
        System.out.println("Generated ID: " + propertyId);
    }

    @Test(priority = 3, dependsOnMethods = "testPostRealEstate")
    public void testGetRealEstate() {
        // Use the propertyId obtained from the POST response
        Assert.assertNotNull(propertyId, "Property ID is null. Ensure POST test has run successfully.");

        // Send the GET request
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/owner/real-estate/" + propertyId);

        // Print response
        System.out.println("GET Response: " + response.asString());

        // Assert the status code
        Assert.assertEquals(response.getStatusCode(), 200, "GET API failed!");

        // Validate response fields
        String title = response.jsonPath().getString("realEstate.title");
        Assert.assertEquals(title, postTitle, "Title mismatch!");
        System.out.println("Title: " + title);
    }
}
