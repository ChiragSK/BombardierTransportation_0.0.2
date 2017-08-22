package bt.com.bombardiertransportation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import bt.com.bombardiertransportation.Device;
import bt.com.bombardiertransportation.QRcreate;
import bt.com.bombardiertransportation.R;

public class QRdevice extends Fragment {

    EditText deviceid;
    EditText serialNo;
    AutoCompleteTextView ownerinfo;
    Spinner devicetype;
    String Text;

    private DatabaseReference mDatabase,userReference;

    Button button;
    public static String jsonString = new String();

    Device Device;
    public QRdevice() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrdevice, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner_devicetypeqr);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.DeviceType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getActivity().setTitle("Generate QR code");

        button = (Button)this.getActivity().findViewById(R.id.generateQr);
        serialNo = (EditText)this.getActivity().findViewById(R.id.editSerialNo);
        deviceid= (EditText)this.getActivity().findViewById(R.id.editDeviceID);
        devicetype = (Spinner)this.getActivity().findViewById(R.id.spinner_devicetypeqr);
        //final String Text = dropdown.getSelectedItem().toString();

        userReference = FirebaseDatabase.getInstance().getReference().child("users");
        final ArrayAdapter<String> autoCompleteUsers = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot tempSnapshot : dataSnapshot.getChildren()){
                    String userName = tempSnapshot.child("name").getValue(String.class);
                    autoCompleteUsers.add(userName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        ownerinfo = (AutoCompleteTextView)this.getActivity().findViewById(R.id.editOwnerid);
        ownerinfo.setThreshold(1);
        ownerinfo.setAdapter(autoCompleteUsers);


        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {

                Device device = new Device();
                device.deviceId = deviceid.getText().toString().trim();
                device.serialNo = serialNo.getText().toString().trim();
                device.owner = ownerinfo.getText().toString().trim();
                device.type= devicetype.getSelectedItem().toString();
                device.currentlyWith = device.owner;

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("devices").push().setValue(device);
                //Toast.makeText(getActivity(), "Added to database", Toast.LENGTH_LONG).show();

                Gson gson = new Gson();
                jsonString = gson.toJson(device);
                //Toast.makeText(getActivity(), jsonString, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), QRcreate.class);
                intent.putExtra("qrstring", jsonString);
                startActivity(intent);

            }  });

    }
}