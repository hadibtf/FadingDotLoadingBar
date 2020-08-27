package me.bastanfar.fadingdotloading;

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

public class FadingDotLoadingBar extends View implements ValueAnimator.AnimatorUpdateListener
{
    private ValueAnimator animator;
    private int dotSize;

    // Initial color for all dots
    // They are set separately because their values will be animated by the ValueAnimator
    private int dotOneColor;
    private int dotTwoBColor;
    private int dotThreeColor;
    private int dotFourColor;

    // The main colors that dotOneColor and dotFourColor will be animated to so we set the separately
    // because their value must be static
    private int dotColor;
    private int transparentDotColor;

    private int animDuration;
    private boolean backgroundBoxVisibility;
    private int backgroundBoxBorderRadius;
    private int backgroundBoxColor;

    private int width;
    private int height;

    // Pos = Position
    // X = Horizontal axis
    // This is the multiplication of width
    // [ex: width * 0.2f] This means the X position of view is in the 20 percent of view's width
    private float dotOneXPos = .1f;
    private float dotTwoXPos = .3f;
    private float dotThreeXPos = .5f;
    private float dotFourXPos = .7f;

    // Amount of distance that dots will be animated to on X axis. (Again position multiplication)
    private float animationDistance = .2f;

    // Y = Vertical axis
    private int dotYPos;

    private int semiTransparentWhite = 0x40FFFFFF;

    // POS = Position
    // PR NAME = Property Name
    private static final String DOT_ONE_X_POS_PR_NAME = "DotOne";
    private static final String DOT_TWO_X_POS_PR_NAME = "DotTwo";
    private static final String DOT_THREE_X_POS_PR_NAME = "DotThree";
    private static final String DOT_FOUR_X_POS_PR_NAME = "DotFour";

    private static final String DOT_ONE_COLOR_PR_NAME = "DotOneColor";
    private static final String DOT_FOUR_COLOR_PR_NAME = "DotFourColor";

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

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
        dotYPos = height / 2;


        if (backgroundBoxVisibility)
        {
            canvas.drawRoundRect(getRectF(), backgroundBoxBorderRadius, backgroundBoxBorderRadius, getPaint(backgroundBoxColor));
        }

        canvas.drawCircle(width * dotOneXPos, dotYPos, dotSize, getPaint(dotOneColor));
        canvas.drawCircle(width * dotTwoXPos, dotYPos, dotSize, getPaint(dotTwoBColor));
        canvas.drawCircle(width * dotThreeXPos, dotYPos, dotSize, getPaint(dotThreeColor));
        canvas.drawCircle(width * dotFourXPos, dotYPos, dotSize, getPaint(dotFourColor));
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation)
    {
        dotOneXPos = (float) animation.getAnimatedValue(DOT_ONE_X_POS_PR_NAME);
        dotTwoXPos = (float) animation.getAnimatedValue(DOT_TWO_X_POS_PR_NAME);
        dotThreeXPos = (float) animation.getAnimatedValue(DOT_THREE_X_POS_PR_NAME);
        dotFourXPos = (float) animation.getAnimatedValue(DOT_FOUR_X_POS_PR_NAME);

        dotOneColor = (int) animation.getAnimatedValue(DOT_ONE_COLOR_PR_NAME);
        dotFourColor = (int) animation.getAnimatedValue(DOT_FOUR_COLOR_PR_NAME);

        invalidate();
    }

    public void setDotColor(int color)
    {
        dotColor = dotTwoBColor = dotThreeColor = dotFourColor = color;
        transparentDotColor = dotOneColor = convertToTransparent(color);
        postInvalidate();
    }

    public void setDotSize(int dotSize)
    {
        this.dotSize = dotSize;
        postInvalidate();
    }

    public void setAnimDuration(int animDuration)
    {
        this.animDuration = animDuration;
        postInvalidate();
    }

    public void setBackgroundBoxVisibility(boolean backgroundBoxVisibility)
    {
        this.backgroundBoxVisibility = backgroundBoxVisibility;
        postInvalidate();
    }

    public void setBackgroundBoxColor(int backgroundBoxColor)
    {
        this.backgroundBoxColor = backgroundBoxColor;
        postInvalidate();
    }

    public void setBackgroundBoxBorderRadius(int backgroundBoxBorderRadius)
    {
        this.backgroundBoxBorderRadius = backgroundBoxBorderRadius;
        postInvalidate();
    }

    private void init()
    {
        PropertyValuesHolder dotOneValueHolder = PropertyValuesHolder.ofFloat(DOT_ONE_X_POS_PR_NAME, dotOneXPos, dotOneXPos + animationDistance);
        PropertyValuesHolder dotTwoValueHolder = PropertyValuesHolder.ofFloat(DOT_TWO_X_POS_PR_NAME, dotTwoXPos, dotTwoXPos + animationDistance);
        PropertyValuesHolder dotThreeValueHolder = PropertyValuesHolder.ofFloat(DOT_THREE_X_POS_PR_NAME, dotThreeXPos, dotThreeXPos + animationDistance);
        PropertyValuesHolder dotFourPosValueHolder = PropertyValuesHolder.ofFloat(DOT_FOUR_X_POS_PR_NAME, dotFourXPos, dotFourXPos + animationDistance);

        PropertyValuesHolder dOneColor = PropertyValuesHolder.ofObject(DOT_ONE_COLOR_PR_NAME, new ArgbEvaluator(), transparentDotColor, dotColor);
        PropertyValuesHolder dFourColor = PropertyValuesHolder.ofObject(DOT_FOUR_COLOR_PR_NAME, new ArgbEvaluator(), dotColor, transparentDotColor);

        animator = new ValueAnimator();
        animator.setValues(dotOneValueHolder, dotTwoValueHolder, dotThreeValueHolder, dotFourPosValueHolder, dOneColor, dFourColor);
        animator.setDuration(animDuration);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(this);
        animator.start();
    }

    private void setAttrs(Context context, @Nullable AttributeSet attrs)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadingDotLoadingBar, 0, 0);
        dotSize = typedArray.getInt(R.styleable.FadingDotLoadingBar_dotSize, 10);
        dotColor = dotTwoBColor = dotThreeColor = dotFourColor = typedArray.getColor(R.styleable.FadingDotLoadingBar_dotColor, Color.WHITE);
        transparentDotColor = dotOneColor = convertToTransparent(dotColor);
        animDuration = typedArray.getColor(R.styleable.FadingDotLoadingBar_animDuration, 800);
        backgroundBoxColor = typedArray.getColor(R.styleable.FadingDotLoadingBar_backgroundBoxColor, semiTransparentWhite);
        backgroundBoxVisibility = typedArray.getBoolean(R.styleable.FadingDotLoadingBar_backgroundBoxVisibility, true);
        backgroundBoxBorderRadius = typedArray.getColor(R.styleable.FadingDotLoadingBar_backgroundBoxBorderRadius, width);
    }

    private int convertToTransparent(int dotColor)
    {
        return Color.parseColor("#00" + String.format("#%06x", dotColor).substring(3));
    }

    private RectF getRectF()
    {
        return new RectF(0, 0, width, height);
    }

    private Paint getPaint(int color)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }
}
