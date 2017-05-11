package com.malalaoshi.android.malapad.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.malalaoshi.android.core.utils.MiscUtil;

/**
 * Created by Donald on 16/8/10.
 */
@SuppressLint("NewApi")
public class LoadingCircleView extends View {
    private Paint mLoadPaint;
    private float mStrokeWidth = 6;
    private float mCenterX, mCenterY, mCenter1;
    private float mRadius;
    private final RectF mRectF = new RectF();
    private int mDegree;
    private int mPaintColor = Color.parseColor("#ffffff");
    private Float mOffsetLeftValue = 0f;
    private Float mOffsetRightValue = 0f;
    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private static final float PADDING = 5;
    private ValueAnimator mCircleAnim;
    private ValueAnimator mLineLeftAnimator;
    private ValueAnimator mLineRightAnimator;

    private boolean mIsCanHide;
    private int progress;
    private int line1_x;
    private int line1_y;
    private int line2_x;
    private int line2_y;

    public LoadingCircleView(Context context) {
        this(context, null);
    }

    public LoadingCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLoadPaint = new Paint();
        mLoadPaint.setAntiAlias(true);
        mLoadPaint.setStrokeJoin(Paint.Join.ROUND);
        mLoadPaint.setStrokeWidth(mStrokeWidth);
        mLoadPaint.setColor(mPaintColor);
        mLoadPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e("LoadingCircleView", "onDraw: ================================");
//        Log.e("LoadingCircleView", "onDraw: mCenterX" + mCenterX);
//        Log.e("LoadingCircleView", "onDraw: mCenterY" + mCenterY);
//        Log.e("LoadingCircleView", "onDraw: mRadius" + mRadius);
//        Log.e("LoadingCircleView", "onDraw: mOffsetLeftValue" + mOffsetLeftValue);
//        Log.e("LoadingCircleView", "onDraw: ================================");
        mRectF.left = mCenterX - mRadius ;
        mRectF.top = mCenterY - mRadius ;
        mRectF.right = mCenterX + mRadius ;
        mRectF.bottom = mCenterY + mRadius ;
        canvas.drawArc(mRectF, 235, mDegree, false, mLoadPaint);
        canvas.drawLine(mCenter1, mCenterY,
                mCenter1 + mOffsetLeftValue, mCenterY + mOffsetLeftValue, mLoadPaint);
        canvas.drawLine(mCenter1 + mOffsetLeftValue -1, mCenterY + mOffsetLeftValue,
                mCenter1 + mOffsetLeftValue + mOffsetRightValue - 1, mCenterY + mOffsetLeftValue - mOffsetRightValue - 1, mLoadPaint);
//        Log.e("LoadingCircleView", "onDraw: ================================");
//        Log.e("LoadingCircleView", "onDraw: SX=" + (mCenterX - mRadius / 2) + ", EX=" + (mCenterX - mRadius / 2 - mOffsetLeftValue));
//        Log.e("LoadingCircleView", "onDraw: SY=" + (mCenterY) + ", EY=" + (mCenterY - mOffsetLeftValue));
//        Log.e("LoadingCircleView", "onDraw: ================================");

//                drawLine(canvas);

    }

    @Override
    public boolean isInLayout() {
        Log.e("LoadingCircleView", "isInLayout: "+super.isInLayout());
        return super.isInLayout();
    }

    private void drawLine(Canvas canvas) {
        progress++;

        /**
         * 绘制圆弧
         */
//        Paint paint = new Paint();
//        //设置画笔颜色
//        paint.setColor(getResources().getColor(R.color.colorPrimary));
//        //设置圆弧的宽度
//        paint.setStrokeWidth(5);
//        //设置圆弧为空心
//        paint.setStyle(Paint.Style.STROKE);
//        //消除锯齿
//        paint.setAntiAlias(true);

        //获取圆心的x坐标
        int center = getWidth() / 2;
        int center1 = center - getWidth() / 5;
        Log.e("LoadingCircleView", "onDraw: " + center + "---" + center1);
        //圆弧半径
        int radius = getWidth() / 2 - 5;

        //定义的圆弧的形状和大小的界限
        RectF rectF = new RectF(center - radius - 1, center - radius - 1, center + radius + 1, center + radius + 1);

        //根据进度画圆弧
        canvas.drawArc(rectF, 235, -360 * progress / 100, false, mLoadPaint);

        /**
         * 绘制对勾
         */
        //先等圆弧画完，才话对勾
        if (progress >= 100) {
            Log.e("LoadingCircleView", "drawLine: "+line1_x+"---"+line1_y);
            if (line1_x < radius / 3) {
                line1_x++;
                line1_y++;
            }
            //画第一根线
            canvas.drawLine(center1, center, center1 + line1_x, center + line1_y, mLoadPaint);

            if (line1_x == radius / 3) {
                line2_x = line1_x;
                line2_y = line1_y;
                line1_x++;
                line1_y++;
            }
            if (line1_x >= radius / 3 && line2_x <= radius) {
                line2_x++;
                line2_y--;
            }
            //画第二根线
            canvas.drawLine(center1 + line1_x - 1, center + line1_y, center1 + line2_x, center + line2_y, mLoadPaint);
        }
        if (line2_x > radius) return;
        //每隔10毫秒界面刷新
//        postInvalidateDelayed(1);
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureWidth(widthMeasureSpec), measureWidth(heightMeasureSpec));
        loadingCircleMeasure();
    }

