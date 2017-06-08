package bt.com.bombardiertransportation;

/**
 * Created by Chirag on 07-06-2017.
 */

public class User {

    public String name;
    public String email;
    public Integer empId;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, Integer empId) {
        this.name = name;
        this.email = email;
        this.empId = empId;
    }
}
