package id.stimik.khs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.stimik.khs.R;
import id.stimik.khs.models.ItemJurusan;


/**
 * Created by Comp on 2/11/2018.
 */

public class JurusanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemJurusan> listItem;


    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public JurusanAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama)
        TextView tvNama;
        @BindView(R.id.click)
        LinearLayout click;

        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jurusan, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            ItemJurusan item = listItem.get(position);
            view.tvNama.setText(item.getNama());
            view.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });

            view.click.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }

            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(ItemJurusan item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<ItemJurusan> listItem) {
        for (ItemJurusan item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<ItemJurusan> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public ItemJurusan getItem(int pos) {
        return listItem.get(pos);
    }

    public String showHourMinute(String hourMinute) {
        String time = "";
        time = hourMinute.substring(0, 5);
        return time;
    }

    public void setFilter(List<ItemJurusan> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<ItemJurusan> getListItem() {
        return listItem;
    }
}
