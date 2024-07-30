package com.example.bhdi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AboutUs extends AppCompatActivity {

    private TextView v1,v2,v3;
    Integer user = 1, post = 1, comment = 1;
    String s1=" ",s2="\n",s3="\n\n";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        v1 = findViewById(R.id.ab);
        v2 = findViewById(R.id.ab2);
        v3 = findViewById(R.id.ab3);
        button = findViewById(R.id.btn_generate_pdf);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Registered Users");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long rowCountLong = dataSnapshot.getChildrenCount();
                int rowCount = (int) rowCountLong;
                user = (int)  rowCount;
                v1.setText("Total number of Registered Users = "+ user);
                //lastpage = (rowCount/5);
                // String rowCountText = "Number of rows: " + rowCount;
                //Toast.makeText(getApplicationContext(), rowCountText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });

        DatabaseReference databaseRef2 = FirebaseDatabase.getInstance().getReference("Posts");

        databaseRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long rowCountLong = dataSnapshot.getChildrenCount();
                int rowCount = (int) rowCountLong;
                post = (int)  rowCount;
                v2.setText("Total Posts by all users = "+ post);
                s1 = "\n";
                //lastpage = (rowCount/5);
                // String rowCountText = "Number of rows: " + rowCount;
                //Toast.makeText(getApplicationContext(), rowCountText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });

        DatabaseReference databaseRef3 = FirebaseDatabase.getInstance().getReference("Comments");

        databaseRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long rowCountLong = dataSnapshot.getChildrenCount();
                int rowCount = (int) rowCountLong;
                comment = (int)  rowCount;
                v3.setText("Total Comments by all users = "+ comment);
                //lastpage = (rowCount/5);
                // String rowCountText = "Number of rows: " + rowCount;
                //Toast.makeText(getApplicationContext(), rowCountText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePdf();
            }
        });




    }

    private void generatePdf() {
        // Create a PDF document
        PdfDocument document = new PdfDocument();

        // Create a page
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(500, 500, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        // Create a canvas
        Canvas canvas = page.getCanvas();
        canvas.drawColor(Color.WHITE);


//        v1.setText("User = "+user);
//        v2.setText("       , Posts = " + post);
//        v3.setText("                  , Comments = " +comment);
//        v1.setText(""+user);
//        v2.setText(s2 + "Posts:" + post);
//        v3.setText(s3 + "Coment:" +comment);

        v1.setText("Total number of Registered Users = "+ user);
        v2.setText("Total Posts by all users = "+ post);
        v3.setText("Total Comments by all users = "+ comment);


        // Create a Paint object for text properties
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(12f);

        // Draw the views on the canvas
        float y = v1.getY();


// Draw a blank line before textView1
        y += v1.getPaint().descent() - v1.getPaint().ascent();

        canvas.drawText(v1.getText().toString(), v1.getX(), y, paint);
        y += v1.getPaint().descent() - v1.getPaint().ascent();

        canvas.drawText(v2.getText().toString(), v2.getX(), y, paint);
        y += v2.getPaint().descent() - v2.getPaint().ascent();

        canvas.drawText(v3.getText().toString(), v3.getX(), y, paint);


//        // Draw the view on the canvas
//       v1.draw(canvas);
//       v2.draw(canvas);
//       v3.draw(canvas);

//        v1.setText("Total number of Registered Users = "+ user);
//        v2.setText("Total Posts by all users = "+ post);
//        v3.setText("Total Comments by all users = "+ comment);

        // Finish the page
        document.finishPage(page);

        // Get the directory for app-specific files
        File directory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if (directory != null) {
            // Create a file to save the PDF
            File pdfFile = new File(directory, "generated_pdf.pdf");

            try {
                // Write the PDF document to a file
                FileOutputStream outputStream = new FileOutputStream(pdfFile);
                document.writeTo(outputStream);
                outputStream.close();
                Toast.makeText(this, "PDF generated and saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to generate PDF", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to access app-specific directory", Toast.LENGTH_SHORT).show();
        }

        // Close the PDF document
        document.close();
    }
}

