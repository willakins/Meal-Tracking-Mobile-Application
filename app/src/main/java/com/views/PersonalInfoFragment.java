package com.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.viewmodels.LoginViewModel;

public class PersonalInfoFragment extends Fragment {

    private View view;
    private EditText editHeight;
    private EditText editWeight;
    private Switch switchGender;
    private Button saveInfoButton;
    private LoginViewModel loginViewModel;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        editHeight = view.findViewById(R.id.editTextHeight);
        editWeight = view.findViewById(R.id.editTextWeight);
        switchGender = view.findViewById(R.id.switchGender);
        saveInfoButton = view.findViewById(R.id.buttonSaveInfo);
        /**
         * TODO: Should save data from 2 edit texts and switch and send it to database
         * TODO: Should set this data in other tabs like input meals screen
         * TODO: Should check user input for null and invalid
         */
        saveInfoButton.setOnClickListener(v -> {
            //...
            //textHeight.setText(newHeight);
        });
        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PersonalInfoFragment.
     */
    public static PersonalInfoFragment newInstance() {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        return fragment;
    }
}
