package com.bond.daniel.appcalidadv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DANIEL on 13/10/2015.
 */
public class CelularAdapter extends ArrayAdapter<Celular> {

    private Context context;
    private List<Celular> data;

    public CelularAdapter(Context _context, int resource, List<Celular> objects) {
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
        CheckBox cbxCelular = (CheckBox)v.findViewById(R.id.cbx_celular);
        ImageView txvImg = (ImageView)v.findViewById(R.id.imgImagen);
        TextView txvDesc = (TextView)v.findViewById(R.id.txvDesc);

        Celular entry = data.get(position);

        txvImg.setImageResource(entry.getImagen());
        txvDesc.setText(entry.getDesc());

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

