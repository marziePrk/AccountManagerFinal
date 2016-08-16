package dataAccessLayer;

/**
 * Created by Dotin school 6 on 8/7/2016.
 */
public class RealCustomer extends Customer{
    private long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String birthDate;
    private String nationalCode;

    //getter
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }


    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

}
