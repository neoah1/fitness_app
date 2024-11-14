package com.example.yogademo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class WeightGraphView extends View {

    private List<Float> weights; // Lijst van gewichten
    private float minWeight; // Minimum gewicht
    private float maxWeight; // Maximum gewicht

    private Paint linePaint; // Verf voor lijnen
    private Paint textPaint; // Verf voor tekst

    public WeightGraphView(Context context) {
        super(context);
        init(); // Initialisatie aanroepen
    }

    public WeightGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(); // Initialisatie aanroepen
    }

    public WeightGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(); // Initialisatie aanroepen
    }

    private void init() {
        linePaint = new Paint(); // Maak een nieuwe verf voor lijnen
        linePaint.setColor(0xFF007ACC); // Stel kleur in
        linePaint.setStrokeWidth(5f); // Stel lijndikte in

        textPaint = new Paint(); // Maak een nieuwe verf voor tekst
        textPaint.setColor(0xFF000000); // Stel kleur in
        textPaint.setTextSize(30f); // Stel tekstgrootte in
    }

    public void setWeightData(List<Float> weights, float minWeight, float maxWeight) {
        this.weights = weights; // Stel gewichten in
        this.minWeight = minWeight; // Stel minimum gewicht in
        this.maxWeight = maxWeight; // Stel maximum gewicht in
        invalidate(); // Vraag om opnieuw te tekenen
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGraph(canvas); // Teken de grafiek
    }

    private void drawGraph(Canvas canvas) {
        if (weights != null && !weights.isEmpty()) { // Controleer of gewichten bestaan
            float width = getWidth(); // Verkrijg breedte van de weergave
            float height = getHeight(); // Verkrijg hoogte van de weergave

            // Bereken de schaal op basis van minimum en maximum gewicht
            float scale = height / (maxWeight - minWeight);

            // Teken lijnen tussen elk punt
            for (int i = 1; i < weights.size(); i++) {
                float startX = (width / (weights.size() - 1)) * (weights.size() - i);
                float startY = height - (weights.get(weights.size() - i - 1) - minWeight) * scale;
                float stopX = (width / (weights.size() - 1)) * (weights.size() - i + 1);
                float stopY = height - (weights.get(weights.size() - i) - minWeight) * scale;

                canvas.drawLine(startX, startY, stopX, stopY, linePaint); // Teken lijn
            }

            // Teken gewicht labels
            for (int i = 0; i < weights.size(); i++) {
                float x = (width / (weights.size() - 1)) * (weights.size() - i - 1);
                float y = height - (weights.get(weights.size() - i - 1) - minWeight) * scale;

                canvas.drawText(String.valueOf(weights.get(weights.size() - i - 1)), x, y - 10, textPaint); // Teken gewicht
            }
        }
    }
}
