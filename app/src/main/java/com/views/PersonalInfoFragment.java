package com.views;

import static androidx.core.content.ContextCompat.getSystemService;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import com.viewmodels.UserViewModel;

public class PersonalInfoFragment extends Fragment {

    private View view;
    private EditText editHeight;
    private EditText editWeight;
    private Switch switchGender;
    private Button saveInfoButton;
    private UserViewModel userViewModel;

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
        userViewModel = UserViewModel.getInstance();
        /**
         * TODO 3: Should save data from 2 edit texts and switch and send it to database
         * TODO 3: Should set this data in other tabs like input meals screen
         * TODO 3: Should check user input for null and invalid
         */
        saveInfoButton.setOnClickListener(v -> {
            userViewModel.setUserHeight(editHeight.getText().toString());
            userViewModel.setUserWeight(editWeight.getText().toString());
            userViewModel.setUserGender(switchGender.isChecked());
            hideKeyboard();
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

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
