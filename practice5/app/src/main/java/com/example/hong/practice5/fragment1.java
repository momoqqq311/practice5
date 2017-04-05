package com.example.hong.practice5;

import android.app.Fragment;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

import static android.R.attr.fragment;
import static java.sql.Types.NULL;

/**
 * Created by hong on 2017-03-30.
 */

public class fragment1 extends Fragment implements View.OnClickListener {
    Button new_order;
    Button modify;
    Button reset;
    Button b[] = new Button[4];
    EditText e1, e2;
    TextView t1, t2;
    TextView t3, t4, t5, t6, t7, t8;
    RadioGroup rg;
    RadioButton rb;
    RadioButton rb2;
    RadioButton rb3;
    String table = "table1";
    int table_num = 1;
    table_order t[] = new table_order[4];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment1, container, false);
        new_order = (Button) fragview.findViewById(R.id.new_order);
        new_order.setOnClickListener(this);
        modify = (Button) fragview.findViewById(R.id.modify_info);
        modify.setOnClickListener(this);
        reset = (Button) fragview.findViewById(R.id.reset);
        reset.setOnClickListener(this);
        b[0] = (Button) fragview.findViewById(R.id.button);
        b[0].setOnClickListener(this);
        b[1] = (Button) fragview.findViewById(R.id.button2);
        b[1].setOnClickListener(this);
        b[2] = (Button) fragview.findViewById(R.id.button3);
        b[2].setOnClickListener(this);
        b[3] = (Button) fragview.findViewById(R.id.button4);
        b[3].setOnClickListener(this);
        t3 = (TextView) fragview.findViewById(R.id.tablename);
        t4 = (TextView) fragview.findViewById(R.id.entrancetime);
        t5 = (TextView) fragview.findViewById(R.id.spagetti_count);
        t6 = (TextView) fragview.findViewById(R.id.pizza_count);
        t7 = (TextView) fragview.findViewById(R.id.membership);
        t8 = (TextView) fragview.findViewById(R.id.price);
        int k;
        for (k = 0; k < 4; k++) {
            t[k] = new table_order("", "", 0, 0, "", 0);
        }
        return fragview;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                table = "table1";
                table_num = 1;
                check();
                info(v);
                break;
            case R.id.button2:
                table = "table2";
                table_num = 2;
                check();
                info(v);
                break;
            case R.id.button3:
                table = "table3";
                table_num = 3;
                check();
                info(v);
                break;
            case R.id.button4:
                table = "table4";
                table_num = 4;
                check();
                info(v);
                break;
            case R.id.new_order:
                order(v);
                break;
            case R.id.reset:
                reset(v);
                break;
            case R.id.modify_info:
                modify(v);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    String GetCurrentTime() {
        String time = "";
        Calendar cal = Calendar.getInstance();
        time = String.format("%04d%02d%02d-%02d:%02d:%02d",
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return time;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void order(final View v) {
        View dia = View.inflate(v.getContext(), R.layout.ordertoast, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
        e1 = (EditText) dia.findViewById(R.id.t_spagetti);
        e2 = (EditText) dia.findViewById(R.id.t_pizza);
        t1 = (TextView) dia.findViewById(R.id.t_name);
        t2 = (TextView) dia.findViewById(R.id.t_time);
        rg = (RadioGroup) dia.findViewById(R.id.radioGroup);
        rb = (RadioButton) dia.findViewById(R.id.radioButton1);
        rb2 = (RadioButton) dia.findViewById(R.id.radioButton2);
        rb3 = (RadioButton) dia.findViewById(R.id.radioButton3);
        t1.setText(table);
        final String time = GetCurrentTime();
        t2.setText(time);
        dlg.setTitle("먹고 싶은 메뉴는?")
                .setView(dia)
                .setIcon(R.drawable.pizza)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String membership;
                                int spagetti_num;
                                int pizza_num;
                                if (e1.getText().toString().equals("") || e2.getText().toString().equals("")) {
                                    if (e1.getText().toString().equals("")) {
                                        spagetti_num = 0;
                                        pizza_num = Integer.parseInt(e2.getText().toString());
                                    } else {
                                        pizza_num = 0;
                                        spagetti_num = Integer.parseInt(e1.getText().toString());
                                    }
                                } else {
                                    spagetti_num = Integer.parseInt(e1.getText().toString());
                                    pizza_num = Integer.parseInt(e2.getText().toString());
                                }
                                double price = (10000 * spagetti_num) + (12000 * pizza_num);
                                int id = rg.getCheckedRadioButtonId();
                                if (id == R.id.radioButton1) {
                                    membership = rb.getText().toString();
                                } else if (id == R.id.radioButton2) {
                                    membership = rb2.getText().toString();
                                    price = price * 0.90;
                                } else {
                                    membership = rb3.getText().toString();
                                    price = price * 0.70;
                                }
                                t[table_num - 1].reset(table, time, spagetti_num, pizza_num, membership, (int) price);
                                b[table_num - 1].setText(table_num + "번 테이블");
                                Snackbar.make(v, "정보가 입력되었습니다.", Snackbar.LENGTH_SHORT)
                                        .setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                            }
                                        }).show();
                            }
                        })
                .setNegativeButton("취소", null)
                .show();

    }

    void info(View v) {
        if (t[table_num - 1].getTable_name().equals("")) {
            Toast.makeText(v.getContext(), "비어있는 테이블입니다", Toast.LENGTH_SHORT).show();
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
            t7.setText("");
            t8.setText("");
        } else {
            t3.setText(t[table_num - 1].getTable_name());
            t4.setText(t[table_num - 1].getTime());
            t5.setText("" + t[table_num - 1].getSpagetti_num());
            t6.setText("" + t[table_num - 1].getPizza_num());
            t7.setText(t[table_num - 1].getMembership());
            t8.setText("" + t[table_num - 1].getPrice());
        }

    }

    void reset(View v) {
        t[table_num - 1].reset("", "", 0, 0, "", 0);
        b[table_num - 1].setText(table_num + "번 테이블(비워있음)");
        Snackbar.make(v, "테이블이 비워졌습니다.", Snackbar.LENGTH_SHORT)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    void modify(final View v) {
        View dia = View.inflate(v.getContext(), R.layout.modifytoast, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
        e1 = (EditText) dia.findViewById(R.id.t_spagetti);
        e2 = (EditText) dia.findViewById(R.id.t_pizza);
        dlg.setTitle("수정해주세요")
                .setView(dia)
                .setIcon(R.drawable.pizza)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String membership;
                                int spagetti_num;
                                int pizza_num;
                                if (e1.getText().toString().equals("") || e2.getText().toString().equals("")) {
                                    if (e1.getText().toString().equals("")) {
                                        spagetti_num = 0;
                                        pizza_num = Integer.parseInt(e2.getText().toString());
                                    } else {
                                        pizza_num = 0;
                                        spagetti_num = Integer.parseInt(e1.getText().toString());
                                    }
                                } else {
                                    spagetti_num = Integer.parseInt(e1.getText().toString());
                                    pizza_num = Integer.parseInt(e2.getText().toString());
                                }
                                double price = (10000 * spagetti_num) + (12000 * pizza_num);
                                int id = rg.getCheckedRadioButtonId();
                                if (id == R.id.radioButton1) {
                                    membership = rb.getText().toString();
                                } else if (id == R.id.radioButton2) {
                                    membership = rb2.getText().toString();
                                    price = price * 0.90;
                                } else {
                                    membership = rb3.getText().toString();
                                    price = price * 0.70;
                                }
                                t[table_num - 1].setSpagetti_num(spagetti_num);
                                t[table_num - 1].setPizza_num(pizza_num);
                                t[table_num - 1].setMembership(membership);
                                t[table_num - 1].setPrice((int) price);
                                Snackbar.make(v, "정보가 수정되었습니다.", Snackbar.LENGTH_SHORT)
                                        .setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                            }
                                        }).show();
                            }
                        })
                .setNegativeButton("취소", null)
                .show();
    }

    void check() {
        if (t[table_num - 1].getTable_name().equals("")) {
            modify.setEnabled(false);
            reset.setEnabled(false);
        } else {
            modify.setEnabled(true);
            reset.setEnabled(true);
        }
    }

}
