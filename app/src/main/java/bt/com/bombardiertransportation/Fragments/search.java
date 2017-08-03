package bt.com.bombardiertransportation.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import bt.com.bombardiertransportation.Device;
import bt.com.bombardiertransportation.R;

public class search extends Fragment {

    Button button;
    private DatabaseReference mDatabase;
    ArrayList<Device> devices = new ArrayList<>();
    ArrayAdapter adapter;
    ListView lv;
    Spinner spinner;

    public search() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        spinner = (Spinner) getView().findViewById(R.id.spinner_devicetype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.DeviceType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        getActivity().setTitle("Search for Device");

        lv = (ListView) getView().findViewById(R.id.LV_Search);
        button = (Button) this.getActivity().findViewById(R.id.searchbt);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retreiveData();
            }
        });
    }

    public void retreiveData(){
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getUpdates(DataSnapshot ds)
    {
        devices.clear();

        for(DataSnapshot dataSnapshot: ds.getChildren())
        {
            Device d = new Device();
            d.setType(dataSnapshot.getValue(Device.class).getType());
            if(d.getType().equals(spinner.getSelectedItem().toString())) {
                d.setSerialNo(dataSnapshot.getValue(Device.class).getSerialNo());
                d.setOwner(dataSnapshot.getValue(Device.class).getOwner());
                devices.add(d);
            }
        }

        if (devices.size()>0)
        {
            adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_2, android.R.id.text1, devices){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(devices.get(position).getSerialNo());
                    text2.setText("Owner: " + devices.get(position).getOwner());
                    return view;
                }
            };
            lv.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(getActivity(),"No data", Toast.LENGTH_SHORT).show();
            devices.clear();
            adapter.notifyDataSetChanged();
        }
    }
}
