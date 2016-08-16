package dataAccessLayer;

/**
 * Created by Dotin school 6 on 8/7/2016.
 */
public class Customer {
    private long id;
    private String customerNumber;

    //getter
    public long getId() {
        return id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String toString() {
        return "id="+id + "Number="+customerNumber;
    }
}
