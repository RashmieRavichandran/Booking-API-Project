package Models.RequestsModel;

public class CreateBookingRequest {

    private BookingDates[] bookingdates;
        private String Firstname;
        private String Lastname;
        private String Totalprice;
        private String Depositpaid;
        private String Additionalneeds;


        public BookingDates[] getbookingdates() {
            return bookingdates;
        }

        public void setDates(BookingDates[] bookingdates) {
            this.bookingdates = bookingdates;
        }

        public String getFirstname() {
            return Firstname;
        }

        public void setFirstname(String Firstname) {
            this.Firstname = Firstname;
        }

        public String getLastname() {
            return Lastname;
        }

        public void setLastname(String Lastname) {
            this.Lastname = Lastname;
        }

        public String getTotalprice() {
            return Totalprice;
        }

        public void setTotalprice(String Totalprice) {
            this.Totalprice = Totalprice;
        }

        public String getDepositpaid() {
            return Depositpaid;
        }

        public void setDepositpaid(String Depositpaid) {
            this.Depositpaid = Depositpaid;
        }

        public String getAdditionalneeds() {
            return Additionalneeds;
        }

        public void setAdditionalneeds(String Additionalneeds) {
            this.Additionalneeds = Additionalneeds;
        }

}
