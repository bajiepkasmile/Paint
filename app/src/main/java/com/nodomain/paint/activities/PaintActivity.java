package com.nodomain.paint.activities;

import android.graphics.Bitmap;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.paint.R;
import com.nodomain.paint.paint.PictureStorageManager;
import com.nodomain.paint.paint.ToolsParamsChangeListener;
import com.nodomain.paint.paint.fragments.AerosolToolFragment;
import com.nodomain.paint.paint.fragments.BaseToolFragment;
import com.nodomain.paint.paint.fragments.BrushToolFragment;
import com.nodomain.paint.paint.fragments.EaserToolFragment;
import com.nodomain.paint.paint.fragments.FilterToolFragment;
import com.nodomain.paint.paint.fragments.LineToolFragment;
import com.nodomain.paint.paint.fragments.LoadFragment;
import com.nodomain.paint.paint.fragments.PenToolFragment;
import com.nodomain.paint.paint.fragments.PipetteToolFragment;
import com.nodomain.paint.paint.fragments.PrintToolFragment;
import com.nodomain.paint.paint.fragments.SaveFragment;
import com.nodomain.paint.paint.fragments.ShapeToolFragment;
import com.nodomain.paint.paint.fragments.TextToolFragment;
import com.nodomain.paint.paint.PaintTool;
import com.nodomain.paint.paint.PaintView;
import com.nodomain.paint.paint.utils.StorageManager;

