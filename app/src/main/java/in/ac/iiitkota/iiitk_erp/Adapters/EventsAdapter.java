package in.ac.iiitkota.iiitk_erp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.GlideApp;
import in.ac.iiitkota.iiitk_erp.Models.Event;
import in.ac.iiitkota.iiitk_erp.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    // initialize data needed
    Context context;
    ArrayList<Event> events;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title,organiser,description;
        private final ImageView image;

        public ViewHolder(View v) {
            super(v);
            title =  v.findViewById(R.id.title);
            organiser = (TextView) v.findViewById(R.id.organiser);
            description = (TextView) v.findViewById(R.id.description);
            image=v.findViewById(R.id.image);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getOrganiser() {
            return organiser;
        }

        public TextView getDescription() {
            return description;
        }

        public ImageView getImage() {
            return image;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * <p>
     * String[] containing the data to populate views to be used by RecyclerView.
     */
    public EventsAdapter(Context context, ArrayList<Event> events) {
        this.context=context;
        this.events=events;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_event, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        Event current=events.get(pos);
        viewHolder.getTitle().setText(current.getTitle());
        viewHolder.getOrganiser().setText(current.getOrganizer()+" | "+current.getDate().substring(0,10));
        viewHolder.getDescription().setText(current.getDescription());
        //load the image in imageView
        GlideApp.with(context).load("http://www.iiitkota.ac.in/uploads/"+current.getEventPhoto()).centerCrop()
                .placeholder(R.drawable.placeholder_image).into(viewHolder.getImage());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return events.size();
    }
}
