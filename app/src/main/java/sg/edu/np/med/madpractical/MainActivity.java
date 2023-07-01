package sg.edu.np.med.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Boolean isFollowed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent receivingEnd = getIntent();
        User user = getIntent().getParcelableExtra("user");
        TextView myHeader = findViewById(R.id.textView4);
        myHeader.setText(user.name);
        TextView description = findViewById(R.id.textView5);
        description.setText(user.description);
        isFollowed = user.isFollowed();
        Button myButton = findViewById(R.id.button4);
        Log.v("MainActivityUser", String.valueOf(user));
        MyDBHandler dbHandler = new MyDBHandler(this, "userDB.db", null, 1);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set the boolean to not equal the original state when the button is clicked.
                isFollowed = !isFollowed;
                dbHandler.updateUser(user);
                if(isFollowed == false)
                {
                    myButton.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else{
                    myButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button message = findViewById(R.id.button5);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activitySwitch = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(activitySwitch);
            }
        });
    }
}