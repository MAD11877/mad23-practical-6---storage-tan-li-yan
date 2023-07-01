package sg.edu.np.med.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements  RecyclerViewInterface {
    ArrayList<User> myList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        MyDBHandler dbHandler = new MyDBHandler(this, "userDB.db", null, 1);

        Random random = new Random();
        for(int i = 0; i< 20; i++)

        {
            User myUser = new User();
            String name = "Name-" + random.nextInt();
            String description = "Description:" + random.nextInt();
            Boolean isFollowed = random.nextBoolean();
            myUser.setName(name);
            myUser.setDescription(description);
            myUser.setFollowed(isFollowed);
            dbHandler.addUser(myUser);
        }
        myList = dbHandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ListAdapter listAdapter = new ListAdapter(myList, this);
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onItemClick(int position) {
        User clickedUser = myList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
            builder.setTitle("Profile");
            builder.setMessage(clickedUser.getName());
            builder.setCancelable(false);
            String name = clickedUser.getName();
            String description = clickedUser.getDescription();
            Boolean isFollowed = clickedUser.isFollowed();
            builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    Bundle extras = new Bundle();
                    extras.putString("name", clickedUser.getName());
                    extras.putString("description", clickedUser.getDescription());
                    extras.putBoolean("isFollowed", clickedUser.isFollowed());
                    User user = new User(name,  description , 0, isFollowed);
                    Intent activityName = new Intent(ListActivity.this, MainActivity.class);
                    activityName.putExtra("user",user);
                    startActivity(activityName);
                }
            });
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

    }

}