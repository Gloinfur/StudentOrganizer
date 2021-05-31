package com.jansmoneymachine.timetablestudents.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jansmoneymachine.timetablestudents.R;

public class MainActivity_CustomAdapterListView extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageView imgView_cardImage;
    private TextView txtView_cardName;
    private TextView txtView_cardDescription;
    private CardItemDatabase cardItemDatabases;

    public MainActivity_CustomAdapterListView(Context context, CardItemDatabase cardItemDatabases) {
        this.context = context;
        this.cardItemDatabases = cardItemDatabases;
    }


    @Override
    public int getCount() {
        return cardItemDatabases.getCardItems().length;
    }


    @Override
    public Object getItem(int position) {
        return cardItemDatabases.getCardItems()[position];
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardItem[] cardItems = cardItemDatabases.getCardItems();
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_main_item, null);
        }

        txtView_cardName = (TextView) convertView.findViewById(R.id.txtView_CardName);
        txtView_cardDescription = (TextView) convertView.findViewById(R.id.txtView_CardDescription);
        imgView_cardImage = (ImageView) convertView.findViewById(R.id.imgView_CardImage);

        if (cardItems[position] != null) {
            txtView_cardName.setText(cardItems[position].getName());
            txtView_cardDescription.setText(cardItems[position].getDescription());
            imgView_cardImage.setImageResource(cardItems[position].getImagePath());
        }
        return convertView;
    }
}
