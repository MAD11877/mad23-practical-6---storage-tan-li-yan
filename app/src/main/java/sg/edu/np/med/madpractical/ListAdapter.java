package sg.edu.np.med.madpractical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private ArrayList<User> user_list;
    //on click interface
    private final RecyclerViewInterface recyclerViewInterface;

    public ListAdapter(ArrayList<User> user, RecyclerViewInterface recyclerViewInterface){
        this.user_list= user;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        User userItem = user_list.get(viewType);
        String name = userItem.getName();
        char lastChar = name.charAt(name.length() - 1);
        int lastNum = Character.getNumericValue(lastChar);

        if (lastNum == 7) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_seven, parent,false);
            return new ListViewHolder(view, recyclerViewInterface);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_item, parent,false);
            return new ListViewHolder(view, recyclerViewInterface);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        User userItem = user_list.get(position);
        holder.name.setText(userItem.getName());
        holder.description.setText(userItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return user_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Return the position as the view type
        return position;
    }
}