public class PaintActivity extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener, ToolsParamsChangeListener,
        View.OnClickListener, PictureStorageManager {

    private PaintView paintView;
    private TextView tvToolName;
    private View flToolFragmentContainer;
    private BaseToolFragment currentToolFragment;
    private PopupMenu toolsMenu;
    private PopupMenu actionsMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint);
        initViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_tools:
                toolsMenu.show();
                break;
            case R.id.iv_settings:
                if (flToolFragmentContainer.getVisibility() == View.VISIBLE) {
                    flToolFragmentContainer.setVisibility(View.GONE);
                } else {
                    flToolFragmentContainer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_actions:
                actionsMenu.show();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_aerosol:
                tvToolName.setText(R.string.aerosol);
                paintView.setPaintTool(PaintTool.AEROSOL);
                replaceToolFragment(
                        AerosolToolFragment.newInstance(
                                paintView.getPaintEngine().getColor(),
                                paintView.getPaintEngine().getIntensity(),
                                paintView.getPaintEngine().getRadius()
                        )
                );
                break;
            case R.id.menu_brush:
                tvToolName.setText(R.string.brush);
                paintView.setPaintTool(PaintTool.BRUSH);
                replaceToolFragment(
                        BrushToolFragment.newInstance(
                            paintView.getPaintEngine().getColor(),
                            paintView.getPaintEngine().getSize()
                        )
                );
                break;
            case R.id.menu_easer:
                tvToolName.setText(R.string.easer);
                paintView.setPaintTool(PaintTool.EASER);
                replaceToolFragment(
                        EaserToolFragment.newInstance(paintView.getPaintEngine().getSize())
                );
                break;
            case R.id.menu_pen:
                tvToolName.setText(R.string.pen);
                paintView.setPaintTool(PaintTool.PEN);
                replaceToolFragment(
                        PenToolFragment.newInstance(paintView.getPaintEngine().getColor())
                );
                break;
            case R.id.menu_pipette:
                tvToolName.setText(R.string.pipette);
                paintView.setPaintTool(PaintTool.PIPETTE);
                replaceToolFragment(
                        PipetteToolFragment.newInstance(paintView.getPaintEngine().getColor())
                );
                break;
            case R.id.menu_text:
                tvToolName.setText(R.string.text);
                paintView.setPaintTool(PaintTool.TEXT);
                replaceToolFragment(
                        TextToolFragment.newInstance(
                                paintView.getPaintEngine().getText(),
                                paintView.getPaintEngine().getColor(),
                                paintView.getPaintEngine().getSize()
                        )
                );
                break;
            case R.id.menu_line:
                tvToolName.setText(R.string.line);
                paintView.setPaintTool(PaintTool.LINE);
                replaceToolFragment(
                        LineToolFragment.newInstance(
                                paintView.getPaintEngine().getColor(),
                                paintView.getPaintEngine().getSize()
                        )
                );
                break;
            case R.id.menu_oval:
                tvToolName.setText(R.string.oval);
                paintView.setPaintTool(PaintTool.OVAL);
                replaceToolFragment(
                        ShapeToolFragment.newInstance(
                                paintView.getPaintEngine().getColor()
                        )
                );
                break;
            case R.id.menu_rectangle:
                tvToolName.setText(R.string.rectangle);
                paintView.setPaintTool(PaintTool.RECTANGLE);
                replaceToolFragment(
                        ShapeToolFragment.newInstance(
                                paintView.getPaintEngine().getColor()
                        )
                );
                break;
            case R.id.menu_circle:
                tvToolName.setText(R.string.circle);
                paintView.setPaintTool(PaintTool.CIRCLE);
                replaceToolFragment(
                        ShapeToolFragment.newInstance(
                                paintView.getPaintEngine().getColor()
                        )
                );
                break;
            case R.id.menu_filter:
                tvToolName.setText(R.string.filter);
                paintView.setPaintTool(PaintTool.FILTER);
                replaceToolFragment(FilterToolFragment.newInstance());
                break;
            case R.id.menu_print:
                tvToolName.setText(R.string.print);
                paintView.setPaintTool(PaintTool.PRINT);
                replaceToolFragment(
                        PrintToolFragment.newInstance(
                                paintView.getPaintEngine().getColor(),
                                paintView.getPaintEngine().getSize()
                        )
                );
                break;
            case R.id.menu_save:
                replaceFragment(R.id.fl_tool_fragment_container, SaveFragment.newInstance());
                tvToolName.setText("Сохранить");
                paintView.setPaintTool(PaintTool.NONE);
                flToolFragmentContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.menu_load:
                replaceFragment(R.id.fl_tool_fragment_container, LoadFragment.newInstance());
                tvToolName.setText("Загрузить");
                paintView.setPaintTool(PaintTool.NONE);
                flToolFragmentContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.menu_clear:
                paintView.clear();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onSizeChange(int size) {
        paintView.getPaintEngine().setSize(size);
    }

    @Override
    public void onColorChange(int color) {
        paintView.getPaintEngine().setColor(color);

        if (currentToolFragment instanceof PipetteToolFragment)           // (=_=) hmmm...
            ((PipetteToolFragment) currentToolFragment).setColor(color);
    }

    @Override
    public void onRadiusChange(int radius) {
        paintView.getPaintEngine().setRadius(radius);
    }

    @Override
    public void onIntensityChange(int intensity) {
        paintView.getPaintEngine().setIntensity(intensity);
    }

    @Override
    public void onTextChange(String text) {
        paintView.getPaintEngine().setText(text);
    }

    @Override
    public void onPrintChange(Bitmap print) {
        paintView.getPaintEngine().setPrint(print);
    }

    @Override
    public void onApplyFilter(ColorMatrixColorFilter filter) {
        paintView.getPaintEngine().setFilter(filter);
        paintView.applyFilter();
    }

    @Override
    public void saveCurrentPicture(String pictureName) {
        StorageManager.getInstance().savePicture(paintView.getPicture(), pictureName);
        Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadPicture(String pictureName) {
        Bitmap picture = StorageManager.getInstance().loadPicture(pictureName);
        paintView.setPicture(picture);
        Toast.makeText(this, "Загружено", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        View ivTools = findViewById(R.id.iv_tools);
        View ivSettings = findViewById(R.id.iv_settings);
        View ivActions = findViewById(R.id.iv_actions);
        tvToolName = (TextView) findViewById(R.id.tv_tool_name);
        paintView = (PaintView) findViewById(R.id.paint_view);
        flToolFragmentContainer = findViewById(R.id.fl_tool_fragment_container);

        ivTools.setOnClickListener(this);
        ivSettings.setOnClickListener(this);
        ivActions.setOnClickListener(this);
        paintView.setListener(this);

        toolsMenu = createMenu(tvToolName, this, R.menu.tools);
        actionsMenu = createMenu(ivActions, this, R.menu.actions);

        replaceToolFragment(
                BrushToolFragment.newInstance(
                        paintView.getPaintEngine().getColor(),
                        paintView.getPaintEngine().getSize()
                )
        );
    }

    private void replaceToolFragment(BaseToolFragment fragment) {
        currentToolFragment = fragment;
        currentToolFragment.setListener(this);
        replaceFragment(R.id.fl_tool_fragment_container, fragment);
    }

    private void replaceFragment(int container, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_tool_fragment_container, fragment)
                .commit();
    }

    private PopupMenu createMenu(View view, PopupMenu.OnMenuItemClickListener listener, @MenuRes int menuRes) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(listener);
        popupMenu.inflate(menuRes);
        return popupMenu;
    }

}
