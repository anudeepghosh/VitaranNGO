package com.vitaran.ngo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class ManageDonation extends AppCompatActivity {

    private TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_donation);

        table = (TableLayout)findViewById(R.id.addItemTable);

        populateTable();
    }

    private void populateTable() {

    }
}

