package org.techtown.veganproject.ui.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigator;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.veganproject.R;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class bookmarkFragment extends AppCompatActivity  {

    int _id;
    String name;
    double lat,lon;
    private DbHelper DbHelper;
   /* @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bookmark);

    }*/

   /* public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_bookmark, container, false);
        return v;
    }*/

    // View root;



    //@Override
   /* public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root =  inflater.inflate(R.layout.fragment_bookmark, container, false);



        return root;
    }*/

    // private ArrayList<bookmark_data> favoritesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavoriteAdapter mAdapter;
    ArrayList<bookmark_data>bookmarkMapList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bookmark);

        //recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        prepareData();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FavoriteAdapter(bookmarkMapList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnFavoriteItemClickListener() {
            @Override
            public void onItemClick(FavoriteAdapter.MyViewHolder holder, View view, int position) {
                int item = mAdapter.getItemViewType(position);
                Log.d("삭제버튼 클릭","");


            }
        });


        //bookmarkMapList=DbHelper.getBookmarkDataByDate(name);


    }

    //데이터 준비(최종적으로는 동적으로 추가하거나 삭제할 수 있어야 한다. 이 데이터를 어디에 저장할지 정해야 한다.)
    private void prepareData() {

        if(DbHelper == null){
            DbHelper = new DbHelper(bookmarkFragment.this,"BookmarkMap",null,DbHelper.DB_VERSION);
        }

        bookmarkMapList =DbHelper.getBookmarkDataByDate();


    }



}