package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akshaychavan.vaxicov.R;

import java.util.ArrayList;

import com.akshaychavan.vaxicov.pojo.CenterDistrict;
import com.akshaychavan.vaxicov.pojo.SessionDistrict;

/**
 * Created by Akshay Chavan on 09,May,2021
 * akshaychavan.kkwedu@gmail.com
 */
public class AvailabilityDetailsRowDistrictAdapter extends RecyclerView.Adapter<AvailabilityDetailsRowDistrictAdapter.DetailsViewHolder> {

    final String TAG = "AvailabilityDetailsRowAdapter";
    private ArrayList<CenterDistrict> mDetailsList;
    private Context mContext;

    public AvailabilityDetailsRowDistrictAdapter(ArrayList<CenterDistrict> detailsList, Context context) {
        mDetailsList = detailsList;
        this.mContext = context;
    }
    

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_row, parent, false);
        DetailsViewHolder holdingsViewHolder = new DetailsViewHolder(v, mContext);
        return holdingsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        CenterDistrict currentItem = mDetailsList.get(position);

        holder.tvCenterName.setText(currentItem.getName());
        holder.tvFeeType.setText(currentItem.getFeeType());

        ArrayList<SessionDistrict> sessionArrayList = (ArrayList<SessionDistrict>) currentItem.getSessions();

        // Passing data to Adapter
        holder.sessionsAdapter = new SubrowsDistrictAdapter(sessionArrayList, mContext);
        holder.sessionsRecylcer.setLayoutManager(holder.sessionsLayoutManager);
        holder.sessionsRecylcer.setAdapter(holder.sessionsAdapter);

    }

    @Override
    public int getItemCount() {
        return mDetailsList.size();
    }


    public static class DetailsViewHolder extends RecyclerView.ViewHolder {
        TextView tvCenterName, tvFeeType;
        // Availability Details Adapter
        RecyclerView sessionsRecylcer;
        RecyclerView.LayoutManager sessionsLayoutManager;
        RecyclerView.Adapter sessionsAdapter;
        /////////////////////////////////

        public DetailsViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);

            tvCenterName = itemView.findViewById(R.id.tv_center_name);
            tvFeeType = itemView.findViewById(R.id.tv_fee_type);

            // Setting up adapters
            sessionsRecylcer = itemView.findViewById(R.id.rv_subrows);
            sessionsRecylcer.setHasFixedSize(true);
            sessionsLayoutManager = new LinearLayoutManager(mContext);

        }
    }
}
