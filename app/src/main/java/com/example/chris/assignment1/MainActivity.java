package com.example.chris.assignment1;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity{

    SeekBar sizeSeekBar;
    drawCircles draw;
    TextView sizeText;
    Button randomColours;
    Button undo;
    Button redo;
    int undoClickCount = 0;
    View drawPanel;
    ArrayList<PointF> circleLocations = new ArrayList();
    ArrayList <PointF> redoList = new ArrayList();


    static Boolean randomColoursCheck = false;

    public Boolean getRandomColoursCheck() {
        return randomColoursCheck;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draw = findViewById(R.id.drawPanel);
        sizeSeekBar=findViewById(R.id.seekBar);
        sizeText = findViewById(R.id.txtSize);
        randomColours=findViewById(R.id.btnRandomColours);
        drawPanel = findViewById(R.id.drawPanel);
        redo = findViewById(R.id.btnRedo);
        undo = findViewById(R.id.btnUndo);
        draw.setRadius(20);
        sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int currentRadius = 20;

                draw.setRadius(currentRadius);


                if(progress == 0)
                {
                    draw.setRadius(20);
                    sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));
                }


                else if(progress >= 1 && progress <= 20)
                {
                    draw.setRadius(currentRadius + progress);
                    sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));

                }
                else if(progress >= 21 && progress <= 40)
                {
                    draw.setRadius(currentRadius + progress);
                    sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));
                }
                else if(progress >= 41 && progress <= 60)
                {
                    draw.setRadius(currentRadius + progress);
                    sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));
                }
                else if(progress >= 61 && progress <= 80)
                {
                    draw.setRadius(currentRadius + progress);
                    sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));
                }
                else if(progress >=81 && progress <= 100)
                {
                    draw.setRadius(currentRadius + progress);
                    sizeText.setText(String.format(Locale.getDefault(),"%s", draw.getRadius()));
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        randomColours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                randomColoursCheck = true;
            }
        });


        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                undoClickCount +=1;

                if(undoClickCount < 12)
                {
                    try
                    {
                        circleLocations = draw.getLocations();

                        int undoIndex = circleLocations.size()-1;

                        if(undoIndex < 0)
                        {
                            undoIndex = 0;
                        }


                        if (!circleLocations.isEmpty())
                        {
                            PointF undoPointF = circleLocations.get(undoIndex);
                            redoList.add(undoPointF);
                            circleLocations.remove(undoIndex);
                            draw.invalidate();
                        }


                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        e.printStackTrace();
                    }
                }




            }
        });

        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    circleLocations = draw.getLocations();
                    int redoIndex = redoList.size()-1;
                    System.out.println(redoList.size());

                    if(!redoList.isEmpty())
                    {
                        PointF redoPointF = redoList.get(redoIndex);
                        circleLocations.add(redoPointF);
                        redoList.remove(redoPointF);
                        draw.invalidate();
                    }

                }



        });

    }
}


