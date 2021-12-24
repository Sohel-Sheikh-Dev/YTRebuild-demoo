package com.example.ytrebuild.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytrebuild.R;
import com.example.ytrebuild.Response.YTSearchResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    Context context;
    List<YTSearchResponse> ytResList = new ArrayList<>();

    public SearchListAdapter(Context context, List<YTSearchResponse> ytResList) {
        this.context = context;
        this.ytResList = ytResList;
    }

    @NonNull
    @Override
    public SearchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.video_id_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchListAdapter.ViewHolder holder, int position) {
        holder.vidId.setText("YT Channel: "+ ytResList.get(position).getSnippet().getChannelTitle());

    }

    @Override
    public int getItemCount() {
        return ytResList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vidId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vidId = itemView.findViewById(R.id.videoIdText);
        }
    }
}
