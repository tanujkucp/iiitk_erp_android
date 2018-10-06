package in.ac.iiitkota.iiitk_erp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.ac.iiitkota.iiitk_erp.R;

public class FacultyCoursesAdapter extends RecyclerView.Adapter<FacultyCoursesAdapter.ViewHolder> {
    //todo initialize data needed

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView course_id, course_name, num_students;

        public ViewHolder(View v) {
            super(v);
            course_id = (TextView) v.findViewById(R.id.course_id);
            course_name = (TextView) v.findViewById(R.id.course_name);
            num_students = (TextView) v.findViewById(R.id.num_students);
        }

        public TextView getCourse_id() {
            return course_id;
        }

        public TextView getCourse_name() {
            return course_name;
        }

        public TextView getNum_students() {
            return num_students;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * <p>
     * String[] containing the data to populate views to be used by RecyclerView.
     */
    public FacultyCoursesAdapter() {
        //todo get the data from constructor and assign here
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_faculty_course, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //todo viewHolder.getCourse_id().setText("something");
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;
        //todo return info.size();
    }
}
