package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heiseyoumo.practiceapp.R;

import java.util.List;

import com.example.heiseyoumo.practiceapp.entity.Item;

public class FruitAdapter extends BaseAdapter{

    private Context mContext;
    private List<Item> mList;
    private LayoutInflater inflater;
    private Item item;

    public FruitAdapter(Context mContext,List<Item> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fruit_item,null);
            viewHolder.fruit_pic = (ImageView)convertView.findViewById(R.id.fruit_pic);
            viewHolder.fruit_name = (TextView)convertView.findViewById(R.id.fruit_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        item = mList.get(position);
        viewHolder.fruit_name.setText(item.getPicName());
        viewHolder.fruit_pic.setImageResource(item.getPicId());
        return convertView;
    }

    class ViewHolder{
        private TextView fruit_name;
        private ImageView fruit_pic;
    }
}
