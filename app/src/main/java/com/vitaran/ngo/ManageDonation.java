package com.vitaran.ngo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ManageDonation extends AppCompatActivity {

    private RequestQueue requestData;
    private TableLayout table;
    private TextView[] tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_donation);

        table = (TableLayout)findViewById(R.id.donationTable);
        populateTable("item");
    }

    private void populateTable(final String orderBy) {
        requestData = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.FILL_MANAGE_DONATION_TABLE_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        String serverResponse = parseJSONTableData(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("fill",orderBy);
                return params;
            }
        };
        requestData.add(stringRequest);
    }

    private String parseJSONTableData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has(Constants.RESULT)) {
                JSONArray result = jsonObject.getJSONArray(Constants.RESULT);
                createTable(result);
            } else {
                Toast.makeText(this, "Error getting server response", Toast.LENGTH_LONG).show();
            }
    } catch (JSONException e) {
        e.printStackTrace();
    }
        return null;
    }

    private void createTable(JSONArray result) {
        try {
            table.removeAllViews();
            TextView[][] textArray = new TextView[result.length()][6];
            TableRow[] tr_head = new TableRow[result.length()];
            TableRow table_heading;
            tv = new TextView[6];
            String table_head[] = {"DID","donorID","item","category","units","quantity"};
            int column[] = {R.id.column1,R.id.column2,R.id.column3,R.id.column4,R.id.column5,R.id.column6};

            table_heading = new TableRow(this);
            table_heading.setId(View.generateViewId());
            table_heading.setBackgroundColor(Color.GRAY);
            table_heading.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            for (int j=0; j<6; j++) {
                // Here create the TextView dynamically
                tv[j] = new TextView(this);
                tv[j].setId(column[j]);
                tv[j].setText(table_head[j]);
                tv[j].setTextColor(Color.WHITE);
                tv[j].setPadding(50, 10, 50, 10);
                table_heading.addView(tv[j]);
                tv[j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        sortTable(v.getId());
                        return false;
                    }
                });
            }
            // Add each table row to table layout
            table.addView(table_heading, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT));

            for(int i=0; i<result.length();i++){
                JSONObject data = result.getJSONObject(i);
                //Create the tablerows
                tr_head[i] = new TableRow(this);
                //tr_head[i].setId(View.generateViewId());
                tr_head[i].setId(i + 1);
                tr_head[i].setBackgroundColor(Color.LTGRAY);
                //tr_head[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                tr_head[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT));

                for (int j=0; j<6; j++) {
                    // Here create the TextView dynamically
                    textArray[i][j] = new TextView(this);
                    textArray[i][j].setId(10*i+j+100+1);
                    textArray[i][j].setText(data.getString(table_head[j]));
                    textArray[i][j].setTextColor(Color.WHITE);
                    textArray[i][j].setPadding(50, 10, 50, 10);
                    tr_head[i].addView(textArray[i][j]);
                }

                CheckBox box = new CheckBox(this);
                tr_head[i].addView(box);

                // Add each table row to table layout
                table.addView(tr_head[i], new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT));

            } // end of for loop
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void sortTable(int id) {
        String param="";
        switch (id) {
            case R.id.column1 :
                param = "DID";
                showMessage("column1 clicked!");
                populateTable(param);
                break;
            case R.id.column2 :
                param = "donorID";
                showMessage("column2 clicked!");
                populateTable(param);
                break;
            case R.id.column3 :
                param = "item";
                showMessage("column3 clicked!");
                populateTable(param);
                break;
            case R.id.column4 :
                param = "category";
                showMessage("column4 clicked!");
                populateTable(param);
                break;
            case R.id.column5 :
                showMessage("column5 clicked!");
                param = "units";
                populateTable(param);
                break;
            case R.id.column6 :
                showMessage("column6 clicked!");
                param = "quantity";
                populateTable(param);
                break;
        }
    }

    /**
     * Function to show toasts for user warnings and info
     * @param : msg, String value
     */
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}

