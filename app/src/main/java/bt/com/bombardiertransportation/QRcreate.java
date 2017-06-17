package bt.com.bombardiertransportation;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import bt.com.bombardiertransportation.Fragments.QRdevice;

public class QRcreate extends AppCompatActivity {

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcreate);
        String value = getIntent().getStringExtra("qrstring");
        // text2QR = text3.getText().toString().trim();
        MultiFormatWriter multiFormatWriter= new MultiFormatWriter();
        try{
            BitMatrix bitMatrix= multiFormatWriter.encode(value, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image= (ImageView)this.findViewById(R.id.imageView);
            image.setImageBitmap(bitmap);
            Toast.makeText(this,QRdevice.jsonString,Toast.LENGTH_LONG).show();
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }

    }
}
