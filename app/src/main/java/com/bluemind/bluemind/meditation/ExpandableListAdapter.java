package com.bluemind.bluemind.meditation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bluemind.bluemind.R;
import com.bluemind.bluemind.newsfeed.AppController;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MeditationPlayerList> meditationPlayerLists;
    private HashMap<List<MeditationPlayerList>, List<Integer>> player_child_items;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ExpandableListAdapter(Activity activity, List<MeditationPlayerList> meditationPlayerLists, HashMap<List<MeditationPlayerList>, List<Integer>> player_child_items){
        this.activity = activity;
        this.meditationPlayerLists = meditationPlayerLists;
        this.player_child_items = player_child_items;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        return this.player_child_items.get(this.meditationPlayerLists.get(childPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        final Bitmap imageBitMap = (Bitmap) getChild(groupPosition, childPosition);

        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.media_player_child_items, null);
        }
        ImageButton imageButtonList = (ImageButton) convertView.findViewById(R.id.media_player_Play_button);
        imageButtonList.setImageBitmap(imageBitMap);
        return  convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return this.player_child_items.get(this.meditationPlayerLists.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return this.meditationPlayerLists.get(groupPosition);
    }

    @Override
    public int getGroupCount(){
        return this.meditationPlayerLists.size();
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        String headerTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.media_player_list_group_header, null);
        }
        TextView audioName = (TextView) convertView.findViewById(R.id.media_player_audio_title);
        NetworkImageView coverImaage = (NetworkImageView) convertView.findViewById(R.id.coverImage);

        MeditationPlayerList meditationPlayerList = meditationPlayerLists.get(groupPosition);
        audioName.setText(meditationPlayerList.getName());
        coverImaage.setImageUrl(meditationPlayerList.getCoverImage(), imageLoader);

        return convertView;

    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }
}
