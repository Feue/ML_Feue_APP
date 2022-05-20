package com.feue.ml.fragment.learn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentCommentBinding;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

@Page(name = "撰写评价")
public class CommentFragment extends BaseFragment<FragmentCommentBinding> {
    @NonNull
    @Override
    protected FragmentCommentBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentCommentBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("提交") {
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
