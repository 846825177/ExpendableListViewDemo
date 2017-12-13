package china.http.com.expendablelistviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 农民伯伯
 * @version 2017/12/13
 */

public class MyRadioGroup extends RadioGroup {
    //当我们new的时候传入一个上下文， 他就会调用一个参数的构造方法
    public MyRadioGroup(Context context) {
        this(context, null);
    }
    private  boolean LINE_MODE=false;
    private int mScreenWidth;
    private int mHorizontalSpacing = 20;
    private int mVerticalSpacing = 20;


    //会在我们在布局文件中书写某个控件时，没有调用自定义属性时调用
    public MyRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setLineModel(boolean mode){
        LINE_MODE=mode;
        invalidate();
        requestLayout();
    }
    /**
     * 这就是父级给它传来一个测量值
     * <p>
     * 测量= 测量模式+测量值
     * 测量模式 3种
     * EXACTLY:精确 100dp match_parent
     * AT_MOST : wrap content
     * UNSPCIFIED：子控件想要多大就多大， 很少见
     *
     * @param widthMeasureSpec  宽度
     * @param heightMeasureSpec 高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int childCount = getChildCount();
        int lineheight = 0;
        int childHeight = 0;
        int i1 = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            int measuredHeight = childAt.getMeasuredHeight();
            childHeight = Math.max(measuredHeight, childHeight);
            lineheight += childHeight;
        }
        if(LINE_MODE){
            setMeasuredDimension(widthSize, MeasureSpec.EXACTLY == mode ? heightMeasureSpec : lineheight / 2);
        }else {
            setMeasuredDimension(widthSize, MeasureSpec.EXACTLY == mode ? heightMeasureSpec : childHeight);
        }

    }

    public void setSpacing(int horizontalSpacing, int verticalSpacing) {
        mHorizontalSpacing = horizontalSpacing;
        mVerticalSpacing = verticalSpacing;
    }

    /**
     * 存储所有的view 但是是一行一行的存储
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 每一行的高度
     */
    private List<Integer> mLineHeight = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mTotalHeight = 0;
        int mTotalWidth = 0;
        int mTempHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            mTempHeight = (measureHeight > mTempHeight) ? measureHeight : mTempHeight;
            if (i > 0 && i % 3 == 0) {
                mTotalWidth = 0;
                mTotalHeight += (mTempHeight + mVerticalSpacing);
                mTempHeight = 0;
            }
            childView.layout(mTotalWidth + mHorizontalSpacing, mTotalHeight, measuredWidth + mTotalWidth + mHorizontalSpacing, mTotalHeight + measureHeight);
            mTotalWidth += (measuredWidth + mHorizontalSpacing);
        }


    }


    /**
     * 与当前ViewGroup对应的LayoutParams
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //一个可以在代码中直接设置margin的方法
        return new LayoutParams(getContext(), attrs);
    }
}