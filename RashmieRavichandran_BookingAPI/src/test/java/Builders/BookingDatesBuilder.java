package Builders;
import Models.RequestsModel.BookingDates;

public class BookingDatesBuilder {

    private BookingDates bookingdates = new BookingDates();

    public BookingDatesBuilder withcheckin(String checkin) {
        bookingdates.setcheckin(checkin);
        return this;
    }

    public BookingDatesBuilder withcheckout(String checkout) {
        bookingdates.setcheckout(checkout);
        return this;
    }
    public BookingDates build() {
        return bookingdates;
    }
}
