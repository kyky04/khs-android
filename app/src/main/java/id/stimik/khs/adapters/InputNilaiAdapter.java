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
import id.stimik.khs.models.ItemStudy;


/**
 * Created by Comp on 2/11/2018.
 */

public class InputNilaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemStudy> listItem;


    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public InputNilaiAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama)
        TextView tvNama;
        @BindView(R.id.tv_nim)
        TextView tvNim;
        @BindView(R.id.tv_nilai)
        TextView tvNilai;
        @BindView(R.id.lay)
        LinearLayout lay;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_input_nilai, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            ItemStudy item = listItem.get(position);
            view.tvNama.setText(item.getMahasiswa());
            view.tvNim.setText(item.getNim());
            int nilai = item.getNilai();
            String nilaiAlhabet = "";

            if (nilai == 8) {
                nilaiAlhabet = "A / " + 8;
            }else if (nilai == 6) {
                nilaiAlhabet = "B / " + 6;
            }else if (nilai == 4) {
                nilaiAlhabet = "C / " + 4;
            }else if (nilai == 2) {
                nilaiAlhabet = "D / " + 2;
            }else if (nilai == 0) {
                nilaiAlhabet = "E / " + 0;
            }

            view.tvNilai.setText("Sks : "+item.getSks()+"\nNilai / Mutu : "+ nilaiAlhabet);
            view.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(ItemStudy item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<ItemStudy> listItem) {
        for (ItemStudy item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<ItemStudy> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public ItemStudy getItem(int pos) {
        return listItem.get(pos);
    }

    public String showHourMinute(String hourMinute) {
        String time = "";
        time = hourMinute.substring(0, 5);
        return time;
    }

    public void setFilter(List<ItemStudy> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<ItemStudy> getListItem() {
        return listItem;
    }
}
