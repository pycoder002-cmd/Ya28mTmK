package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ReviewAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerViewClickListener {
    private Context mContext;
    private ArrayList<Review> reviews;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircularImageView avatar;
        public TextView comment;
        private RecyclerViewClickListener mListener;
        public SimpleRatingBar rating;
        public TextView username;

        public ViewHolder(View view, RecyclerViewClickListener recyclerViewClickListener) {
            super(view);
            this.avatar = (CircularImageView) view.findViewById(R.id.avatar);
            this.username = (TextView) view.findViewById(R.id.username);
            this.rating = (SimpleRatingBar) view.findViewById(R.id.rating);
            this.comment = (TextView) view.findViewById(R.id.comment);
            view.setOnClickListener(this);
            this.mListener = recyclerViewClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mListener.onClickView(getAdapterPosition());
        }

        public void setup(Review review) {
            try {
                Picasso.get().load(Uri.parse(review.getAvatar())).placeholder(R.drawable.ic_user_account).error(R.drawable.ic_user_account).resize(0, 200).networkPolicy(NetworkPolicy.NO_CACHE, new NetworkPolicy[0]).into(this.avatar);
            } catch (Exception e) {
                MainApp.log(getClass().getName(), e);
                this.avatar.setImageResource(R.drawable.ic_user_account);
            }
            try {
                this.username.setText(review.getFullName());
                this.rating.setRating(review.getRating().intValue());
                this.comment.setText(review.getComment());
            } catch (NullPointerException e2) {
                MainApp.log(getClass().getName(), e2);
                e2.printStackTrace();
            }
        }
    }

    public ReviewAppAdapter(Context context, ArrayList<Review> arrayList) {
        this.mContext = context;
        this.reviews = arrayList;
    }

    public void addReviews(@NonNull ArrayList<Review> arrayList) {
        this.reviews.addAll(this.reviews.size(), arrayList);
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.reviews.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).setup(this.reviews.get(viewHolder.getAdapterPosition()));
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener
    public void onClickView(int i) {
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_app_review, viewGroup, false), this);
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener
    public void onLongClickView(int i) {
        Toast.makeText(this.mContext, "onLongPress", 0).show();
    }
}
