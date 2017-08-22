package com.example.kun.heatmapdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by effy on 2017/8/10.
 */

public class draw extends View {
    private Bitmap bitmap;
    private Paint paint;
    private Canvas canvas;
    private HeatMap heatMap;
    private HeatMapOverlay heatMapOverlay;
    public draw(Context context) {
        super(context);

        paint = new Paint(Paint.DITHER_FLAG);
        canvas = new Canvas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Position> data = generateHeatMapData();
        heatMap = new HeatMap.Builder().weightedData(data).radius(140).width(2160).height(1800).build();
        canvas.drawBitmap(heatMap.generateMap(), 0, 0, paint);




    }
    private List<Position> generateHeatMapData() {
        List<Position> data = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.exhibits);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = new String();

        try {
            while ((line = reader.readLine()) != null) {
                String pieces[] = line.split(",");
                int x = Integer.parseInt(pieces[1]);
                int y = Integer.parseInt(pieces[2]);
                int value = Integer.parseInt(pieces[3]);
                data.add(new Position(x, y , value));


            }
        } catch (IOException e) {
            Log.wtf("heatMap", "Error reading from file" + line, e);
            e.printStackTrace();
        }


        return data;
    }

}
