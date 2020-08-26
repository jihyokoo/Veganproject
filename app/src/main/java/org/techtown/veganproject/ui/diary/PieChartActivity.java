package org.techtown.veganproject.ui.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import org.techtown.veganproject.MainActivity;
import org.techtown.veganproject.R;
import java.util.ArrayList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;



public class PieChartActivity extends AppCompatActivity {
    int a = 1; int b = 1; int c = 1; int d = 1; int e = 1; int f = 1;

//   Button ba, bb, bc, bd, be, bf;
   //private Context context;

    Button ba = (Button) findViewById(R.id.radio1);
    Button bb = (Button) findViewById(R.id.radio2);
    Button bc = (Button) findViewById(R.id.radio3);
    Button bd = (Button) findViewById(R.id.radio4);
    Button be = (Button) findViewById(R.id.radio5);
    Button bf = (Button) findViewById(R.id.radio6);

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        final PieChart pieChart = findViewById(R.id.piechart);

//        LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
//        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.activity_pie_chart, null, false);


        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ArrayList NoOfEmp = new ArrayList();

                if(v.getId() == R.id.radio1) {
                    a++;
                    Log.d("a클릭", String.valueOf(a));
                    NoOfEmp.add(new Entry(a, 0));
                    NoOfEmp.add(new Entry(b, 1));
                    NoOfEmp.add(new Entry(c, 2));
                    NoOfEmp.add(new Entry(d, 3));
                    NoOfEmp.add(new Entry(e, 4));
                    NoOfEmp.add(new Entry(f, 5));
                } else if(v.getId() == R.id.radio2) {
                    b++;
                    NoOfEmp.add(new Entry(a, 0));
                    NoOfEmp.add(new Entry(b, 1));
                    NoOfEmp.add(new Entry(c, 2));
                    NoOfEmp.add(new Entry(d, 3));
                    NoOfEmp.add(new Entry(e, 4));
                    NoOfEmp.add(new Entry(f, 5));
                }else if(v.getId() == R.id.radio3) {
                    c++;
                    NoOfEmp.add(new Entry(a, 0));
                    NoOfEmp.add(new Entry(b, 1));
                    NoOfEmp.add(new Entry(c, 2));
                    NoOfEmp.add(new Entry(d, 3));
                    NoOfEmp.add(new Entry(e, 4));
                    NoOfEmp.add(new Entry(f, 5));
                }else if(v.getId() == R.id.radio4) {
                    d++;
                    NoOfEmp.add(new Entry(a, 0));
                    NoOfEmp.add(new Entry(b, 1));
                    NoOfEmp.add(new Entry(c, 2));
                    NoOfEmp.add(new Entry(d, 3));
                    NoOfEmp.add(new Entry(e, 4));
                    NoOfEmp.add(new Entry(f, 5));
                }else if(v.getId() == R.id.radio5) {
                    e++;
                    NoOfEmp.add(new Entry(a, 0));
                    NoOfEmp.add(new Entry(b, 1));
                    NoOfEmp.add(new Entry(c, 2));
                    NoOfEmp.add(new Entry(d, 3));
                    NoOfEmp.add(new Entry(e, 4));
                    NoOfEmp.add(new Entry(f, 5));
                }else if(v.getId() == R.id.radio6) {
                    f++;
                    NoOfEmp.add(new Entry(a, 0));
                    NoOfEmp.add(new Entry(b, 1));
                    NoOfEmp.add(new Entry(c, 2));
                    NoOfEmp.add(new Entry(d, 3));
                    NoOfEmp.add(new Entry(e, 4));
                    NoOfEmp.add(new Entry(f, 5));
                }

                PieDataSet dataSet = new PieDataSet(NoOfEmp, "비건 실천 통계");
                ArrayList year = new ArrayList();
                year.add("비건"); year.add("락토");
                year.add("락토오보"); year.add("페스코");
                year.add("세미"); year.add("논비건");


                PieData data = new PieData(year, dataSet); // MPAndroidChart v3.X 오류 발생
                pieChart.setData(data);
                dataSet.setColors(ColorTemplate.PASTEL_COLORS);
                pieChart.animateXY(1000, 1000);

            }
        };
//
//        ba = (Button) findViewById(R.id.radio1);
//        bb = (Button) findViewById(R.id.radio2);
//        bc = (Button) findViewById(R.id.radio3);
//        bd = (Button) findViewById(R.id.radio4);
//        be = (Button) findViewById(R.id.radio5);
//        bf = (Button) findViewById(R.id.radio6);

        ba.setOnClickListener(listener);
        bb.setOnClickListener(listener);
        bc.setOnClickListener(listener);
        bd.setOnClickListener(listener);
        be.setOnClickListener(listener);
        bf.setOnClickListener(listener);


    }

}
