package Scenarios;


import Builders.BookingBuilder;
import Builders.BookingDatesBuilder;
import Models.RequestsModel.CreateBookingRequest;
import Models.RequestsModel.BookingDates;
import Utils.*;
import com.github.javafaker.Faker;
import org.junit.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Booking_PositiveCases extends BaseTest{
    Faker faker = new Faker();

    // Generate a number between 1(inclusive) to 999 (exclusive)
    public int randomNum = faker.number().numberBetween(1, 999);
    public  String token = "";
    public  String bookingId = "";
    public  String Firstname = propertiesReader.getValues("Firstname")+randomNum;
    public  String Lastname = propertiesReader.getValues("Lastname")+randomNum;
    public  String checkin = propertiesReader.getValues("checkin");
    public  String checkout = propertiesReader.getValues("checkout");
    public  String TotalPrice = propertiesReader.getValues("TotalPrice");
    public  String DepositPaid = propertiesReader.getValues("DepositPaid");
    public  String AdditonalNeeds = propertiesReader.getValues("AdditonalNeeds");
    public String url = propertiesReader.getEndPointUrl("base_url");
    public String bookingurl = propertiesReader.getEndPointUrl("booking_url");


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

    // Creating a booking with valid paramaters
    public void Test03_CreateBooking()
    {
        System.out.println("*****************************************************************");
        System.out.println("Create a new Booking");
        System.out.println("*****************************************************************");

        // Creating the BookingDates Object
        BookingDates bookingdates = new BookingDatesBuilder()
                .withcheckin(checkin)
                .withcheckout(checkout)
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

            System.out.println(response.getStatusCode());

            // Validating the ResponseModel Code
            Assert.assertEquals(200,response.getStatusCode());
            JsonPath jsonPathEvaluator = response.jsonPath();

            bookingId = jsonPathEvaluator.get("bookingid").toString();
            System.out.println("Booking ID : " + bookingId);
            System.out.println("First name : " + jsonPathEvaluator.get("booking.firstname"));
            System.out.println("Last name : " + jsonPathEvaluator.get("booking.lastname"));
            System.out.println("Total Price : " + jsonPathEvaluator.get("booking.totalprice"));
            System.out.println("Is Deposit Paid : " + jsonPathEvaluator.get("booking.depositpaid"));
            System.out.println("CheckIn Date : " + jsonPathEvaluator.get("booking.bookingdates.checkin"));
            System.out.println("CheckOut Date : " + jsonPathEvaluator.get("booking.bookingdates.checkout"));
            System.out.println("Additional Needs : " + jsonPathEvaluator.get("booking.additionalneeds"));
        }
        catch (Exception ex)
        {
            System.out.println("Code terminated");
            ex.printStackTrace();

        }
    }

    // Getting Booking details from an ID
    public void Test04_GetBookingByID()
    {
        System.out.println("*****************************************************************");
        System.out.println("Retrieving Booking details based on booking ID created for new Booking");
        System.out.println("*****************************************************************");

        try {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .pathParam("ID", bookingId)
                    .when()
                    .get(bookingurl+"/{ID}");

            Assert.assertEquals(response.getStatusCode(), 200);
            JsonPath jsonPathEvaluator = response.jsonPath();
            Assert.assertEquals(response.getStatusCode(),200);
            Assert.assertTrue(jsonPathEvaluator.get("firstname").toString().contains(Firstname));
            Assert.assertTrue(jsonPathEvaluator.get("lastname").toString().contains(Lastname));
            System.out.println("*****************************************************************");
            System.out.println("Booking Details retrieved match correctly!!");
            System.out.println("*****************************************************************");
        }
        catch (Exception ex){
            ex.printStackTrace();

        }
    }

    // Updating details of a Booking
        public void Test05_UpdateBooking() {
        System.out.println("*****************************************************************");
        System.out.println("Placing back the original first and last name for the above updated booking");
        System.out.println("*****************************************************************");
        try {
            Response response = given()
                    .cookie("token=" + token)
                    .pathParam("ID", bookingId)
                    .body("{\"firstname\":\"updated_Test\", \"lastname\":\"updated_Booking\"}")
                    .when()
                    .patch(bookingurl + "/{ID}");

            Assert.assertEquals(response.getStatusCode(), 403);
            System.out.println("*****************************************************************");
            System.out.println("Booking Details are restricted to be updated!!");
            System.out.println("*****************************************************************");
        } catch (Exception ex) {
            ex.printStackTrace();


        }
    }

    //Deleting a booking
        public void Test06_DeleteBooking() {
            System.out.println("*****************************************************************");
            System.out.println("Deleting the above created Booking");
            System.out.println("*****************************************************************");
            try {
                Response res = given()
                        .header("Authorization", "Bearer " + token)
                        .accept("application/json")
                        .contentType("application/json")
                        .pathParam("ID","25")
                        .when()
                        .delete(bookingurl+"/{ID}");
                String body = res.getBody().asString();
                System.out.println("Booking details are deleted : " + body);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }