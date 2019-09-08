package com.bluemind.bluemind.expert_Consultation;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.newsfeed.AppController;
import com.bluemind.bluemind.newsfeed.FeedImageView;
import com.bluemind.bluemind.newsfeed.FeedItem;

import java.util.List;

public class ExpertsListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<ExpertsList> expertsLists;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public  ExpertsListAdapter(Activity activity, List<ExpertsList> expertsLists){
        this.activity = activity;
        this.expertsLists = expertsLists;
    }

    @Override
    public int getCount(){
        return expertsLists.size();
    }

    @Override
    public Object getItem(int location){
        return expertsLists.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.expert_list, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView expertise = (TextView) convertView
                .findViewById(R.id.expertise);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);

        ExpertsList item = expertsLists.get(position);

        name.setText(item.getName());
        expertise.setText(item.getExpertise());

        // user profile pic
        profilePic.setImageUrl(item.getProfilePicture(), imageLoader);

        return convertView;
    }
}
