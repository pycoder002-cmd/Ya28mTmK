package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter;
import cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity;
import cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener;
import cu.uci.android.apklis.presentation.ui.util.LabelLayout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class UpdateAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UpdateViewClickListener {
    private ArrayList<AppDetails> apps = new ArrayList<>();
    private Context mContext;
    public final UpdateAppPresenter.View mView;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppDetails app;
        LinearLayout btnUpdate;
        LinearLayout btn_update_cancel;
        private Boolean canUpdate;
        CardView card;
        public TextView date;
        public ImageView icon;
        private ImageView iv_cancel_download;
        private UpdateViewClickListener mListener;
        public TextView name;
        LabelLayout price;
        private Integer progress;
        TextView tvProgress;
        TextView tv_progress_cancel;
        public TextView version;

        public ViewHolder(View view, UpdateViewClickListener updateViewClickListener) {
            super(view);
            this.progress = -1;
            this.canUpdate = true;
            this.card = (CardView) view.findViewById(R.id.cardApp);
            this.icon = (ImageView) view.findViewById(R.id.app_icon);
            this.name = (TextView) view.findViewById(R.id.app_name);
            this.version = (TextView) view.findViewById(R.id.app_version);
            this.date = (TextView) view.findViewById(R.id.app_date);
            this.btnUpdate = (LinearLayout) view.findViewById(R.id.btn_update);
            this.btn_update_cancel = (LinearLayout) view.findViewById(R.id.btn_update_cancel);
            this.tvProgress = (TextView) view.findViewById(R.id.tv_progress);
            this.tv_progress_cancel = (TextView) view.findViewById(R.id.tv_progress_cancel);
            this.price = (LabelLayout) view.findViewById(R.id.price);
            this.iv_cancel_download = (ImageView) view.findViewById(R.id.iv_cancel_download);
            view.setOnClickListener(this);
            this.mListener = updateViewClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mListener.onClickView(getAdapterPosition());
        }

        public void setup(AppDetails appDetails) {
            this.app = appDetails;
            this.card.setVisibility(0);
            this.btnUpdate.setVisibility(0);
            MainApp.log("Icon: " + appDetails.getIcon());
            try {
                this.name.setText(appDetails.getName());
                this.version.setText(appDetails.getLastRelease().getPackage_versionName());
                this.date.setText(appDetails.getLastRelease().getPublishedAtFormated());
                if (this.progress.intValue() > 100) {
                    this.progress = 100;
                }
                if (this.progress.intValue() >= 0) {
                    this.tv_progress_cancel.setText(this.progress + "%");
                    this.canUpdate = false;
                } else {
                    this.tvProgress.setText(this.card.getContext().getString(R.string.update));
                    this.canUpdate = true;
                    this.btn_update_cancel.setVisibility(8);
                }
                this.btnUpdate.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.UpdateAppAdapter.ViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (ViewHolder.this.canUpdate.booleanValue()) {
                            ViewHolder.this.mListener.onClickUpdate(ViewHolder.this.getAdapterPosition());
                            ViewHolder.this.iv_cancel_download.setVisibility(0);
                        }
                    }
                });
                this.btn_update_cancel.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.UpdateAppAdapter.ViewHolder.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (ViewHolder.this.canUpdate.booleanValue()) {
                            return;
                        }
                        ViewHolder.this.mListener.onClickCancelDownload(ViewHolder.this.getAdapterPosition());
                        ViewHolder.this.btn_update_cancel.setVisibility(8);
                        ViewHolder.this.btnUpdate.setVisibility(0);
                        ViewHolder.this.iv_cancel_download.setVisibility(8);
                    }
                });
                Picasso.get().load(Uri.parse(appDetails.getIcon())).placeholder(R.drawable.ic_apk_default).error(R.drawable.ic_apk_default).into(this.icon);
            } catch (Exception e) {
                this.icon.setImageResource(R.drawable.ic_apk_default);
                MainApp.log(getClass().getName(), e);
            }
            try {
                if (appDetails.getPrice().doubleValue() <= Utils.DOUBLE_EPSILON) {
                    this.price.setLabelText("");
                    this.price.setLabelHeight(0);
                    return;
                }
                this.price.setLabelText("$" + appDetails.getPriceString());
                this.price.setLabelHeight(1);
            } catch (Exception e2) {
                MainApp.log(getClass().getName(), e2);
                this.price.setLabelText("");
                this.price.setLabelHeight(0);
            }
        }

        public void updateProgress(Integer num) {
            String string;
            this.progress = num;
            if (num.intValue() > 100) {
                num = 100;
            }
            if (num.intValue() >= 0) {
                string = num + "%";
                this.canUpdate = false;
            } else {
                string = this.card.getContext().getString(R.string.update);
                this.iv_cancel_download.setVisibility(8);
                this.canUpdate = true;
            }
            this.tv_progress_cancel.setText(string);
            this.btnUpdate.setVisibility(8);
            this.btn_update_cancel.setVisibility(0);
        }
    }

    public UpdateAppAdapter(UpdateAppPresenter.View view, Context context, RecyclerView recyclerView) {
        this.mView = view;
        this.mContext = context;
        notifyDataSetChanged();
    }

    public Integer getAppPositionByPackageName(String str) {
        int i = -1;
        if (this.apps != null) {
            for (int i2 = 0; i2 < this.apps.size(); i2++) {
                if (this.apps.get(i2).getPackageName().equals(str)) {
                    i = Integer.valueOf(i2);
                }
            }
        }
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.apps == null) {
            return 0;
        }
        return this.apps.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).setup(this.apps.get(viewHolder.getAdapterPosition()));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, viewHolder.getAdapterPosition(), list);
        } else if (list.get(0) instanceof Integer) {
            ((ViewHolder) viewHolder).updateProgress((Integer) list.get(0));
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener
    public void onClickCancelDownload(int i) {
        this.mView.onClickCancelDownload(this.apps.get(i));
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener
    public void onClickUpdate(int i) {
        this.mView.onClickUpdate(this.apps.get(i));
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener
    public void onClickView(int i) {
        String json = new Gson().toJson(this.apps.get(i));
        Intent intent = new Intent(this.mContext, (Class<?>) AppDetailsActivity.class);
        intent.putExtra("APP_JSON_PARAM", json);
        this.mContext.startActivity(intent);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_app_update, viewGroup, false), this);
    }

    public void updateApps(@NonNull ArrayList<AppDetails> arrayList) {
        if (this.apps != null) {
            this.apps.clear();
        }
        this.apps = arrayList;
        notifyDataSetChanged();
    }
}
