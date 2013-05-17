package com.pirdad.expandableitem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: pirdod
 * Date: 4/26/13
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExpandableItem extends View {

    private Context context;

    public SetupInfo setup;
    private HeightInfo heights;
    private WidthInfo widths;
    private PositionInfo positions;
    private AnimationInfo anim;
    private DragInfo drag;

    private VerticalDragHandler vertical_drag_handler;
    private RightLayoutDragHandler horizontal_right_drag_handler;

    private boolean first_time = true;
    private boolean reset = false;
    private int rightWidthMeasureSpec;

    public ExpandableItem(Context context) {

        super(context);
        setup = new SetupInfo();
        initialize(context, setup);
    }

    public ExpandableItem(Context context, AttributeSet attrs) {

        super(context, attrs);
        setup = getDefaultSetup(context);
        initialize(context, setup);
    }

    public ExpandableItem(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        setup = getDefaultSetup(context);
        initialize(context, setup);
    }

    public SetupInfo getDefaultSetup(Context cxt) {

        SetupInfo setup = new SetupInfo();

        TextView txt_center = new TextView(cxt);
        txt_center.setBackgroundColor(Color.LTGRAY);
        txt_center.setTextColor(Color.BLACK);
        txt_center.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        txt_center.setText("Visible Layout");

        TextView txt_bottom = new TextView(cxt);
        txt_bottom.setBackgroundColor(Color.DKGRAY);
        txt_bottom.setTextColor(Color.WHITE);
        txt_bottom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        txt_bottom.setText("Bottom Hidden Layout");

        setup.center_layout = txt_center;
        setup.bottom_layout = txt_bottom;

        return setup;
    }

    private void initialize(Context cxt, SetupInfo stp) {

        context = cxt;
        setup = stp;
        heights = new HeightInfo();
        widths = new WidthInfo();
        positions = new PositionInfo();
        drag = new DragInfo();
        anim = new AnimationInfo();

        vertical_drag_handler = new VerticalDragHandler();
        horizontal_right_drag_handler = new RightLayoutDragHandler();
    }

    @Override
    protected void onMeasure(int width_measure_spec, int height_measure_spec) {

        super.onMeasure(width_measure_spec, height_measure_spec);

        int visible_measure_spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        setup.center_layout.measure(width_measure_spec, visible_measure_spec);
        widths.center_width = setup.center_layout.getMeasuredWidth();
        heights.center_height = setup.center_layout.getMeasuredHeight();

        widths.canvas_width = widths.center_width;

        int hidden_measure_spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        setup.bottom_layout.measure(width_measure_spec, hidden_measure_spec);
        heights.bottom_height = setup.bottom_layout.getMeasuredHeight();

        int right_measure_spec = MeasureSpec.makeMeasureSpec(widths.canvas_width, MeasureSpec.AT_MOST);
        setup.right_layout.measure(right_measure_spec, hidden_measure_spec);
        widths.right_width = setup.right_layout.getMeasuredWidth();

        heights.canvas_min_height = heights.center_height;
        heights.canvas_max_height = heights.center_height + heights.bottom_height;

        if (first_time || reset) {

            resetValues();
        }

        if (anim.animating) {

            measureForAnimation();
        }

        if (anim.animating) {

            int position_diff = 0 - positions.min_bottom_y;
            heights.canvas_height = heights.canvas_min_height;
            heights.canvas_height += (anim.bottom_y + position_diff);

        } else {

            int position_diff = 0 - positions.min_bottom_y;
            heights.canvas_height = heights.canvas_min_height;
            heights.canvas_height += (positions.bottom_y + position_diff);
        }

        setMeasuredDimension(MeasureSpec.getSize(width_measure_spec), heights.canvas_height);
    }

    public int getRightWidthMeasureSpec() {

        int right_width = setup.right_layout.getLayoutParams().width;
        if (right_width == ViewGroup.LayoutParams.WRAP_CONTENT) {

            return MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        } else if (right_width == ViewGroup.LayoutParams.MATCH_PARENT || right_width == ViewGroup.LayoutParams.FILL_PARENT) {

            return MeasureSpec.makeMeasureSpec(widths.canvas_width, MeasureSpec.AT_MOST);

        } else {

            return MeasureSpec.makeMeasureSpec(right_width, MeasureSpec.EXACTLY);
        }
    }

    private void resetValues() {

        int canvas_y = 0;
        heights.ht_diff_cntr_nd_bttm = heights.bottom_height - heights.center_height;
        positions.bottom_y = (canvas_y - heights.ht_diff_cntr_nd_bttm);

        positions.max_bottom_y = heights.center_height;
        positions.min_bottom_y = positions.bottom_y;

        positions.right_x = widths.canvas_width;
        positions.max_right_x = widths.canvas_width - widths.right_width;
        positions.min_right_x = positions.right_x;

        if (setup.is_expanded) positions.bottom_y = positions.max_bottom_y;

        drag.drag_fling_threshold_bottom = heights.bottom_height * 0.2f;
        drag.drag_fling_threshold_right = widths.right_width * 0.2f;

        if (first_time) first_time = false;
        if (reset) reset = false;
    }

    private void measureForAnimation() {

        if (positions.bottom_y == positions.max_bottom_y) {
            // open by anim
            anim.bottom_y = anim.bottom_y + (anim.anim_multiplier);
            if (anim.bottom_y >= positions.bottom_y) {
                anim.bottom_y = positions.bottom_y;
                anim.animating = false;
            }

        } else if (positions.bottom_y == positions.min_bottom_y) {
            // close by anim
            anim.bottom_y = anim.bottom_y - (anim.anim_multiplier);
            if (anim.bottom_y <= positions.bottom_y) {
                anim.bottom_y = positions.bottom_y;
                anim.animating = false;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);

        setup.center_layout.layout(left, top, right, top + heights.center_height);
        setup.bottom_layout.layout(left, top, right, top + heights.bottom_height);
        setup.right_layout.layout(left, top, left + widths.right_width, top + heights.center_height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (anim.animating) {

            animateCanvas(canvas);

        } else {

            redraw(canvas, positions.bottom_y, positions.right_x);
        }

        checkForOpenOrClose();
    }

    private void animateCanvas(Canvas canvas) {

        redraw(canvas, anim.bottom_y, anim.right_x);
        requestLayout();
        invalidate();
    }

    private void redraw(Canvas canvas, int bottom_y, int right_x) {

        //int right = widths.canvas_width - 50;

        canvas.translate(0, bottom_y);
        setup.bottom_layout.draw(canvas);
        canvas.translate(right_x, (bottom_y * -1));
        setup.right_layout.draw(canvas);
        canvas.translate((right_x * -1) - (widths.canvas_width - right_x), 0);
        setup.center_layout.draw(canvas);
    }

    private void checkForOpenOrClose() {

        if (opening) {

            if (anim.bottom_y == positions.bottom_y && positions.bottom_y == positions.max_bottom_y) {
                resetOpen();
            }

        } else if (closing) {

            if (anim.bottom_y == positions.bottom_y && positions.bottom_y == positions.min_bottom_y) {
                resetClose();
            }
        }
    }

    private void resetOpen() {

        opening = false;
        if (should_notify_on_open && setup.open_listener != null) {
            setup.is_expanded = true;
            setup.open_listener.onItemOpened(setup.id, this);
        }
    }

    private void resetClose() {

        closing = false;
        if (should_notify_on_close && setup.open_listener != null) {
            setup.is_expanded = false;
            setup.open_listener.onItemClosed(setup.id, this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (setup.right_layout != null) return horizontal_right_drag_handler.onTouch(event);
        else return false;
    }
    private boolean should_notify_on_open = true;
    private boolean opening = false;

    public void open(boolean animate, boolean notify_listener) {

        if (!setup.is_expanded) openBottom(animate, notify_listener);
    }

    private void openBottom(boolean animate, boolean notify_listener) {

        should_notify_on_open = notify_listener;
        opening = true;
        if (animate) {
            anim.animating = true;
            anim.anim_multiplier = heights.bottom_height / 15;
            anim.bottom_y = positions.bottom_y;
            positions.bottom_y = positions.max_bottom_y;
        } else {
            positions.bottom_y = positions.max_bottom_y;
            anim.bottom_y = positions.bottom_y;
        }

        requestLayout();
        invalidate();
    }

    private void openRight(boolean animate, boolean notify_listener) {

        should_notify_on_open = notify_listener;
        opening = true;

        if (animate) {
            anim.animating = true;
            anim.anim_multiplier = widths.canvas_width / 15;
            anim.right_x = positions.right_x;
            positions.right_x = positions.max_right_x;
        } else {
            positions.right_x = positions.max_right_x;
            anim.right_x = positions.right_x;
        }

        requestLayout();
        invalidate();
    }
    private boolean should_notify_on_close = true;
    private boolean closing = false;

    public void close(boolean animate, boolean notify_listener) {

        if (setup.is_expanded) closeBottom(animate, notify_listener);
    }

    private void closeBottom(boolean animate, boolean notify_listener) {

        should_notify_on_close = notify_listener;
        closing = true;
        if (animate) {
            anim.animating = true;
            anim.anim_multiplier = heights.bottom_height / 15;
            anim.bottom_y = positions.bottom_y;
            positions.bottom_y = positions.min_bottom_y;
        } else {
            positions.bottom_y = positions.min_bottom_y;
            anim.bottom_y = positions.bottom_y;
        }

        requestLayout();
        invalidate();
    }

    private void closeRight(boolean animate, boolean notify_listener) {

        should_notify_on_close = notify_listener;
        closing = true;
        if (animate) {
            anim.animating = true;
            anim.anim_multiplier = widths.canvas_width / 15;
            anim.right_x = positions.right_x;
            positions.right_x = positions.min_right_x;
        } else {
            positions.right_x = positions.min_right_x;
            anim.right_x = positions.right_x;
        }

        requestLayout();
        invalidate();
    }

    public void reset() {

        reset = true;
        opening = false;
        closing = false;
    }

    // ==================================================================================================
    public interface ItemOpenListener {

        public void onItemOpened(int id, ExpandableItem view);
        public void onItemClosed(int id, ExpandableItem view);
    }

    public class SetupInfo {

        public View center_layout; // is the visible layout
        public View bottom_layout; // is the hidden layout that can be dragged down
        public View right_layout; // is the hidden layout that can be dragged from the right

        public int id = -1;
        public long long_press_delay = 2 * 1000;

        public boolean is_expanded = false;
        public boolean long_press_expand = true;

        public DRAG_GESTURE drag_gesture = DRAG_GESTURE.TWO_FINGER;

        public ItemOpenListener open_listener;
        public Map<String, Object> extra_params;

        public SetupInfo() {

            if (extra_params == null) extra_params = new HashMap<String, Object>();
        }
    }

    private class HeightInfo {

        public int canvas_min_height = 0;
        public int canvas_max_height = 0;
        public int canvas_height = 0;

        public int center_height = 0;
        public int bottom_height = 0;

        public int ht_diff_cntr_nd_bttm = 0;
    }

    private class WidthInfo {

        public int canvas_width = 0;

        public int center_width = 0;
        public int right_width = 0;

        public int wd_diff_cntr_nd_rght = 0;
    }

    private class PositionInfo {

        public int bottom_y = 0;
        public int max_bottom_y = 0;
        public int min_bottom_y = 0;

        public int right_x = 0;
        public int max_right_x = 0;
        public int min_right_x = 0;
    }

    private class AnimationInfo {

        public boolean animating = false;
        public int bottom_y = 0;
        public int right_x = 0;
        public int anim_multiplier = 4;
    }

    private class DragInfo {

        public float drag_fling_threshold_bottom = 0;
        public float drag_fling_threshold_right = 0;
    }

    private enum DIRECTION {UP, DOWN, LEFT, RIGHT};
    public enum DRAG_GESTURE {NONE(0), ONE_FINGER(1), TWO_FINGER(2), THREE_FINGER(3);
        int value;
        DRAG_GESTURE(int num) {
            this.value = num;
        }
    };

    private class VerticalDragHandler {

        private long down_time = 0;
        private float y1 = 0;
        private float y2 = 0;
        private float dn_y = 0;
        private float up_y = 0;
        private boolean is_crct_nmbr_of_pointers = false;
        private DIRECTION dir;

        public boolean onTouch(MotionEvent event) {

            int action = event.getAction();

            switch (action) {

                case MotionEvent.ACTION_DOWN:

                    handleMotionDown(event);
                    break;

                case MotionEvent.ACTION_MOVE:

                    int finger_touch_count = event.getPointerCount();
                    if (finger_touch_count == setup.drag_gesture.value) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        handleMotionMove(event);
                        is_crct_nmbr_of_pointers = true;
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    handleMotionUp(event);
                    is_crct_nmbr_of_pointers = false;
                    break;
            }

            return true;
        }

        private void handleMotionDown(MotionEvent event) {

            down_time = System.currentTimeMillis();
            y1 = event.getRawY();
            dn_y = y1;
            if (positions.bottom_y == positions.min_bottom_y) dir = DIRECTION.DOWN;
            if (positions.bottom_y == positions.max_bottom_y) dir = DIRECTION.UP;
        }

        private void handleMotionMove(MotionEvent event) {

            if (anim.animating) {
                anim.animating = false;
                positions.bottom_y = anim.bottom_y;
                opening = false; closing = false;
            }

            y2 = event.getRawY();
            float change_y = y2 - y1;

            if (change_y > 0) dir = DIRECTION.DOWN;
            if (change_y < 0) dir = DIRECTION.UP;

            positions.bottom_y = positions.bottom_y + (int) change_y;
            if (positions.bottom_y > positions.max_bottom_y) positions.bottom_y = positions.max_bottom_y;
            if (positions.bottom_y < positions.min_bottom_y) positions.bottom_y = positions.min_bottom_y;

            y1 = y2;

            requestLayout();
            invalidate();
        }

        private void handleMotionUp(MotionEvent event) {

            up_y = event.getRawY();
            float change_y = up_y - dn_y;

            if (is_crct_nmbr_of_pointers) {

                if (Math.abs(change_y) > drag.drag_fling_threshold_bottom) {
                    if (dir == DIRECTION.DOWN) openBottom(true, (!setup.is_expanded));
                    if (dir == DIRECTION.UP) closeBottom(true, (setup.is_expanded));
                }
                if (Math.abs(change_y) < drag.drag_fling_threshold_bottom) {
                    if (dir == DIRECTION.DOWN) closeBottom(true, (setup.is_expanded));
                    if (dir == DIRECTION.UP) openBottom(true, (!setup.is_expanded));
                }

            } else if (setup.long_press_expand) {

                long up_time = System.currentTimeMillis();
                long delay = up_time - down_time;
                if (delay >= setup.long_press_delay) {

                    if (!setup.is_expanded && !anim.animating) {
                        open(true, true);
                    } else if (setup.is_expanded && !anim.animating) {
                        close(true, true);
                    }
                }
            }
        }
    }

    private class RightLayoutDragHandler {

        private long down_time = 0;
        private float x1 = 0;
        private float x2 = 0;
        private float dn_x = 0;
        private float up_x = 0;
        private boolean is_crct_nmbr_of_pointers = false;
        private DIRECTION dir;

        public boolean onTouch(MotionEvent event) {

            int action = event.getAction();

            switch (action) {

                case MotionEvent.ACTION_DOWN:

                    handleMotionDown(event);
                    break;

                case MotionEvent.ACTION_MOVE:

                    //int finger_touch_count = event.getPointerCount();
                    //if (finger_touch_count == setup.drag_gesture.value) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        handleMotionMove(event);
                        //is_crct_nmbr_of_pointers = true;
                    //}
                    break;

                case MotionEvent.ACTION_UP:

                    handleMotionUp(event);
                    //is_crct_nmbr_of_pointers = false;
                    break;
            }

            return true;
        }

        private void handleMotionDown(MotionEvent event) {

            down_time = System.currentTimeMillis();
            x1 = event.getRawX();
            dn_x = x1;
            if (positions.right_x == positions.min_right_x) dir = DIRECTION.LEFT;
            if (positions.right_x == positions.max_right_x) dir = DIRECTION.RIGHT;
        }

        private void handleMotionMove(MotionEvent event) {

            if (anim.animating) {
                anim.animating = false;
                positions.right_x = anim.right_x;
                opening = false; closing = false;
            }

            x2 = event.getRawX();
            float change_x = x2 - x1;

            if (change_x < 0) dir = DIRECTION.LEFT;
            if (change_x > 0) dir = DIRECTION.RIGHT;

            positions.right_x = positions.right_x + (int) change_x;
            if (positions.right_x < positions.max_right_x) positions.right_x = positions.max_right_x;
            if (positions.right_x > positions.min_right_x) positions.right_x = positions.min_right_x;

            x1 = x2;

            requestLayout();
            invalidate();
        }

        private void handleMotionUp(MotionEvent event) {

            up_x = event.getRawX();
            float change_y = up_x - dn_x;

            //if (is_crct_nmbr_of_pointers) {

                if (Math.abs(change_y) > drag.drag_fling_threshold_right) {
                    if (dir == DIRECTION.LEFT) openRight(true, (!setup.is_expanded));
                    if (dir == DIRECTION.RIGHT) closeRight(true, (setup.is_expanded));
                }
                if (Math.abs(change_y) < drag.drag_fling_threshold_right) {
                    if (dir == DIRECTION.LEFT) closeRight(true, (setup.is_expanded));
                    if (dir == DIRECTION.RIGHT) openRight(true, (!setup.is_expanded));
                }

            //}
//            else if (setup.long_press_expand) {
//
//                long up_time = System.currentTimeMillis();
//                long delay = up_time - down_time;
//                if (delay >= setup.long_press_delay) {
//
//                    if (!setup.is_expanded && !anim.animating) {
//                        open(true, true);
//                    } else if (setup.is_expanded && !anim.animating) {
//                        close(true, true);
//                    }
//                }
//            }
        }
    }
}
