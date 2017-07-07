package bt.com.bombardiertransportation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import bt.com.bombardiertransportation.Fragments.release;

public class ReleaseDevice extends AppCompatActivity {
    private bt.com.bombardiertransportation.Fragments.release release;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_device);
        setTitle("Scan for Release Device");
        IntentIntegrator integrator = new IntentIntegrator(ReleaseDevice.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan device for release");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                System.out.println("Cancelled");
                Toast.makeText(this, "You cancelled the scanning!", Toast.LENGTH_LONG).show();
            } else {
                System.out.println("Worked: " + result.getContents());
                Toast.makeText(this, "scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            release.onActivityResult(requestCode, resultCode, data);
        }
    }
}
