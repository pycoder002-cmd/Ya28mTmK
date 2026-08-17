package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener;

/* loaded from: classes.dex */
public class ScreenshotAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerViewClickListener {
    private Context mContext;
    private String[] screenshots;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewClickListener mListener;
        public ImageView screenshot;
        private String url;

        public ViewHolder(View view, RecyclerViewClickListener recyclerViewClickListener) {
            super(view);
            this.screenshot = (ImageView) view.findViewById(R.id.screenshot);
            view.setOnClickListener(this);
            this.mListener = recyclerViewClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mListener.onClickView(getAdapterPosition());
        }

        public void setup(String str) {
            this.url = str;
            MainApp.log("Url: " + str);
            try {
                Picasso.get().load(Uri.parse(str)).placeholder(R.drawable.ic_screenshot_default).error(R.drawable.ic_screenshot_default).resize(0, 200).into(this.screenshot);
            } catch (Exception e) {
                MainApp.log(getClass().getName(), e);
                this.screenshot.setImageResource(R.drawable.ic_screenshot_default);
            }
        }
    }

    public ScreenshotAppAdapter(Context context, String[] strArr) {
        this.mContext = context;
        this.screenshots = strArr;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.screenshots.length;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).setup(this.screenshots[viewHolder.getAdapterPosition()]);
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener
    public void onClickView(int i) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse(this.screenshots[i]), "image/*");
        this.mContext.startActivity(intent);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_screenshot_recycler, viewGroup, false), this);
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener
    public void onLongClickView(int i) {
    }
}
