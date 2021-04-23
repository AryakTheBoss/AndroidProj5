package com.cs213.rutgerscafelol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * Class is a custom adapter for the recycler view to help show the items
 * @author mss390 amp487 Mayank Singamreddy Aryak Pande
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> listData;
    private LayoutInflater inflater;
    private ItemClickListener listener;


    /**
     * construct a new recycler view adapter
     * @param context current context
     * @param data the list of data
     */
   public RecyclerViewAdapter(Context context, List<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.listData = data;
    }


    /**
     * each row of the recycler view is set to the special layout file called recyclerview_row.xml
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }


    /**
     * set the row's text to the item in the appropiate position.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String orderItem = listData.get(position);
        holder.recyclerRow.setText(orderItem);
    }

    /**
     * gets the item count
     * @return number of items in the recycler view
     */
    @Override
    public int getItemCount() {
        return listData.size();
    }


    /**
     * this class represents a row of the recycler view
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recyclerRow;

        /**
         * construct a new row
         * @param itemView
         */
        ViewHolder(View itemView) {
            super(itemView);
            recyclerRow = itemView.findViewById(R.id.itemName);
            itemView.setOnClickListener(this);
        }

        /**
         * called when you click on any row of the recycler view
         * @param view
         */
        @Override
        public void onClick(View view) {
            if (listener != null) listener.onItemClick(view, getAdapterPosition());
        }
    }

    /**
     * get the item at position
     * @param pos position
     * @return true if found false otherwise
     */
    String getItem(int pos) {
        return listData.get(pos);
    }

    /**
     * sets the click listener for this adapter
     * @param itemClickListener
     */
    void setClickListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    /**
     * interface to be implemented by the activity the recycler view is in
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
