package in.ac.iiitkota.iiitk_erp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.R;

public class FacultyPollAdapter extends RecyclerView.Adapter<FacultyPollAdapter.ViewHolder> {
    //initialize data needed
    public FacultyPollAdapterListener listener;
    ArrayList<String> IDs,names;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView student;
        public Button present,absent,leave;

        public ViewHolder(View v) {
            super(v);
            student=v.findViewById(R.id.name);
            present=v.findViewById(R.id.present);
            absent=v.findViewById(R.id.absent);
            leave=v.findViewById(R.id.leave);
            // handle clicks on buttons
            present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickPresent(view,getAdapterPosition());
                }
            });
            absent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickAbsent(view,getAdapterPosition());
                }
            });
            leave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickLeave(view,getAdapterPosition());
                }
            });
        }

        public TextView getStudent() {
            return student;
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     * <p>
     * String[] containing the data to populate views to be used by RecyclerView.
     */
    public FacultyPollAdapter(ArrayList<String> IDs,ArrayList<String> names,FacultyPollAdapterListener lis) {
        this.listener=lis;
        this.IDs=IDs;
        this.names=names;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_faculty_poll, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getStudent().setText(IDs.get(pos)+" | "+names.get(pos));
    }

    public interface FacultyPollAdapterListener{
        void onClickPresent(View v,int position);
        void onClickAbsent(View v,int position);
        void onClickLeave(View v,int position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return IDs.size();
    }
}
