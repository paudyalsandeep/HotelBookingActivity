package com.example.hotelbookingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner spnLocation,roomType;
    TextView tvCheckIn,tvCheckOut,tvDays,tvroomno,tvsuite,tvtotal,tvVat;
    EditText etAdult,etChild,etRoom;
    Button btnBook;
    int yearin,monthin,dayin,yearout,monthout,dayout;
    int no_of_room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding
        spnLocation=findViewById(R.id.spnLocation);

        roomType=findViewById(R.id.spnRoomType);
        tvCheckIn=findViewById(R.id.tvCheckIn);
        tvCheckOut=findViewById(R.id.tvCheckOut);
        etAdult=findViewById(R.id.etadult);
        etChild=findViewById(R.id.etchild);
        etRoom=findViewById(R.id.etroom);
        btnBook=findViewById(R.id.btnBook);
        tvDays=findViewById(R.id.tvDays);
        tvroomno=findViewById(R.id.tvroomno);
        tvsuite=findViewById(R.id.totalsuite);
        tvtotal=findViewById(R.id.tvtotal);
        tvVat=findViewById(R.id.tvVat);


        //spinner for city starts

        String countries[]={"Kathmandu","Bhaktapur","Lalitpur","Pokhara","Chitwan"};

        ArrayAdapter adapter=new ArrayAdapter<> (
                this,android.R.layout.simple_list_item_1,countries
        );
        spnLocation.setAdapter(adapter);

        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this,spnLocation.getSelectedItem().toString()
                        ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner for city ends

        //spinner for room type starts

        String rooms[]={"Deluxe\n Rs.2000","AC\n Rs.3000","Platinum\n Rs.4000"};
        ArrayAdapter roomAdapter=new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,rooms
        );
        roomType.setAdapter(roomAdapter);

        //spinner for room type ends

        //datepicker section starts

        tvCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendarIn();
            }
        });

        tvCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendarOut();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(yearin, monthin, dayin);
                cal2.set(yearout, monthout, dayout);
                long millis1 = cal1.getTimeInMillis();
                long millis2 = cal2.getTimeInMillis();
                long diff = millis2 - millis1;
                long diffDays = (diff / (86400000));
                tvDays.setText("No of Days: "+diffDays);

                no_of_room = Integer.parseInt(etRoom.getText().toString());
                double price;
                double Total_Price;
                double Grand_Total;

                String suite = roomType.getSelectedItem().toString();

                if (suite == "Deluxe\n Rs.2000")
                {
                    price = 2000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;

                    tvDays.setText("Total Days:"+diffDays);
                    tvroomno.setText(("Number of Room(s):"+no_of_room));
                    tvsuite.setText("Room Price Per Night:"+"2000");
                    tvtotal.setText("Total:"+Total_Price);
                    tvVat.setText("Grand Total:"+Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total,
                            Toast.LENGTH_SHORT).show();
                }

                else if (suite == "AC\n Rs.3000")
                {
                    price = 3000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvDays.setText("Total Days:"+diffDays);
                    tvroomno.setText(("Number of Room(s):"+no_of_room));
                    tvsuite.setText("Room Price Per Night:"+"3000");
                    tvtotal.setText("Total:"+Total_Price);
                    tvVat.setText("Grand Total:"+Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();
                }

                else if (suite == "Platinum\n Rs.4000")
                {
                    price = 4000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvDays.setText("Total Days:"+diffDays);
                    tvroomno.setText(("Number of Room(s):"+no_of_room));
                    tvsuite.setText("Room Price Per Night:"+"4000");
                    tvtotal.setText("Total:"+Total_Price);
                    tvVat.setText("Grand Total:"+Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void loadCalendarIn()
    {
        //use the current date as the default date

        final Calendar c=Calendar.getInstance();
        yearin=c.get(Calendar.YEAR);
        monthin=c.get(Calendar.MONTH);
        dayin=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date =  year + "/" + month + "/" + dayOfMonth;
                monthin = month;
                dayin = dayOfMonth;
                yearin = year;
                tvCheckIn.setText(date);
            }
        },yearin,monthin,dayin);
        datePickerDialog.show();
    }

    private void loadCalendarOut()
    {
        //use the current date as the default date

        final Calendar c=Calendar.getInstance();
        yearout=c.get(Calendar.YEAR);
        monthout=c.get(Calendar.MONTH);
        dayout=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date =  year + "/" + month + "/" + dayOfMonth;
                monthout = month;
                dayout = dayOfMonth;
                yearout = year;
                tvCheckOut.setText(date);
            }
        },yearout,monthout,dayout);
        datePickerDialog.show();
    }



}
