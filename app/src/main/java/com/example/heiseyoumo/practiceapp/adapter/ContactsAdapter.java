package com.example.heiseyoumo.practiceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.entity.Contacts;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Contacts> mList;
    private LayoutInflater inflater;
    private Contacts contacts;

    public ContactsAdapter(Context mContext,List<Contacts> mList) {
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
            convertView = inflater.inflate(R.layout.contacts_item,null);
            viewHolder.edit_name = (EditText)convertView.findViewById(R.id.edit_name);
            viewHolder.edit_number = (EditText)convertView.findViewById(R.id.edit_number);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        contacts = mList.get(position);
        viewHolder.edit_name.setText(contacts.getName());
        viewHolder.edit_number.setText(contacts.getNumber());
        return convertView;
    }

    class ViewHolder{
        private EditText edit_name;
        private EditText edit_number;
    }
}
