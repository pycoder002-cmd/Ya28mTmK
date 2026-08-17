package com.michaelflisar.dragselectrecyclerview;

import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class DragSelectionProcessor implements DragSelectTouchListener.OnAdvancedDragSelectListener {
    private boolean mFirstWasSelected;
    private HashSet<Integer> mOriginalSelection;
    private ISelectionHandler mSelectionHandler;
    private boolean mCheckSelectionState = false;
    private Mode mMode = Mode.Simple;
    private ISelectionStartFinishedListener mStartFinishedListener = null;

    /* loaded from: classes.dex */
    public interface ISelectionHandler {
        Set<Integer> getSelection();

        boolean isSelected(int i);

        void updateSelection(int i, int i2, boolean z, boolean z2);
    }

    /* loaded from: classes.dex */
    public interface ISelectionStartFinishedListener {
        void onSelectionFinished(int i);

        void onSelectionStarted(int i, boolean z);
    }

    /* loaded from: classes.dex */
    public enum Mode {
        Simple,
        ToggleAndUndo,
        FirstItemDependent,
        FirstItemDependentToggleAndUndo
    }

    public DragSelectionProcessor(ISelectionHandler iSelectionHandler) {
        this.mSelectionHandler = iSelectionHandler;
    }

    private void checkedUpdateSelection(int i, int i2, boolean z) {
        if (!this.mCheckSelectionState) {
            this.mSelectionHandler.updateSelection(i, i2, z, false);
            return;
        }
        while (i <= i2) {
            if (this.mSelectionHandler.isSelected(i) != z) {
                this.mSelectionHandler.updateSelection(i, i, z, false);
            }
            i++;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000c. Please report as an issue. */
    @Override // com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener.OnDragSelectListener
    public void onSelectChange(int i, int i2, boolean z) {
        boolean z2 = false;
        switch (this.mMode) {
            case Simple:
                if (this.mCheckSelectionState) {
                    checkedUpdateSelection(i, i2, z);
                    return;
                } else {
                    this.mSelectionHandler.updateSelection(i, i2, z, false);
                    return;
                }
            case ToggleAndUndo:
                while (i <= i2) {
                    checkedUpdateSelection(i, i, z ? !this.mOriginalSelection.contains(Integer.valueOf(i)) : this.mOriginalSelection.contains(Integer.valueOf(i)));
                    i++;
                }
                return;
            case FirstItemDependent:
                if (!z) {
                    z2 = this.mFirstWasSelected;
                } else if (!this.mFirstWasSelected) {
                    z2 = true;
                }
                checkedUpdateSelection(i, i2, z2);
                return;
            case FirstItemDependentToggleAndUndo:
                while (i <= i2) {
                    checkedUpdateSelection(i, i, z ? !this.mFirstWasSelected : this.mOriginalSelection.contains(Integer.valueOf(i)));
                    i++;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener.OnAdvancedDragSelectListener
    public void onSelectionFinished(int i) {
        this.mOriginalSelection = null;
        if (this.mStartFinishedListener != null) {
            this.mStartFinishedListener.onSelectionFinished(i);
        }
    }

    @Override // com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener.OnAdvancedDragSelectListener
    public void onSelectionStarted(int i) {
        this.mOriginalSelection = new HashSet<>();
        Set<Integer> selection = this.mSelectionHandler.getSelection();
        if (selection != null) {
            this.mOriginalSelection.addAll(selection);
        }
        this.mFirstWasSelected = this.mOriginalSelection.contains(Integer.valueOf(i));
        switch (this.mMode) {
            case Simple:
                this.mSelectionHandler.updateSelection(i, i, true, true);
                break;
            case ToggleAndUndo:
                this.mSelectionHandler.updateSelection(i, i, !this.mOriginalSelection.contains(Integer.valueOf(i)), true);
                break;
            case FirstItemDependent:
                this.mSelectionHandler.updateSelection(i, i, !this.mFirstWasSelected, true);
                break;
            case FirstItemDependentToggleAndUndo:
                this.mSelectionHandler.updateSelection(i, i, !this.mFirstWasSelected, true);
                break;
        }
        if (this.mStartFinishedListener != null) {
            this.mStartFinishedListener.onSelectionStarted(i, this.mFirstWasSelected);
        }
    }

    public DragSelectionProcessor withCheckSelectionState(boolean z) {
        this.mCheckSelectionState = z;
        return this;
    }

    public DragSelectionProcessor withMode(Mode mode) {
        this.mMode = mode;
        return this;
    }

    public DragSelectionProcessor withStartFinishedListener(ISelectionStartFinishedListener iSelectionStartFinishedListener) {
        this.mStartFinishedListener = iSelectionStartFinishedListener;
        return this;
    }
}
