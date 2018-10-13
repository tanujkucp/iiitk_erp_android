package in.ac.iiitkota.iiitk_erp.Utilities;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import in.ac.iiitkota.iiitk_erp.Models.PollStudent;

public class StudentDiffCallback extends DiffUtil.Callback {

    private final List<PollStudent> oldList,newList;

    public StudentDiffCallback(List<PollStudent> oldList,List<PollStudent> newList){
        this.oldList=oldList;
        this.newList=newList;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).getId().equals(newList.get(
                newPosition).getId()) ;
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        final PollStudent old=oldList.get(oldPosition);
        final PollStudent newItem=newList.get(newPosition);
        return (old.getName().equals(newItem.getName()) && (old.getStatus() == newItem.getStatus()) );
    }
}
