package com.bottomsheet.custombottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button persistentbtn,modalbtn;
    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persistentbtn = (Button) findViewById(R.id.persistentsheet);
        modalbtn = (Button) findViewById(R.id.modalsheet);

        persistentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // persistent bottom sheet
                persistent();
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

       modalbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // modal bottom sheet
               View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
               dialog = new BottomSheetDialog(MainActivity.this);
               dialog.setContentView(view);
               dialog.show();
           }
       });
    }

    private void persistent(){
        View bottomSheet = findViewById(R.id.design_bottom_sheet);
        final ImageView imgicon = (ImageView) bottomSheet.findViewById(R.id.imgbtn);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        imgicon.setImageResource(R.drawable.ic_expand_more);
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        imgicon.setImageResource(R.drawable.ic_expand);
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
            }
        });

    }

}
