package bt.com.bombardiertransportation.Fragments;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import bt.com.bombardiertransportation.QRcreate;
import bt.com.bombardiertransportation.R;
import bt.com.bombardiertransportation.device;

import static bt.com.bombardiertransportation.R.id.devicetype;


public class QRdevice extends Fragment {

    EditText deviceid;
    EditText uniqueid;
    EditText ownerinfo;
    String Text;

    Button button;
    public static String jsonString = new String();

    device Device;
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
        uniqueid = (EditText)this.getActivity().findViewById(R.id.editUniqueId);
        deviceid= (EditText)this.getActivity().findViewById(R.id.editDeviceID);
        ownerinfo = (EditText)this.getActivity().findViewById(R.id.editOwnerid);

        //final String Text = dropdown.getSelectedItem().toString();
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                Device = new device();
                Device.deviceId = deviceid.getText().toString().trim();
                Device.UniqueId = uniqueid.getText().toString().trim();
                Device.Owner = ownerinfo.getText().toString().trim();

                Gson gson = new Gson();
                jsonString = gson.toJson(Device);
                Toast.makeText(getActivity(), jsonString, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), QRcreate.class);
                intent.putExtra("qrstring", jsonString);
                startActivity(intent);

            }  });

}
    }