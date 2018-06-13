package hookahdrivendevelopment.vote_up;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import hookahdrivendevelopment.vote_up.types.*;

/**
 * Created by timur on 11/06/2018.
 */

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Vote mVote;
        private TextView tvTitle;
        private TextView tvAuthor;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new GsonBuilder().create();
                    final String jsonVote = gson.toJson(mVote);

                    Intent myIntent = new Intent(view.getContext(), VoteViewActivity.class);
                    myIntent.putExtra("vote", jsonVote);

                    view.getContext().startActivity(myIntent);
                }
            });
        }

        public void assignVote(Vote vote) {
            mVote = vote;

            tvTitle.setText(mVote.title);
            tvAuthor.setText(mVote.author);
        }

        public void viewSelectedVote(View view) {
        }
    }

    private Votes mVotes;

    public VotesAdapter(Votes votes) {
        mVotes = votes;
    }

    @Override
    public VotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_vote, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VotesAdapter.ViewHolder holder, int position) {
        holder.assignVote(mVotes.votes.get(position));
    }

    @Override
    public int getItemCount() {
        return mVotes.votes.size();
    }
}
