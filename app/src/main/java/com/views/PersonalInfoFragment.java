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
    InputMealActivity currentContext;

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

        saveInfoButton.setOnClickListener(v -> {
            String height = editHeight.getText().toString();
            String weight = editWeight.getText().toString();
            boolean gender = switchGender.isChecked();
            userViewModel.updateUserData(this.currentContext, height, weight, gender);
            editHeight.setText("Enter Your Height (inches)");
            editWeight.setText("Enter Your Weight (lbs)");
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

    /**
     * Helper method that allows this view to output toast messages to the screen
     *
     * @param context the view page that is currently being displayed (InputMealActivity)
     */
    public void setContext(InputMealActivity context) {
        this.currentContext = context;
    }
}
