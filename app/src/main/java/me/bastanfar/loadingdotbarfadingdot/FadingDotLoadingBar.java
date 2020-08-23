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

class FadingDotLoadingBar extends View implements ValueAnimator.AnimatorUpdateListener
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


    // Pos = Position
    // X = Horizontal axis
    private float dotOneXPos = getMeasuredWidth() * .1f;
    private float dotTwoXPos = getMeasuredWidth() * .3f;
    private float dotThreeXPos = getMeasuredWidth() * .5f;
    private float dotFourXPos = getMeasuredWidth() * .7f;

    // Y = Vertical axis
    private final int dotYPos = getMeasuredHeight() / 2;

    private int semiTransparentWhite = 0x40FFFFFF;
    private int transparentWhite = 0x00FFFFFF;
    private int white = 0xFFFFFFFF;


    // POS = Position
    // PR NAME = Property Name
    private static final String DOT_ONE_X_POS_PR_NAME = "DotOne";
    private static final String DOT_TWO_X_POS_PR_NAME = "DotTwo";
    private static final String DOT_THREE_X_POS_PR_NAME = "DotThree";
    private static final String DOT_FOUR_X_POS_PR_NAME = "DotFour";

    private static final String DOT_ONE_COLOR_PR_NAME = "DotOneColor";
    private static final String DOT_FOUR_COLOR_PR_NAME = "DotFourColor";

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawRoundRect(getRectF(), getMeasuredWidth(), getMeasuredWidth(), getPaint(semiTransparentWhite));

        canvas.drawCircle(dotOneXPos, dotYPos, 10f, getPaint(transparentWhite));
        canvas.drawCircle(dotTwoXPos, dotYPos, 10f, getPaint(Color.WHITE));
        canvas.drawCircle(dotThreeXPos, dotYPos, 10f, getPaint(Color.WHITE));
        canvas.drawCircle(dotFourXPos, dotYPos, 10f, getPaint(white));


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
        PropertyValuesHolder dotOneValueHolder = PropertyValuesHolder.ofFloat(DOT_ONE_X_POS_PR_NAME, dotOneXPos, dotOneXPos + .2f);
        PropertyValuesHolder dotTwoValueHolder = PropertyValuesHolder.ofFloat(DOT_TWO_X_POS_PR_NAME, dotTwoXPos, dotTwoXPos + .2f);
        PropertyValuesHolder dotThreeValueHolder = PropertyValuesHolder.ofFloat(DOT_THREE_X_POS_PR_NAME, dotThreeXPos, dotThreeXPos + .2f);
        PropertyValuesHolder dotFourPosValueHolder = PropertyValuesHolder.ofFloat(DOT_FOUR_X_POS_PR_NAME, dotFourXPos, dotFourXPos + .2f);

        PropertyValuesHolder dOneColor = PropertyValuesHolder.ofObject(DOT_ONE_COLOR_PR_NAME, new ArgbEvaluator(), transparentWhite, white);
        PropertyValuesHolder dFourColor = PropertyValuesHolder.ofObject(DOT_FOUR_COLOR_PR_NAME, new ArgbEvaluator(), white, transparentWhite);


        ValueAnimator animator = new ValueAnimator();
        animator.setValues(dotOneValueHolder, dotTwoValueHolder, dotThreeValueHolder, dotFourPosValueHolder, dOneColor, dFourColor);
        animator.setDuration(800);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(this);
        animator.start();
    }

    private void setAttrs(Context context, @Nullable AttributeSet attrs)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadingDotLoadingBar, 0, 0);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation)
    {
        dotOneXPos = (float) animation.getAnimatedValue(DOT_ONE_X_POS_PR_NAME);
        dotTwoXPos = (float) animation.getAnimatedValue(DOT_TWO_X_POS_PR_NAME);
        dotThreeXPos = (float) animation.getAnimatedValue(DOT_THREE_X_POS_PR_NAME);
        dotFourXPos = (float) animation.getAnimatedValue(DOT_FOUR_X_POS_PR_NAME);

        transparentWhite = (int) animation.getAnimatedValue(DOT_ONE_COLOR_PR_NAME);
        white = (int) animation.getAnimatedValue(DOT_FOUR_COLOR_PR_NAME);
        invalidate();
    }
}
