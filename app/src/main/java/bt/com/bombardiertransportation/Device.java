package bt.com.bombardiertransportation;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kripatel on 6/17/2017.
 */

public class Device{

    public String type;
    public String deviceId;
    public String serialNo;
    public String owner;
    public String currentlyWith;


    public void setType(String type) {
        this.type = type;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCurrentlyWith(String currentlyWith) {
        this.currentlyWith = currentlyWith;
    }

    public String getType() {
        return type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getOwner() {
        return owner;
    }

    public String getCurrentlyWith() {
        return currentlyWith;
    }

    // Default constructor required for calls to
    // DataSnapshot.getValue(Device.class)
    public Device() {
        this.currentlyWith="abc";
    }

    public Device(String type, String deviceId, String serialNo, String owner, String currentlyWith) {
        this.type = type;
        this.deviceId = deviceId;
        this.serialNo = serialNo;
        this.owner = owner;
        this.currentlyWith = currentlyWith;
    }
}
