package me.bastanfar.loadingdotbarfadingdot;

import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

class FadingDotLoadingBar extends View
{
    public FadingDotLoadingBar(Context context)
    {
        super(context);
        init();
    }

    public FadingDotLoadingBar(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        setAttrs(context, attrs);
        init();
    }

    public FadingDotLoadingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setAttrs(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FadingDotLoadingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAttrs(context, attrs);
        init();
    }

    float dotOnePos = .1f;
    float dotTwoPos = .3f;
    float dotThreePos = .5f;
    float dotFourPos = .7f;

    int semiTransparentWhite = 0x40FFFFFF;
    int transparentWhite = 0x00FFFFFF;
    int white = 0xFFFFFFFF;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawRoundRect(getRectF(), getMeasuredWidth(), getMeasuredWidth(), getPaint(semiTransparentWhite));

        canvas.drawCircle(getMeasuredWidth() * dotOnePos, getMeasuredHeight() / 2, 10f, getPaint(transparentWhite));
        canvas.drawCircle(getMeasuredWidth() * dotTwoPos, getMeasuredHeight() / 2, 10f, getPaint(Color.WHITE));
        canvas.drawCircle(getMeasuredWidth() * dotThreePos, getMeasuredHeight() / 2, 10f, getPaint(Color.WHITE));
        canvas.drawCircle(getMeasuredWidth() * dotFourPos, getMeasuredHeight() / 2, 10f, getPaint(white));


    }

    private RectF getRectF()
    {
        return new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    private Paint getPaint(int color)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }

    private void init()
    {
        PropertyValuesHolder dOne = PropertyValuesHolder.ofFloat("D1", .1f, .3f);
        PropertyValuesHolder dTwo = PropertyValuesHolder.ofFloat("D2", .3f, .5f);
        PropertyValuesHolder dThree = PropertyValuesHolder.ofFloat("D3", .5f, .7f);
        PropertyValuesHolder dFour = PropertyValuesHolder.ofFloat("D4", .7f, .9f);
        PropertyValuesHolder dOneColor = PropertyValuesHolder.ofObject("D1C", new ArgbEvaluator(), 0x00FFFFFF, 0xFFFFFFFF);
        PropertyValuesHolder dFourColor = PropertyValuesHolder.ofObject("D4C", new ArgbEvaluator(), 0xFFFFFFFF, 0x00FFFFFF);


        ValueAnimator animator = new ValueAnimator();
        animator.setValues(dOne, dTwo, dThree, dFour, dOneColor, dFourColor);
        animator.setDuration(800);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation)
            {
                dotOnePos = (float) animation.getAnimatedValue("D1");
                dotTwoPos = (float) animation.getAnimatedValue("D2");
                dotThreePos = (float) animation.getAnimatedValue("D3");
                dotFourPos = (float) animation.getAnimatedValue("D4");

                transparentWhite = (int) animation.getAnimatedValue("D1C");
                white = (int) animation.getAnimatedValue("D4C");
                invalidate();
            }
        });
        animator.start();
    }

    private void setAttrs(Context context, @Nullable AttributeSet attrs)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadingDotLoadingBar, 0, 0);
    }

}
