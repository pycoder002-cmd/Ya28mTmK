package com.miguelcatalan.materialsearchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SearchAdapter extends BaseAdapter implements Filterable {
    private ArrayList<String> data = new ArrayList<>();
    private boolean ellipsize;
    private LayoutInflater inflater;
    private Drawable suggestionIcon;
    private String[] suggestions;

    /* loaded from: classes.dex */
    private class SuggestionsViewHolder {
        ImageView imageView;
        TextView textView;

        public SuggestionsViewHolder(View view) {
            this.textView = (TextView) view.findViewById(R.id.suggestion_text);
            if (SearchAdapter.this.suggestionIcon != null) {
                this.imageView = (ImageView) view.findViewById(R.id.suggestion_icon);
                this.imageView.setImageDrawable(SearchAdapter.this.suggestionIcon);
            }
        }
    }

    public SearchAdapter(Context context, String[] strArr) {
        this.inflater = LayoutInflater.from(context);
        this.suggestions = strArr;
    }

    public SearchAdapter(Context context, String[] strArr, Drawable drawable, boolean z) {
        this.inflater = LayoutInflater.from(context);
        this.suggestions = strArr;
        this.suggestionIcon = drawable;
        this.ellipsize = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.data.size();
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return new Filter() { // from class: com.miguelcatalan.materialsearchview.SearchAdapter.1
            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                Filter.FilterResults filterResults = new Filter.FilterResults();
                if (!TextUtils.isEmpty(charSequence)) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : SearchAdapter.this.suggestions) {
                        if (str.toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            arrayList.add(str);
                        }
                    }
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                if (filterResults.values != null) {
                    SearchAdapter.this.data = (ArrayList) filterResults.values;
                    SearchAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.data.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        SuggestionsViewHolder suggestionsViewHolder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.suggest_item, viewGroup, false);
            suggestionsViewHolder = new SuggestionsViewHolder(view);
            view.setTag(suggestionsViewHolder);
        } else {
            suggestionsViewHolder = (SuggestionsViewHolder) view.getTag();
        }
        suggestionsViewHolder.textView.setText((String) getItem(i));
        if (this.ellipsize) {
            suggestionsViewHolder.textView.setSingleLine();
            suggestionsViewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        return view;
    }
}
