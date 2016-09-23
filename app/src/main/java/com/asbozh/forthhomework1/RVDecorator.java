package com.asbozh.forthhomework1;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RVDecorator extends RecyclerView.ItemDecoration {

    private Paint paintGreen, paintWriting, paintCircle, paintDownload;
    private int leftOffset, bottomOffset, topHeight, rightWidth;
    private Context mContext;


    public RVDecorator(Context c){
        mContext = c;
        leftOffset = 20;
        bottomOffset = 0;
        topHeight = 50;
        rightWidth = 205;
        paintGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintGreen.setColor(mContext.getResources().getColor(R.color.spotify_green));
        paintGreen.setStyle(Paint.Style.FILL);
        paintGreen.setStrokeWidth(3);

        paintDownload = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDownload.setColor(mContext.getResources().getColor(R.color.spotify_green));
        paintDownload.setStyle(Paint.Style.FILL);
        paintDownload.setStrokeWidth(0);

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setColor(mContext.getResources().getColor(R.color.spotify_green));
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(3);

        paintWriting = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintWriting.setColor(mContext.getResources().getColor(R.color.dark));
        paintWriting.setTextSize(36);
        paintWriting.setStyle(Paint.Style.FILL);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for(int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (i % 2 == 0) {
                c.drawRoundRect(layoutManager.getDecoratedLeft(child) + leftOffset,
                        layoutManager.getDecoratedBottom(child) - topHeight,
                        layoutManager.getDecoratedLeft(child) + rightWidth,
                        layoutManager.getDecoratedBottom(child) - bottomOffset,
                        20,
                        20,
                        paintGreen);
            } else {
                c.drawCircle(layoutManager.getDecoratedLeft(child) + leftOffset + 30,
                        layoutManager.getDecoratedBottom(child) - bottomOffset - 10, 20, paintCircle);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for(int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (i % 2 == 0) {
                c.drawText(mContext.getString(R.string.explicit), layoutManager.getDecoratedLeft(child) + leftOffset + 20,
                        layoutManager.getDecoratedBottom(child) - bottomOffset - 10, paintWriting);
            } else {
                Point point1_draw = new Point();
                point1_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 35, layoutManager.getDecoratedBottom(child) - bottomOffset - 20);
                Point point2_draw = new Point();
                point2_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 25, layoutManager.getDecoratedBottom(child) - bottomOffset - 20);
                Point point3_draw = new Point();
                point3_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 25, layoutManager.getDecoratedBottom(child) - bottomOffset - 5);
                Point point4_draw = new Point();
                point4_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 18, layoutManager.getDecoratedBottom(child) - bottomOffset - 5);
                Point point5_draw = new Point();
                point5_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 30, layoutManager.getDecoratedBottom(child) - bottomOffset + 3);
                Point point6_draw = new Point();
                point6_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 42, layoutManager.getDecoratedBottom(child) - bottomOffset - 5);
                Point point7_draw = new Point();
                point7_draw.set(layoutManager.getDecoratedLeft(child) + leftOffset + 35, layoutManager.getDecoratedBottom(child) - bottomOffset - 5);
                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.moveTo(point1_draw.x,point1_draw.y);
                path.lineTo(point2_draw.x,point2_draw.y);
                path.lineTo(point3_draw.x,point3_draw.y);
                path.lineTo(point4_draw.x,point4_draw.y);
                path.lineTo(point5_draw.x,point5_draw.y);
                path.lineTo(point6_draw.x,point6_draw.y);
                path.lineTo(point7_draw.x,point7_draw.y);
                path.lineTo(point1_draw.x,point1_draw.y);
                path.close();
                c.drawPath(path, paintDownload);
            }
        }

    }


}