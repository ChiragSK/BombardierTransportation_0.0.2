package bt.com.bombardiertransportation.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import bt.com.bombardiertransportation.R;
import bt.com.bombardiertransportation.ReleaseDevice;

import static android.R.attr.data;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link release.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link release#newInstance} factory method to
 * create an instance of this fragment.
 */
public class release extends Fragment {
    private release release;
    public release() {
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
        return inflater.inflate(R.layout.fragment_release, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Release Device");

        Intent intent = new Intent(getActivity(), ReleaseDevice.class);
        startActivityForResult(intent,1);



    }
}
