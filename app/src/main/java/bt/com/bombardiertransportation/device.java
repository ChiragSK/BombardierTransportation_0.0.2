package bt.com.bombardiertransportation;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kripatel on 6/17/2017.
 */

public class Device {

    public String type;
    public String deviceId;
    public String serialNo;
    public String owner;
    public String currentlyWith;


    // Default constructor required for calls to
    // DataSnapshot.getValue(Device.class)
    public Device() {
    }

    public Device(String type, String deviceId, String serialNo, String owner, String currentlyWith) {
        this.type = type;
        this.deviceId = deviceId;
        this.serialNo = serialNo;
        this.owner = owner;
        this.currentlyWith = currentlyWith;
    }
}
