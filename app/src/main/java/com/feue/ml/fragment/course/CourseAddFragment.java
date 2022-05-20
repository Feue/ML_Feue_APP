package com.feue.ml.fragment.course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentCourseAddBinding;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

@Page(name = "课程创建")
public class CourseAddFragment extends BaseFragment<FragmentCourseAddBinding> {
    @NonNull
    @Override
    protected FragmentCourseAddBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentCourseAddBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("完成") {
            @Override
            public void performAction(View view) {

            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {

    }
}