//    private int measureWidth(int measureSpec) {
//        int result = 0;
//        int mode = MeasureSpec.getMode(measureSpec);
//        int size = MeasureSpec.getSize(measureSpec);
//        if (mode == MeasureSpec.EXACTLY){
//            result = size;
//            Log.e("LoadingCircleView", "measureWidth: "+size);
//        }else {
//            Log.e("LoadingCircleView", "measureWidth: ");
//        }
//
//        return result;
//    }

    private void loadingCircleMeasure() {
        int mViewWidth = MiscUtil.dp2px(100f);
        int mViewHeight = MiscUtil.dp2px(100f);
        int mViewLength = Math.min(mViewHeight, mViewWidth);
        mCenterX = mViewWidth / 2;
        mCenterY = mViewHeight / 2;
        mRadius = mViewLength /2 -  PADDING;
        mCenter1 = mCenterX - mViewWidth / 5;
//        Log.e("LoadingCircleView", "loadingCircleMeasure: ================");
//        Log.e("LoadingCircleView", "loadingCircleMeasure: mViewWidth="+mViewWidth);
//        Log.e("LoadingCircleView", "loadingCircleMeasure: mViewHeight="+mViewHeight);
//        Log.e("LoadingCircleView", "loadingCircleMeasure: mRadius="+mRadius);
//        Log.e("LoadingCircleView", "loadingCircleMeasure: ================");
    }

    public void loadCircle() {
//        Log.e("LoadingCircleView", "loadCircle: ");
        if (null != mAnimatorSet && mAnimatorSet.isRunning()) {
            return;
        }
        initDegreeAndOffset();
        loadingCircleMeasure();
        mCircleAnim = ValueAnimator.ofInt(0, -360);
        mLineLeftAnimator = ValueAnimator.ofFloat(0, mRadius / 3f);
        mLineRightAnimator = ValueAnimator.ofFloat(0, mRadius / 3f * 2);
//        Log.e("LoadingCircleView", "loadCircle: ====================");
//        Log.e("LoadingCircleView", "loadCircle: mRadius=" + mRadius);
//        Log.e("LoadingCircleView", "loadCircle: " + mLineLeftAnimator.toString());
//        Log.e("LoadingCircleView", "loadCircle: " + mLineRightAnimator.toString());
//        Log.e("LoadingCircleView", "loadCircle: ====================");

        mCircleAnim.setDuration(800);
        mLineLeftAnimator.setDuration(270);
        mLineRightAnimator.setDuration(530);
        mCircleAnim.addUpdateListener(animation -> {
            mDegree = (Integer) animation.getAnimatedValue();
//            Log.e("LoadingCircleView", "loadCircle: "+mDegree);
            invalidate();
        });
        mLineLeftAnimator.addUpdateListener(valueAnimator -> {
            mOffsetLeftValue = (Float) valueAnimator.getAnimatedValue();
//                Log.e("LoadingCircleView", "onAnimationUpdate: mOffsetLeftValue=" + mOffsetLeftValue);
            invalidate();
        });
        mLineRightAnimator.addUpdateListener(animation -> {
            mOffsetRightValue = (Float) animation.getAnimatedValue();
//                Log.e("LoadingCircleView", "onAnimationUpdate: mOffsetRightValue=" + mOffsetRightValue);
            invalidate();
        });
        mAnimatorSet.play(mCircleAnim).before(mLineLeftAnimator);
        mAnimatorSet.play(mLineRightAnimator).after(mLineLeftAnimator);
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                stop();
                postDelayed(() -> {
                    if (mEndListner != null) {
                        mEndListner.onCircleDone();
                    }
                }, 800);
            }
        });
        mAnimatorSet.start();
    }

    public void stop() {
        if (null != mCircleAnim) {
            mCircleAnim.end();
        }
        if (null != mLineLeftAnimator) {
            mLineLeftAnimator.end();
        }
        if (null != mLineRightAnimator) {
            mLineRightAnimator.end();
        }
        clearAnimation();
    }

    public boolean isStarted() {
        if (null != mAnimatorSet) {
            return mAnimatorSet.isStarted();
        }
        return false;
    }

    public void initDegreeAndOffset() {
        mDegree = 0;
        mOffsetLeftValue = 0f;
        mOffsetRightValue = 0f;
    }

    public boolean isCanHide() {
        return mIsCanHide;
    }

    public void setCanHide(boolean mCanHide) {
        this.mIsCanHide = mCanHide;
    }

    private OnDoneCircleAnimListner mEndListner;

    public void addCircleAnimatorEndListner(OnDoneCircleAnimListner endListenr) {
        if (null == mEndListner) {
            this.mEndListner = endListenr;
        }
    }

    public interface OnDoneCircleAnimListner {
        void onCircleDone();
    }

    public void removeCircleAnimatorEndListner() {
        mEndListner = null;
    }
}
