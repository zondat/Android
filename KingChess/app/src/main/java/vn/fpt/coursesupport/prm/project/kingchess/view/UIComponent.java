package vn.fpt.coursesupport.prm.project.kingchess.view;

import android.view.View;

import java.util.List;

public interface UIComponent {
    View getView();
    List<UIComponent> getSubcomponents();
    UIComponent getParent();
    void init();
    void enable();
    void disable();
}
