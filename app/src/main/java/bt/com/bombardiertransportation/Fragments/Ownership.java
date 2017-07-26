package bt.com.bombardiertransportation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import bt.com.bombardiertransportation.R;

public class Ownership extends Fragment {

    JSONObject scannedObj;
    TextView deviceType, serialNo, owner;

    private DatabaseReference mDatabase;

    public Ownership() {
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
        return inflater.inflate(R.layout.fragment_ownership, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles

        mDatabase = FirebaseDatabase.getInstance().getReference();

        deviceType = (TextView) this.getActivity().findViewById(R.id.editOwner_deviceType);
        serialNo = (TextView) this.getActivity().findViewById(R.id.editOwner_SerialNo);
        owner = (TextView) this.getActivity().findViewById(R.id.editOwner_currentOwner);

        getActivity().setTitle("Ownership Transfer");
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan device for Ownership Transfer");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
       }
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                System.out.println("Cancelled");
                Toast.makeText(getActivity(), "You cancelled the scanning!", Toast.LENGTH_LONG).show();
            } else {
                System.out.println("Worked: " + result.getContents());
                Toast.makeText(getActivity(), "scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                try{
                    scannedObj = new JSONObject(result.getContents());
                    deviceType.setText(scannedObj.getString("type"));
                    serialNo.setText(scannedObj.getString("serialNo"));
                    owner.setText(scannedObj.getString("owner"));

                    Query query = mDatabase.child("devices").orderByChild("serialNo").equalTo(scannedObj.getString("serialNo"));
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                    String obtainedOwner = dataSnapshot.child("owner").getValue(String.class);

                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Log.w("MYAPP","scannedObj.getString(\"type\")");
                }catch (JSONException e){
                    Log.e("MYAPP", "unexpected JSON exception", e);
                }



            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
    }
    }
}
