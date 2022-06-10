package com.justmax.virtualstyler.ui.nav.mannequin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.justmax.virtualstyler.R;

import java.util.ArrayList;

public class NavMannequin extends Fragment {
    private boolean clothed = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nav_mannequin, container, false);

        ImageView imgBody = root.findViewById(R.id.nav_mannequin_graphics_body);
        Button btnClothes = root.findViewById(R.id.nav_mannequin_btnClothes);
        Button btnRemove = root.findViewById(R.id.nav_mannequin_btnRemove);
        ListView listClothes = root.findViewById(R.id.nav_mannequin_list);

        ArrayList<String> arrClothes = new ArrayList<>();
        arrClothes.add("Косуха");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, arrClothes);
        listClothes.setAdapter(adapter);

        btnClothes.setOnClickListener(e -> {
            if (listClothes.getCount() > 0) {
                String s = (String) listClothes.getItemAtPosition(0);
                imgBody.setImageResource(R.drawable.ic_test_jacket);

                if (s.contentEquals("Косуха")) {
                    if (!clothed) {
                        imgBody.setVisibility(View.VISIBLE);
                        clothed = true;
                    } else {
                        imgBody.setVisibility(View.INVISIBLE);
                        clothed = false;
                    }
                }
            }
        });

        btnRemove.setOnClickListener(e -> {
            arrClothes.clear();
            ArrayAdapter<String> adapterNew = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, arrClothes);
            listClothes.setAdapter(adapterNew);
            imgBody.setVisibility(View.INVISIBLE);

//            listClothes.invalidateViews();
            clothed = false;
        });

        return root;
    }
}
