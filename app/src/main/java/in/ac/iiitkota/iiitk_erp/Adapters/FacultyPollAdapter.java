package in.ac.iiitkota.iiitk_erp.Adapters;

import android.graphics.Color;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.Models.PollStudent;
import in.ac.iiitkota.iiitk_erp.Utilities.StudentDiffCallback;
import in.ac.iiitkota.iiitk_erp.R;

public class FacultyPollAdapter extends RecyclerView.Adapter<FacultyPollAdapter.ViewHolder> {
    //initialize data needed
    public FacultyPollAdapterListener listener;
    ArrayList<PollStudent> studentsData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView student;
        private final CardView card;
        public Button present, absent, leave;

        public ViewHolder(View v) {
            super(v);
            student = v.findViewById(R.id.name);
            present = v.findViewById(R.id.present);
            absent = v.findViewById(R.id.absent);
            leave = v.findViewById(R.id.leave);
            card = v.findViewById(R.id.card_poll);
            // handle clicks on buttons
            present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.setCardBackgroundColor(Color.GREEN);
                    listener.onClickPresent(view, getAdapterPosition());
                }
            });
            absent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.setCardBackgroundColor(Color.RED);
                    listener.onClickAbsent(view, getAdapterPosition());
                }
            });
            leave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.setCardBackgroundColor(Color.BLUE);
                    listener.onClickLeave(view, getAdapterPosition());
                }
            });
        }

        public TextView getStudent() {
            return student;
        }

        public CardView getCard() {
            return card;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * <p>
     * String[] containing the data to populate views to be used by RecyclerView.
     */
    public FacultyPollAdapter(ArrayList<PollStudent> data, FacultyPollAdapterListener lis) {
        this.listener = lis;
        this.studentsData = data;
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
        PollStudent singleData = studentsData.get(pos);
        viewHolder.getStudent().setText(singleData.getId() + " | " + singleData.getName());
        // also update the layout according to the status value
        int status = singleData.getStatus();
        switch (status) {
            case 0:
                viewHolder.getCard().setCardBackgroundColor(Color.GREEN);
                //todo other animations on cards
                break;
            case 1:
                viewHolder.getCard().setCardBackgroundColor(Color.RED);
                break;
            case 2:
                viewHolder.getCard().setCardBackgroundColor(Color.BLUE);
                break;
        }

    }

    public interface FacultyPollAdapterListener {

        void onClickPresent(View v, int position);

        void onClickAbsent(View v, int position);

        void onClickLeave(View v, int position);
    }

    public void updateStudentEntries(ArrayList<PollStudent> students) {
        final StudentDiffCallback diffCallback = new StudentDiffCallback(this.studentsData, students);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.studentsData.clear();
        this.studentsData.addAll(students);
        diffResult.dispatchUpdatesTo(this);
        Log.e("attend", "dispatched updates to adapter");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return studentsData.size();
    }
}
