package com.bringg.worky.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bringg.worky.R;
import com.bringg.worky.data.db.SessionModel;
import com.bringg.worky.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;

public class SessionsListAdapter extends RecyclerView.Adapter<SessionsListAdapter.SessionViewHolder> {

    private List<SessionModel> sessionModels;

    private Context context;

    public SessionsListAdapter(Context context) {
        this.context = context;
        this.sessionModels = new ArrayList<>();
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SessionViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final SessionViewHolder holder, int position) {
        SessionModel sessionModel = sessionModels.get(position);

        holder.tvEntanceTime.setText(
                SessionUtils.parseDate(context,sessionModel.getEntranceTime())
        );

        holder.tvExitTime.setText(
                SessionUtils.parseDate(context,sessionModel.getExitTime())
        );

        holder.tvDruation.setText(
                SessionUtils.parseDuration(sessionModel.getDuration())
        );

        int backgroundColor = sessionModel.isException()
                ? ContextCompat.getColor(context, R.color.exception_color)
                : ContextCompat.getColor(context, android.R.color.transparent);

            holder.itemView.setBackgroundColor(backgroundColor);

    }

    @Override
    public int getItemCount() {
        return sessionModels.size();
    }

    public void addItems(List<SessionModel> sessionModels) {
        this.sessionModels = sessionModels;
        notifyItemRangeInserted(sessionModels.size(),  sessionModels.size());
    }

    static class SessionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEntanceTime;
        private TextView tvExitTime;
        private TextView tvDruation;

        SessionViewHolder(View view) {
            super(view);
            tvEntanceTime = view.findViewById(R.id.tv_entrance_time);
            tvExitTime = view.findViewById(R.id.tv_exit_time);
            tvDruation = view.findViewById(R.id.tv_duration);
        }
    }
}