package Builders;
import Models.RequestsModel.CreateBookingRequest;
import Models.RequestsModel.BookingDates;

public class BookingBuilder {

        private  CreateBookingRequest booking = new CreateBookingRequest();

        public BookingBuilder withBookingDates(BookingDates[] bookingdates){
            booking.setDates(bookingdates);
            return this;
        }

    public BookingBuilder withFirstname( String Firstname){
        booking.setFirstname(Firstname);
        return this;
    }
    public BookingBuilder withLastname( String Lastname){
        booking.setLastname(Lastname);
        return this;
    }
    public BookingBuilder withTotalprice( String Totalprice){
        booking.setTotalprice(Totalprice);
        return this;
    }
    public BookingBuilder withDepositpaid( String Depositpaid){
        booking.setDepositpaid(Depositpaid);
        return this;
    }

    public BookingBuilder withAdditionalneeds( String Additionalneeds){
        booking.setAdditionalneeds(Additionalneeds);
        return this;
    }
    public CreateBookingRequest build(){
        return booking;
    }
}
