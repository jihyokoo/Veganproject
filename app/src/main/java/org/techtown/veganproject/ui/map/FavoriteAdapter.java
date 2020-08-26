package org.techtown.veganproject.ui.map;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.veganproject.MainActivity;
import org.techtown.veganproject.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private ArrayList<bookmark_data>mDataset;
    Context context;
    DbHelper Dbhelper;





    // DbHelper DbHelper;
    OnFavoriteItemClickListener listener;
    public void setOnItemClickListener(OnFavoriteItemClickListener listener){
        this.listener=listener;
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView delete_icon;



        public MyViewHolder(@NonNull  View itemView, final OnFavoriteItemClickListener listener) {
            super(itemView);
            name =(TextView) itemView.findViewById(R.id.name);
            delete_icon = (ImageView) itemView.findViewById(R.id.iv_delete);
            itemView.setClickable(true);



          /* delete_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("삭제버튼 클릭 완료","");
                    int position = getAdapterPosition();
                    if (listener!=null){
                        listener.onItemClick(MyViewHolder.this,view,position);
                    }


                 //   int position = getAdapterPosition();
                  //  boolean isDeleted = DbHelper.delete_column(position);

                   // DbHelper.delete_column(getAdapterPosition());
                 /*  if (isDeleted) {
                        mDataset.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mDataset.size());
                        notifyDataSetChanged();
                    }*/

               /* }
            });*/


        }
    }
    public FavoriteAdapter(ArrayList<bookmark_data> myData){
        this.mDataset = myData; //bookmark_data

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_raw, parent, false);
        Dbhelper = new DbHelper(parent.getContext(), "BookmarkMap", null, DbHelper.DB_VERSION);

        return new MyViewHolder(itemview,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.delete_icon.setTag(position);




        //클릭이벤트
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                String marker_name = mDataset.get(position).getName();
                Double marker_lat = mDataset.get(position).getLat();
                Double marker_lon = mDataset.get(position).getLon();

                Log.d("마커 이름",marker_name);
                Log.d("마커 위도", String.valueOf(marker_lat));
                Log.d("마커 경도", String.valueOf(marker_lon));


                Intent intent = new Intent();
                intent.putExtra("marker_name",marker_name);
                intent.putExtra("marker_lat",marker_lat);
                intent.putExtra("marker_lon",marker_lon);

                activity.setResult(RESULT_OK,intent);


                activity.finish();


            }
        });


        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (int) v.getTag();

                Log.i("삭제버튼 클릭 완료", String.valueOf(position));

                int Id = mDataset.get(position).get_ID(); //삭제를 위해 추가
                Dbhelper.delete_column(Id);

                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mDataset.size());
                notifyDataSetChanged();


            }
        });
    }




    @Override
    public int getItemCount() {
        // return mDataset.size();
        return (mDataset == null) ? 0 : mDataset.size();

    }
}