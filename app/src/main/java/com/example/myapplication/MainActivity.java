package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView selectedItemsTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        selectedItemsTextView = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        final String[] items = {"美味蟹堡", "義式香腸堡", "蔬菜水果堡", "香蕉潛艇堡", "香烤雞肉堡"};
        final boolean[] checkedItems = {false, false, false, false, false}; // 初始狀態都為未選取

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("選擇選項")
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // 在這裡處理選擇的選項狀態
                        checkedItems[which] = isChecked;
                    }
                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 在這裡處理點擊確定按鈕後的邏輯
                        List<String> selectedItems = new ArrayList<>();
                        for (int i = 0; i < items.length; i++) {
                            if (checkedItems[i]) {
                                selectedItems.add(items[i]);
                            }
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String selectedItem : selectedItems) {
                            stringBuilder.append(selectedItem).append("\n");
                        }
                        String selectedItemsText = "你點的餐點有\n" + stringBuilder.toString();

                        // 更新TextView來顯示選擇的複選項目
                        selectedItemsTextView.setText(selectedItemsText);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 在這裡處理點擊取消按鈕後的邏輯
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}



