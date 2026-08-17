package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cu.uci.android.apklis.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ApklisAdapter extends BaseAdapter {
    Context context;
    ArrayList<ZipFile> files;
    private InputStream icon;
    private final String TAG = "myAdapter";
    ArrayList<JSONObject> manifiest = new ArrayList<>();

    public ApklisAdapter(Context context, ArrayList<ZipFile> arrayList) {
        this.context = context;
        this.files = arrayList;
    }

    private String getSize(Integer num) {
        Integer num2 = 1024;
        Integer valueOf = Integer.valueOf(num2.intValue() * 1024);
        Integer valueOf2 = Integer.valueOf(valueOf.intValue() * 1024);
        String str = "B";
        if (num.intValue() > valueOf2.intValue()) {
            num = Integer.valueOf(num.intValue() / valueOf2.intValue());
            str = "GB";
        } else if (num.intValue() > valueOf.intValue()) {
            num = Integer.valueOf(num.intValue() / valueOf.intValue());
            str = "MB";
        } else if (num.intValue() > num2.intValue()) {
            num = Integer.valueOf(num.intValue() / num2.intValue());
            str = "KB";
        }
        return Math.round(num.intValue()) + str;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.files.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.manifiest.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.xapklis_list, (ViewGroup) null);
        ZipFile zipFile = this.files.get(i);
        TextView textView = (TextView) inflate.findViewById(R.id.tvTitle);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvVersion);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tvSize);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ivIcon);
        try {
            InputStream inputStream = zipFile.getInputStream(zipFile.getEntry("manifest.json"));
            InputStream inputStream2 = zipFile.getInputStream(zipFile.getEntry("icon.png"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append('\n');
            }
            JSONObject jSONObject = new JSONObject(sb.toString());
            this.manifiest.add(i, jSONObject);
            textView.setText(jSONObject.getString("name"));
            textView3.setText("v" + jSONObject.getString("version_name"));
            textView2.setText(getSize(Integer.valueOf(jSONObject.getInt("total_size"))));
            imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream2));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return inflate;
    }
}
