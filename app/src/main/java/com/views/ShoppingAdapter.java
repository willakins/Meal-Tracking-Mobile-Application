package com.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.model.Ingredient;
import com.model.ShoppingItem;
import com.model.User;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter
        .ShoppingViewHolder> {
    private ArrayList<ShoppingItem> shoppingList;
    private ArrayList<ShoppingItem> checkedItems = new ArrayList<>();
    private Context context;
    private UserViewModel userViewModel;
    private Button addButton;
    private Button subtractButton;
    private TextView shoppingNameTxt;
    private ShoppingFragment shoppingFragment;
    private IngredientAdapter.OnIngredientClickListener listener;
    private User user;

    public ShoppingAdapter(Context context, ShoppingFragment shopFragment) {
        this.context = context;
        this.shoppingFragment = shopFragment;
        this.userViewModel = UserViewModel.getInstance();
        this.user = userViewModel.getUser();
        this.shoppingList = user.getShoppingList();
    }

    @NonNull
    @Override
    public ShoppingAdapter.ShoppingViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_item, parent, false);
        return new ShoppingAdapter.ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingAdapter.ShoppingViewHolder holder, int position) {
        ShoppingItem item = shoppingList.get(position);
        holder.shoppingNameTxt.setText("  " + item.getName() + " : "
                + item.getQuantity() + " ");

        holder.addButton.setOnClickListener(v -> {
            DatabaseReference mDatabase = userViewModel.getDatabase();
            int shoppingIndex = user.findShoppingItem(item);
            if (shoppingIndex != -1) {
                String newQuantity = Integer.toString(Integer.parseInt(user.getShoppingList()
                        .get(shoppingIndex).getQuantity()) + 1);
                user.getShoppingList().get(shoppingIndex).setQuantity(newQuantity);
                mDatabase.child("shoppingList").child(user.getUserId()).child("Items")
                        .setValue(user.getShoppingList());
                holder.shoppingNameTxt.setText("  " + item.getName() + " : "
                        + item.getQuantity() + " ");
                shoppingFragment.generateNewAdapter();
            }


        });

        holder.subtractButton.setOnClickListener(v -> {
            DatabaseReference mDatabase = userViewModel.getDatabase();
            int shoppingIndex = user.findShoppingItem(item);
            if (shoppingIndex != -1) {
                String newQuantity = Integer.toString(Integer.parseInt(user.getShoppingList()
                        .get(shoppingIndex).getQuantity()) - 1);
                user.getShoppingList().get(shoppingIndex).setQuantity(newQuantity);
                mDatabase.child("shoppingList").child(user.getUserId()).child("Items")
                        .setValue(user.getShoppingList());
                holder.shoppingNameTxt.setText("  " + item.getName() + " : "
                        + item.getQuantity() + " ");
                shoppingFragment.generateNewAdapter();
                if (Integer.parseInt(newQuantity) == 0) {
                    userViewModel.removeShoppingItem(item);
                }
            }
        });

        holder.buyButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    checkedItems.add(item);
                } else {
                    int index = findChecked(item);
                    checkedItems.remove(index);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {
        private TextView shoppingNameTxt;
        private Button addButton;
        private Button subtractButton;
        private CheckBox buyButton;

        public ShoppingViewHolder(final View view) {
            super(view);
            shoppingNameTxt = view.findViewById(R.id.ShoppingName);
            addButton = view.findViewById(R.id.addButton);
            subtractButton = view.findViewById(R.id.removeButton);
            buyButton = view.findViewById(R.id.buyButton);
        }
    }

    private int findChecked(ShoppingItem target) {
        for (int i = 0; i < checkedItems.size(); i++) {
            if (checkedItems.get(i).getName().toUpperCase()
                    .equals(target.getName().toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<ShoppingItem> getCheckedItems() {
        return this.checkedItems;
    }
}
