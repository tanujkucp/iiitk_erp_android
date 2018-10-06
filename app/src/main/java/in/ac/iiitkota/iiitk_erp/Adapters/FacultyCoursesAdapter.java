package in.ac.iiitkota.iiitk_erp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Views.FacultyAttendanceActivity;

public class FacultyCoursesAdapter extends RecyclerView.Adapter<FacultyCoursesAdapter.ViewHolder> {
    //todo initialize data needed
    Context context;
    int arr[];

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView course_id, course_name, num_students;

        public ViewHolder(View v) {
            super(v);
            course_id = (TextView) v.findViewById(R.id.course_id);
            course_name = (TextView) v.findViewById(R.id.course_name);
            num_students = (TextView) v.findViewById(R.id.num_students);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    //todo pass the values of batchID, teacher username
                    context.startActivity(new Intent(context,FacultyAttendanceActivity.class));
                }
            });
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
    public FacultyCoursesAdapter(Context context,int arr[]) {
        this.context=context;
        this.arr=arr;
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
        return arr.length;
        //todo return info.size();
    }
}
