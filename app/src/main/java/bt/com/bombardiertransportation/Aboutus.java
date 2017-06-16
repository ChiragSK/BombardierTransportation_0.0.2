package bt.com.bombardiertransportation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        ImageView image=(ImageView)findViewById(R.id.kripal);
        Bitmap bm= BitmapFactory.decodeResource(getResources(), R.drawable.kripal);
        int width=500;
        int height=500;
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bm, width, height, true);

        image.setImageBitmap(resizedbitmap);

        ImageView image2=(ImageView)findViewById(R.id.chirag);
        Bitmap bm1= BitmapFactory.decodeResource(getResources(), R.drawable.chirag);
        int width1=500;
        int height1=500;
        Bitmap resizedbitmap1 = Bitmap.createScaledBitmap(bm1, width1, height1, true);

        image2.setImageBitmap(resizedbitmap1);



    }

}
