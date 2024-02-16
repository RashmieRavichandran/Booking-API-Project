package Models.RequestsModel;

public class BookingDates {

    private String checkin;
    private String checkout;

    public String getcheckin() {
        return checkin;
    }

    public void setcheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getcheckout() {
        return checkout;
    }

    public void setcheckout(String checkout) {
        this.checkout = checkout;
    }
}
