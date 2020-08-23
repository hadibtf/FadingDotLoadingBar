package me.bastanfar.loadingdotbarfadingdot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

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

    int dotOneColor = 0x00FFFFFF;
    int dotFourColor = 0xFFFFFFFF;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        RectF rect = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        Paint paint = new Paint();
        paint.setColor(0x40FFFFFF);

        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        Paint paint2 = new Paint();
        paint2.setColor(0xFFFFFFFF);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);


        Paint paintD1C = new Paint();
        paintD1C.setColor(dotOneColor);
        paintD1C.setStyle(Paint.Style.FILL);
        paintD1C.setAntiAlias(true);

        Paint paintD4C = new Paint();
        paintD4C.setColor(dotFourColor);
        paintD4C.setStyle(Paint.Style.FILL);
        paintD4C.setAntiAlias(true);


        canvas.drawRoundRect(rect, getMeasuredWidth(), getMeasuredWidth(), paint);

        canvas.drawCircle(getMeasuredWidth() * dotOnePos, getMeasuredHeight() / 2, 10f, paintD1C);
        canvas.drawCircle(getMeasuredWidth() * dotTwoPos, getMeasuredHeight() / 2, 10f, paint2);
        canvas.drawCircle(getMeasuredWidth() * dotThreePos, getMeasuredHeight() / 2, 10f, paint2);
        canvas.drawCircle(getMeasuredWidth() * dotFourPos, getMeasuredHeight() / 2, 10f, paintD4C);


    }

    private void init()
    {
        PropertyValuesHolder dOne = PropertyValuesHolder.ofFloat("D1", .1f, .3f);
//        PropertyValuesHolder dOneColor = PropertyValuesHolder.ofInt("D1C", 0x00FFFFFF, 0xFFFFFFFF);

        ValueAnimator d1cAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), 0x00FFFFFF, 0xFFFFFFFF);
        d1cAnimator.setDuration(1000);
        d1cAnimator.setRepeatMode(ValueAnimator.REVERSE);
        d1cAnimator.setRepeatCount(ValueAnimator.INFINITE);
        d1cAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                dotOneColor = (int) animation.getAnimatedValue();
            }
        });
        d1cAnimator.start();

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(dOne);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation)
            {

                dotOnePos = (float) animation.getAnimatedValue("D1");
//                dotOneColor = (int) animation.getAnimatedValue("D1C");

                invalidate();
            }
        });
        animator.start();

        // ---------------------------------------------------------------------------------------//
        PropertyValuesHolder dTwo = PropertyValuesHolder.ofFloat("D2", .3f, .5f);

        ValueAnimator animator2 = new ValueAnimator();
        animator2.setValues(dTwo);
        animator2.setDuration(900);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation)
            {

                dotTwoPos = (float) animation.getAnimatedValue("D2");

                invalidate();
            }
        });
        animator2.start();

        // ---------------------------------------------------------------------------------------//
        PropertyValuesHolder dThree = PropertyValuesHolder.ofFloat("D3", .5f, .7f);

        final ValueAnimator animator3 = new ValueAnimator();
        animator3.setValues(dThree);
        animator3.setDuration(800);
        animator3.setRepeatMode(ValueAnimator.REVERSE);
        animator3.setRepeatCount(ValueAnimator.INFINITE);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation)
            {

                dotThreePos = (float) animation.getAnimatedValue("D3");

                invalidate();
            }
        });
        animator3.start();

        ValueAnimator d2cAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), 0xFFFFFFFF, 0x00FFFFFF);
        d2cAnimator.setDuration(700);
        d2cAnimator.setRepeatMode(ValueAnimator.REVERSE);
        d2cAnimator.setRepeatCount(ValueAnimator.INFINITE);
        d2cAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                dotFourColor = (int) animation.getAnimatedValue();
            }
        });
        d2cAnimator.start();

        // ---------------------------------------------------------------------------------------//
        PropertyValuesHolder dFour = PropertyValuesHolder.ofFloat("D4", .7f, .9f);

        final ValueAnimator animator4 = new ValueAnimator();
        animator4.setValues(dFour);
        animator4.setDuration(700);
        animator4.setRepeatMode(ValueAnimator.REVERSE);
        animator4.setRepeatCount(ValueAnimator.INFINITE);
        animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation)
            {

                dotFourPos = (float) animation.getAnimatedValue("D4");

                invalidate();
            }
        });

//        restartAnim(animator);
//        restartAnim(animator2);
//        restartAnim(animator3);
//        restartAnim(animator4);
//        restartAnim(d1cAnimator);
//        restartAnim(d2cAnimator);
        animator4.start();

    }

    private void restartAnim(final ValueAnimator animator)
    {
        animator.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                animator.start();
                super.onAnimationEnd(animation);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


//        setMeasuredDimension(width, height);
    }

    private void setAttrs(Context context, @Nullable AttributeSet attrs)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadingDotLoadingBar, 0, 0);
    }

}
