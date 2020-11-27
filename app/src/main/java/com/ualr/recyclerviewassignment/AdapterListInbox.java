package com.ualr.recyclerviewassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterListInbox extends RecyclerView.Adapter<AdapterListInbox.ViewHolder> {

    private Context ctx;
    private List<Inbox> items;
    int currentItemSelected;

    public AdapterListInbox(Context mContext, List<Inbox> items) {
        this.ctx = mContext;
        this.items = items;
        this.currentItemSelected = -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Inbox inbox = items.get(position);

        holder.from.setText(inbox.getFrom());
        holder.email.setText(inbox.getEmail());
        holder.message.setText(inbox.getMessage());
        holder.date.setText(inbox.getDate());
        holder.image_letter.setText(inbox.getFrom().substring(0, 1));
        toggleItemSelection(holder, inbox);
    }

    private void toggleItemSelection(ViewHolder holder, Inbox inbox) {
        if (inbox.isSelected()) {
            holder.image_checked.setVisibility(View.VISIBLE);
            holder.image_letter.setVisibility(View.GONE);
            holder.lyt_parent.setActivated(true);
            holder.lyt_thumbnail.setClickable(true);
        } else {
            holder.image_checked.setVisibility(View.GONE);
            holder.image_letter.setVisibility(View.VISIBLE);
            holder.lyt_parent.setActivated(false);
            holder.lyt_thumbnail.setClickable(false);
        }
    }

    public void addNewItem() {
        this.items.add(0, DataGenerator.getRandomInboxItem(this.ctx));
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView from, email, message, date, image_letter;
        public ImageView image, image_checked;
        public RelativeLayout lyt_checked, lyt_image, lyt_thumbnail;
        public View lyt_parent;

        public ViewHolder(View view) {
            super(view);
            lyt_thumbnail = view.findViewById(R.id.thumbnail);
            from = view.findViewById(R.id.from);
            email = view.findViewById(R.id.email);
            message = view.findViewById(R.id.message);
            date = view.findViewById(R.id.date);
            image_letter = view.findViewById(R.id.image_letter);
            image_checked = view.findViewById(R.id.image_checked);
            image = view.findViewById(R.id.image);
            lyt_checked = view.findViewById(R.id.lyt_checked);
            lyt_image = view.findViewById(R.id.lyt_image);
            lyt_parent = view.findViewById(R.id.lyt_parent);

            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentItemSelected != -1) {
                        items.get(currentItemSelected).toggleSelection();
                        notifyItemChanged(currentItemSelected);
                    }
                    if (getBindingAdapterPosition() != currentItemSelected) {
                        items.get(getBindingAdapterPosition()).toggleSelection();
                        notifyItemChanged(getBindingAdapterPosition());
                        currentItemSelected = getBindingAdapterPosition();
                    }
                }
            });

            lyt_thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getBindingAdapterPosition() == currentItemSelected) {
                        items.remove(getBindingAdapterPosition());
                        notifyItemRemoved(getBindingAdapterPosition());
                        currentItemSelected = -1;
                    }
                }
            });
        }
    }
}
