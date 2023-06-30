package sg.edu.np.med.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    ImageView image;
    private final RecyclerViewInterface recyclerViewInterface;
    public ListViewHolder (View itemView, RecyclerViewInterface recyclerViewInterface){
        super(itemView);
        //the whole object data is going to unwrap here
        name = itemView.findViewById(R.id.textView10);
        description = itemView.findViewById(R.id.textView11);
        image = itemView.findViewById(R.id.imageView);
        this.recyclerViewInterface = recyclerViewInterface;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ListViewHolder.this.recyclerViewInterface !=null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        ListViewHolder.this.recyclerViewInterface.onItemClick(position);
                    }

                }
            }
        });
    }

}
