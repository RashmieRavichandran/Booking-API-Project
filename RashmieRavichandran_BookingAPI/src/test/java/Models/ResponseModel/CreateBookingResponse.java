package Models.ResponseModel;
import Models.RequestsModel.BookingDates;

public class CreateBookingResponse {

    private String bookingid;
    private BookingDates[] bookingdates;
    private String firstname;
    private String lastname;
    private String totalprice;
    private String depositpaid;
    private String additionalneeds;


    public BookingDates[] getbookingdates() {
        return bookingdates;
    }

    public void setbookingdates(BookingDates[] bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String gettotalprice() {
        return totalprice;
    }

    public void settotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getdepositpaid() {
        return depositpaid;
    }

    public void setdepositpaid(String depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getadditionalneeds() {
        return additionalneeds;
    }

    public void setadditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
    public String getbookingid() {
        return bookingid;
    }

    public void setbookingid(String bookingid) {
        this.bookingid = bookingid;
    }


}
