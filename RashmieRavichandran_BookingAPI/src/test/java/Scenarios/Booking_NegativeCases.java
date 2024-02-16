package Scenarios;


import Builders.BookingBuilder;
import Builders.BookingDatesBuilder;
import Models.RequestsModel.BookingDates;
import Models.RequestsModel.CreateBookingRequest;
import Utils.BaseTest;
import Utils.RequestHelper;
import Utils.ResourceHelper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Booking_NegativeCases extends BaseTest{
    Faker faker = new Faker();

    // Generate a number between 1(inclusive) to 999 (exclusive)
    public int randomNum = faker.number().numberBetween(1, 999);
    public  String token = "";
    public  String bookingId = "";
    public  String Firstname = propertiesReader.getValues("Firstname")+randomNum;
    public  String Lastname = propertiesReader.getValues("Lastname")+randomNum;
    public  String checkin = propertiesReader.getValues("checkin");
    public String invalidTotalPrice = propertiesReader.getValues("invalidTotalPrice");
    public  String TotalPrice = propertiesReader.getValues("TotalPrice");
    public  String DepositPaid = propertiesReader.getValues("DepositPaid");
    public  String AdditonalNeeds = propertiesReader.getValues("AdditonalNeeds");
    String url = propertiesReader.getEndPointUrl("base_url");
    String bookingurl = propertiesReader.getEndPointUrl("booking_url");


    // Performing a simple check if the API End point is up and running
    public void Test01_APIHealthCheck()
    {
        System.out.println("*****************************************************************");
        System.out.println("Performing a simple check if the API End point is up and running");
        System.out.println("*****************************************************************");
         given()
                .when()
                .get(url)
                .then()
                .statusCode(200);
        System.out.println("URL is up and running fine");
    }

    //Creating an authentication token, to use in future requests
    public void Test02_getAuthCode (){

        System.out.println("*****************************************************************");
        System.out.println("Retrieving an Authentication code");
        System.out.println("*****************************************************************");
        try {
        Response res = given()
                .body("{\"username\":User , \"password\":Password}")
                .when()
                .post(url);

           token = res.getBody().asString();
            System.out.println("Authentication Token :" + token);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creating a booking with no checkout Date
    public void Test03_CreateBooking_withCheckoutDateNotPresent()
    {
        System.out.println("*****************************************************************");
        System.out.println("Create a new Booking with no CheckOut date");
        System.out.println("*****************************************************************");

        // Creating the BookingDates Object
        BookingDates bookingdates = new BookingDatesBuilder()
                .withcheckin(checkin)
                .build();

        // Inserting the above created BookingDates object in the BookingList, because main object accepts as an array
        BookingDates[] datesList = new BookingDates[1];
        datesList[0] = bookingdates;

        // Creating the Booking Object
        CreateBookingRequest booking = new BookingBuilder()
                .withAdditionalneeds(AdditonalNeeds)
                .withFirstname(Firstname)
                .withLastname(Lastname)
                .withTotalprice(TotalPrice)
                .withBookingDates(datesList)
                .withDepositpaid(DepositPaid)
                .build();

        String json = RequestHelper.getJsonString(booking).replace("[","").replace("]",""); // Convert above created object into a String
        try {
            Response response = ResourceHelper.create(bookingurl, json);
            // Validating the ResponseModel Code
            Assert.assertEquals(500,response.getStatusCode());

            System.out.println("ResponseModel Code:" + response.getStatusCode() + "\nActual ResponseModel Body :" + response.asString());
            System.out.println("\"Error ResponseModel received,so this negative test is verified successfully !\"");
        }
        catch (Exception ex)
        {
            System.out.println("Code terminated");
            ex.printStackTrace();

        }
    }

    // Creating a booking with Invalid Format of Price
    public void Test03_CreateBooking_withInvalidFormatOfPrice()
    {
        System.out.println("*****************************************************************");
        System.out.println("Create a new Booking with Invalid Format of Price");
        System.out.println("*****************************************************************");

        // Creating the BookingDates Object
        BookingDates bookingdates = new BookingDatesBuilder()
                .withcheckin(checkin)
                .build();

        // Inserting the above created BookingDates object in the BookingList, because main object accepts as an array
        BookingDates[] datesList = new BookingDates[1];
        datesList[0] = bookingdates;

        // Creating the Booking Object
        CreateBookingRequest booking = new BookingBuilder()
                .withAdditionalneeds(AdditonalNeeds)
                .withFirstname(Firstname)
                .withLastname(Lastname)
                .withTotalprice(invalidTotalPrice)
                .withBookingDates(datesList)
                .withDepositpaid(DepositPaid)
                .build();

        String json = RequestHelper.getJsonString(booking).replace("[","").replace("]",""); // Convert above created object into a String
        try {
            Response response = ResourceHelper.create(bookingurl, json);

            // Validating the ResponseModel Code
            Assert.assertEquals(500,response.getStatusCode());
            System.out.println("ResponseModel Code:" + response.getStatusCode() + "\nActual ResponseModel Body :" + response.asString());
            System.out.println("\"Error ResponseModel received,so this negative test is verified successfully !\"");
        }
        catch (Exception ex)
        {
            System.out.println("Code terminated");
            ex.printStackTrace();

        }
    }



}