package com.nononsenseapps.filepicker;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.nononsenseapps.filepicker.AbstractFilePickerFragment;

/* loaded from: classes.dex */
public class FileItemAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected SortedList<T> mList = null;
    protected final LogicHandler<T> mLogic;

    public FileItemAdapter(@NonNull LogicHandler<T> logicHandler) {
        this.mLogic = logicHandler;
    }

    @Nullable
    protected T getItem(int i) {
        if (i == 0) {
            return null;
        }
        return this.mList.get(i - 1);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mList == null) {
            return 0;
        }
        return 1 + this.mList.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = i - 1;
        return this.mLogic.getItemViewType(i2, this.mList.get(i2));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 0) {
            this.mLogic.onBindHeaderViewHolder((AbstractFilePickerFragment.HeaderViewHolder) viewHolder);
        } else {
            int i2 = i - 1;
            this.mLogic.onBindViewHolder((AbstractFilePickerFragment.DirViewHolder) viewHolder, i2, this.mList.get(i2));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mLogic.onCreateViewHolder(viewGroup, i);
    }

    public void setList(@Nullable SortedList<T> sortedList) {
        this.mList = sortedList;
        notifyDataSetChanged();
    }
}
