package in.ac.iiitkota.iiitk_erp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.Models.News;
import in.ac.iiitkota.iiitk_erp.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    // initialize data needed
    Context context;
    ArrayList<News> news;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title,date,description;
        private final Button link;

        public ViewHolder(View v) {
            super(v);
            title =  v.findViewById(R.id.title);
            date = (TextView) v.findViewById(R.id.date);
            description = (TextView) v.findViewById(R.id.description);
            link=v.findViewById(R.id.link);
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News current = news.get(getAdapterPosition());
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    if (current.getFile()!=null){
                        i.setData(Uri.parse("http://www.iiitkota.ac.in/uploads/Document/"+news.get(getAdapterPosition()).getFile()));
                    }else if (current.getLink()!=null){
                        i.setData(Uri.parse(current.getLink()));
                    }
                    context.startActivity(i);
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }

        public TextView getDate() {
            return date;
        }

        public Button getLink() {
            return link;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * <p>
     * String[] containing the data to populate views to be used by RecyclerView.
     */
    public NewsAdapter(Context context, ArrayList<News> news) {
        this.context=context;
        this.news=news;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_news, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int pos) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        News current=news.get(pos);
        viewHolder.getTitle().setText(current.getContent().getTitle());
        viewHolder.getDate().setText(current.getCreate_time().substring(0,10));
        viewHolder.getDescription().setText(current.getContent().getText());
        if (current.getUploadTitle()!=null){
            viewHolder.getLink().setVisibility(View.VISIBLE);
            viewHolder.getLink().setText(current.getUploadTitle());
        }
        else viewHolder.getLink().setVisibility(View.GONE);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return news.size();
    }
}
