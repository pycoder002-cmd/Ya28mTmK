package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cu.uci.android.apklis.R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PermissionAdapter extends BaseAdapter {
    private final Context context;
    ArrayList<Permission> songs;

    public PermissionAdapter(Context context, ArrayList<Permission> arrayList) {
        this.context = context;
        this.songs = arrayList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.songs.size();
    }

    @Override // android.widget.Adapter
    public Permission getItem(int i) {
        return this.songs.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.activity_permission_item, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon);
        TextView textView = (TextView) inflate.findViewById(R.id.name);
        TextView textView2 = (TextView) inflate.findViewById(R.id.label);
        TextView textView3 = (TextView) inflate.findViewById(R.id.description);
        textView.setText(this.songs.get(i).getName());
        textView2.setText(this.songs.get(i).getLabel());
        textView3.setText(this.songs.get(i).getDescription());
        imageView.setImageDrawable(this.songs.get(i).getIcon());
        return inflate;
    }
}
