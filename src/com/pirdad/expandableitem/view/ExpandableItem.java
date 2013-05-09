package com.pirdad.expandableitem.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
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
    private PositionInfo positions;
    private AnimationInfo anim;
    private DragInfo drag;

    private VerticalDragHandler vertical_drag_handler;

    private boolean first_time = true;
    private boolean reset = false;

    public ExpandableItem(Context context) {

        super(context);
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
        positions = new PositionInfo();
        drag = new DragInfo();
        anim = new AnimationInfo();

        vertical_drag_handler = new VerticalDragHandler();
    }

    @Override
    protected void onMeasure(int width_measure_spec, int height_measure_spec) {

        super.onMeasure(width_measure_spec, width_measure_spec);

        int visible_measure_spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        setup.center_layout.measure(width_measure_spec, visible_measure_spec);
        heights.center_height = setup.center_layout.getMeasuredHeight();

        int hidden_measure_spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        setup.bottom_layout.measure(width_measure_spec, hidden_measure_spec);
        heights.bottom_height = setup.bottom_layout.getMeasuredHeight();

        heights.canvas_min_height = heights.center_height;
        heights.canvas_max_height = heights.center_height + heights.bottom_height;

        if (first_time || reset) {

            resetValues();
        }

        if (anim.animate) {

            measureForAnimation();
        }

        if (anim.animate) {

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

    private void resetValues() {

        int canvas_y = 0;
        heights.ht_diff_cntr_nd_bttm = heights.bottom_height - heights.center_height;
        positions.bottom_y = (canvas_y - heights.ht_diff_cntr_nd_bttm);

        positions.max_bottom_y = heights.center_height;
        positions.min_bottom_y = positions.bottom_y;

        if (setup.expanded) positions.bottom_y = positions.max_bottom_y;

        drag.drag_fling_threshold = heights.bottom_height * 0.2f;

        if (first_time) first_time = false;
        if (reset) reset = false;
    }

    private void measureForAnimation() {

        if (positions.bottom_y == positions.max_bottom_y) {
            // open by anim
            anim.bottom_y = anim.bottom_y + (anim.anim_multiplier);
            if (anim.bottom_y >= positions.bottom_y) {
                anim.animate = false;
            }

        } else if (positions.bottom_y == positions.min_bottom_y) {
            // close by anim
            anim.bottom_y = anim.bottom_y - (anim.anim_multiplier);
            if (anim.bottom_y <= positions.bottom_y) {
                anim.animate = false;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);

        setup.center_layout.layout(left, top, right, top + heights.center_height);
        setup.bottom_layout.layout(left, top, right, top + heights.bottom_height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (anim.animate) {

            animateCanvas(canvas);

        } else {

            redraw(canvas, positions.bottom_y);
        }
    }

    private void animateCanvas(Canvas canvas) {

        redraw(canvas, anim.bottom_y);
        requestLayout();
        invalidate();
    }

    private void redraw(Canvas canvas, int bottom_y) {

        canvas.translate(0, bottom_y);
        setup.bottom_layout.draw(canvas);
        canvas.translate(0, (bottom_y * -1));
        setup.center_layout.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (setup.bottom_layout != null) return vertical_drag_handler.onTouch(event);
        else return false;
    }

    public void open(boolean animate) {

        if (!setup.expanded) {

            if (animate) {
                anim.animate = true;
                anim.bottom_y = positions.bottom_y;
                anim.anim_multiplier = heights.bottom_height / 15;
            }

            positions.bottom_y = positions.max_bottom_y;
            requestLayout();
            invalidate();

            setup.expanded = true;
        }
    }

    public void close(boolean animate) {

        if (setup.expanded) {

            if (animate) {
                anim.animate = true;
                anim.bottom_y = positions.bottom_y;
                anim.anim_multiplier = heights.bottom_height / 15;
            }

            positions.bottom_y = positions.min_bottom_y;
            requestLayout();
            invalidate();

            setup.expanded = false;
        }
    }

    public void reset() {

        reset = true;
    }

    // ==================================================================================================
    public interface ItemOpenListener {

        public void onItemOpened(int id, ExpandableItem view);
        public void onItemClosed(int id, ExpandableItem view);
    }

    public class SetupInfo {

        public View center_layout; // is the visible layout
        public View bottom_layout; // is the hidden layout that can be dragged down
        // we can have a layout all around the center_layout in the future...

        public int id = -1;
        public long long_press_delay = 2 * 1000;

        public boolean expanded = false;
        //public boolean two_finger_drag = true;
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

    private class PositionInfo {

        public int bottom_y = 0;
        public int max_bottom_y = 0;
        public int min_bottom_y = 0;
    }

    private class AnimationInfo {

        public boolean animate = false;
        public int bottom_y = 0;
        public int anim_multiplier = 4;
    }

    private class DragInfo {

        public float drag_fling_threshold = 0;
    }

    private enum DIRECTION {UNKNOWN, UP, DOWN, LEFT, RIGHT};
    public enum DRAG_GESTURE {NONE, ONE_FINGER, TWO_FINGER, THREE_FINGER};

    private class VerticalDragHandler {

        private long down_time = 0;
        private float y1 = 0;
        private float y2 = 0;
        private float dn_y = 0;
        private float up_y = 0;
        private boolean is_crct_nmbr_of_pointers = false;
        private DIRECTION dir = DIRECTION.UNKNOWN;

        public boolean onTouch(MotionEvent event) {

            int finger_touch_count = event.getPointerCount();
            int action = event.getAction();

            switch (action) {

                case MotionEvent.ACTION_DOWN:

                    handleMotionDown(event);
                    break;

                case MotionEvent.ACTION_MOVE:

                    int tch_cnt_to_chk = 0;
                    if (setup.drag_gesture == DRAG_GESTURE.ONE_FINGER) tch_cnt_to_chk = 1;
                    if (setup.drag_gesture == DRAG_GESTURE.TWO_FINGER) tch_cnt_to_chk = 2;
                    if (setup.drag_gesture == DRAG_GESTURE.THREE_FINGER) tch_cnt_to_chk = 3;

                    if (setup.drag_gesture != DRAG_GESTURE.NONE && finger_touch_count == tch_cnt_to_chk) {
                        is_crct_nmbr_of_pointers = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        handleMotionMove(event);
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
        }

        private void handleMotionMove(MotionEvent event) {

            y2 = event.getRawY();
            float change_y = y2 - y1;

            DIRECTION new_dir = DIRECTION.UNKNOWN;
            if (change_y > 0) new_dir = DIRECTION.DOWN;
            if (change_y < 0) new_dir = DIRECTION.UP;

            positions.bottom_y = positions.bottom_y + (int) change_y;
            if (positions.bottom_y > positions.max_bottom_y) positions.bottom_y = positions.max_bottom_y;
            if (positions.bottom_y < positions.min_bottom_y) positions.bottom_y = positions.min_bottom_y;

            if (dir == DIRECTION.DOWN && new_dir == DIRECTION.UP) dn_y = y2;
            if (dir == DIRECTION.UP && new_dir == DIRECTION.DOWN) dn_y = y2;

            y1 = y2;
            dir = new_dir;

            requestLayout();
            invalidate();
        }

        private void handleMotionUp(MotionEvent event) {

            up_y = event.getRawY();
            float change_y = up_y - dn_y;

            if (is_crct_nmbr_of_pointers) {

                if (change_y == 0) dir = DIRECTION.UNKNOWN;
                if (change_y > 0) dir = DIRECTION.DOWN;
                if (change_y < 0) dir = DIRECTION.UP;

                anim.animate = true;
                anim.bottom_y = positions.bottom_y;
                anim.anim_multiplier = heights.bottom_height / 15;

                if (Math.abs(change_y) > drag.drag_fling_threshold) {
                    if (dir == DIRECTION.DOWN) positions.bottom_y = positions.max_bottom_y;
                    if (dir == DIRECTION.UP) positions.bottom_y = positions.min_bottom_y;
                }
                if (Math.abs(change_y) < drag.drag_fling_threshold) {
                    if (dir == DIRECTION.DOWN) positions.bottom_y = positions.min_bottom_y;
                    if (dir == DIRECTION.UP) positions.bottom_y = positions.max_bottom_y;
                }

                if (dir == DIRECTION.UNKNOWN) {
                    // IF DIRECTION IS SOMEHOW UNKNOWN, THEN SET THE BOTTOM_Y TO THE NEAREST MIN OR MAX VALUES
                    int half_of_movement = heights.bottom_height / 2;
                    if (change_y >= half_of_movement) positions.bottom_y = positions.max_bottom_y;
                    else positions.bottom_y = positions.min_bottom_y;
                }

                requestLayout();
                invalidate();

                if (!setup.expanded && positions.bottom_y == positions.max_bottom_y) {
                    setup.expanded = true;
                    if (setup.open_listener != null) setup.open_listener.onItemOpened(setup.id, ExpandableItem.this);
                }
                if (setup.expanded && positions.bottom_y == positions.min_bottom_y) {
                    setup.expanded = false;
                    if (setup.open_listener != null) setup.open_listener.onItemClosed(setup.id, ExpandableItem.this);
                }

            } else if (setup.long_press_expand) {

                long up_time = System.currentTimeMillis();
                long delay = up_time - down_time;
                if (delay >= setup.long_press_delay) {

                    if (!setup.expanded) {
                        open(true);
                        if (setup.open_listener != null) setup.open_listener.onItemOpened(setup.id, ExpandableItem.this);
                    } else if (setup.expanded) {
                        close(true);
                        if (setup.open_listener != null) setup.open_listener.onItemClosed(setup.id, ExpandableItem.this);
                    }
                }
            }
        }
    }
}
