package com.bond.daniel.s7listadoble;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DANIEL on 13/10/2015.
 */
public class UserAdapter extends ArrayAdapter<UserEntity> {

    private Context context;
    private List<UserEntity> data;

    public UserAdapter(Context _context, int resource, List<UserEntity> objects) {
        super(_context, resource, objects);

        context = _context;
        data = objects;

    }

    @Override
    public int getCount () {
        return data.size();
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.item_layout, null);
        }
        TextView txtName = (TextView)v.findViewById(R.id.txtTitle);
        TextView txtMail = (TextView)v.findViewById(R.id.txtEmail);

        UserEntity entry = data.get(position);

        txtName.setText(entry.getName());
        txtMail.setText(entry.getEmail());

        return v;
    }

    /**public UserAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public UserAdapter(Context context, int resource, UserEntity[] objects) {
        super(context, resource, objects);
    }

    public UserAdapter(Context context, int resource, int textViewResourceId, UserEntity[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public UserAdapter(Context context, int resource, List<UserEntity> objects) {
        super(context, resource, objects);
    }

    public UserAdapter(Context context, int resource, int textViewResourceId, List<UserEntity> objects) {
        super(context, resource, textViewResourceId, objects);*/
    }

