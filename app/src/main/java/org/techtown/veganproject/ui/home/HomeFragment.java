package org.techtown.veganproject.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.techtown.veganproject.MainActivity;
import org.techtown.veganproject.R;

public class HomeFragment extends Fragment {

    EditText et;
    String sfName = "myFile";
    private HomeViewModel homeViewModel;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        et = (EditText) root.findViewById(R.id.editText1);

        // 지난번 저장해놨던 사용자 입력값을 꺼내서 보여주기
        SharedPreferences sf = getActivity().getSharedPreferences(sfName, 0);
        String str = sf.getString("name", ""); // 키값으로 꺼냄
        et.setText(str); // EditText에 반영함

        final Spinner spinner6 = (Spinner) root.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.vegantype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner6.setAdapter(adapter);
        final String firstItem = String.valueOf(spinner6.getSelectedItem());



        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (firstItem.equals(String.valueOf(spinner6.getSelectedItem()))) {

                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SharedPreferences test = getActivity().getSharedPreferences("Preferences6", Context.MODE_PRIVATE);
        int spinnerValue = test.getInt("spinner6",-1);
        if(spinnerValue != -1)
            // set the value of the spinner
            spinner6.setSelection(spinnerValue);

        return root;
    } // end of onCreate

    @Override
    public void onPause() {
        super.onPause();
        Spinner spinner6 = (Spinner) root.findViewById(R.id.spinner);
        SharedPreferences.Editor prefEditor = getActivity().getSharedPreferences("Preferences6", 0).edit();
        prefEditor.putInt("spinner6", spinner6.getSelectedItemPosition());
        prefEditor.apply();
    }


    @Override
    public void onStop() {
        super.onStop();
        // Activity 가 종료되기 전에 저장한다
        // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
        SharedPreferences sf = getActivity().getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요
        String str = et.getText().toString(); // 사용자가 입력한 값
        editor.putString("name", str); // 입력
        editor.putString("xx", "xx"); // 입력
        editor.commit(); // 파일에 최종 반영함
    }



}