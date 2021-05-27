package com.example.lesson6.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson6.ItemAdapter;
import com.example.lesson6.R;
import com.example.lesson6.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<String> innerList() {
        List<String> res = new ArrayList<>(5);
        res.add("дрель");
        res.add("шуроповерт");
        res.add("ножевка");
        res.add("гвоздодер");
        res.add("лопата");
        return res;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView rView = root.findViewById(R.id.recyclingView);
        rView.setHasFixedSize(true);
        LinearLayoutManager lManager = new LinearLayoutManager(getContext());
        rView.setLayoutManager(lManager);
        rView.setAdapter(new ItemAdapter(innerList()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}