package indi.mat.work.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import indi.mat.work.android.R;
import indi.mat.work.android.model.bean.WareHouse;

public class BottomDrawer extends BottomSheetDialog {

    private List<WareHouse> mWarehouseList = new ArrayList<>();
    private GridView gridView;
    private ItemAdapter adapter;
    private OnChoiceWarehouseListener mListener;


    public BottomDrawer(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BottomDrawer(@NonNull Context context, int theme) {
        super(context, theme);
        init(context);

    }

    protected BottomDrawer(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);

    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.dialog_choice_warehouse_bottomsheet, null);
        setContentView(view);
        setCanceledOnTouchOutside(true);
        getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        gridView = view.findViewById(R.id.login_warehouse_grid);
        initEvent();
    }


    private void initEvent() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if(mListener!=null){
                mListener.onChoice(mWarehouseList.get(position));
            }
            dismiss();
        });
        findViewById(R.id.dialog_bottomsheet_iv_close).setOnClickListener(v -> dismiss());
    }


    public void setOnChoiceWarehouseListener(OnChoiceWarehouseListener listener) {
        mListener = listener;
    }

    public interface OnChoiceWarehouseListener {
        void onChoice(WareHouse warehouse);
    }

    public void show(List<WareHouse> warehouseList) {
        mWarehouseList.clear();
        if (warehouseList != null && warehouseList.size() > 0) {
            mWarehouseList.addAll(warehouseList);
        }
        if (adapter == null) {
            adapter = new ItemAdapter(mWarehouseList);
            gridView.setAdapter(adapter);
        } else {
            adapter.setWarehouseList(mWarehouseList);
            adapter.notifyDataSetChanged();
        }
        show();
    }

    public static class ItemAdapter extends BaseAdapter {

        private List<WareHouse> warehouseList;

        public ItemAdapter(List<WareHouse> warehouseList) {
            this.warehouseList = warehouseList;
        }

        public List<WareHouse> getWarehouseList() {
            return warehouseList;
        }

        public void setWarehouseList(List<WareHouse> warehouseList) {
            this.warehouseList = warehouseList;
        }

        @Override
        public int getCount() {
            return warehouseList.size();
        }

        @Override
        public Object getItem(int position) {
            return warehouseList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                // 第一次加载创建View，其余复用 View
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_choice_warehouse, null);
                holder = new ViewHolder();
         /*   holder.imageView = (ImageView) convertView
                    .findViewById(R.id.grid_img);*/
                holder.textView = convertView
                        .findViewById(R.id.warehouse_item_button);
                // 打标签
                convertView.setTag(holder);

            } else {
                // 从标签中获取数据
                holder = (ViewHolder) convertView.getTag();
            }
            // 根据key值设置不同数据内容
      /*  holder.imageView.setImageResource((Integer) warehouseList.get(position).get(
                "image"));*/
            holder.textView.setText((String) warehouseList.get(position).getName());

            return convertView;
        }

        class ViewHolder {
            TextView textView;
        }
    }
}
