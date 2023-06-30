package sg.edu.np.med.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button group1 = findViewById(R.id.Fragment1);
        Button group2 = findViewById(R.id.Fragment2);
        FrameLayout frameLayout = findViewById(R.id.frame);

        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate and add layout1.xml to the FrameLayout
                View view = getLayoutInflater().inflate(R.layout.group1, null);
                frameLayout.removeAllViews();
                frameLayout.addView(view);
            }
        });
        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate layout2.xml and add it to the FrameLayout
                View view = getLayoutInflater().inflate(R.layout.group2, null);
                frameLayout.removeAllViews();
                frameLayout.addView(view);
            }
        });
    }
}