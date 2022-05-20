package com.feue.ml.fragment.practice;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentPracticeBinding;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.tabbar.TabSegment;

import java.util.ArrayList;

@Page(name = "练习")
public class PracticeFragment extends BaseFragment<FragmentPracticeBinding> {

    private String[] mTitles;

    private final int TAB_COUNT = 5;
    private int mCurrentItemCount = TAB_COUNT;

    @NonNull
    @Override
    protected FragmentPracticeBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentPracticeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initViews() {
        mTitles = new String[] {"计算机基础", "数据结构", "基础算法", "编程语言", "数据库"};

        ArrayList<BaseFragment> fragments = new ArrayList<>(10);
        for (int i = 0; i < mCurrentItemCount; i++) {
            fragments.add(new PracticeListFragment(mTitles[i]));
        }
        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getParentFragmentManager(), fragments);
        binding.contentViewPager.setOffscreenPageLimit(mTitles.length - 1);
        binding.contentViewPager.setAdapter(adapter);

        for (int i = 0; i < mCurrentItemCount; i++) {
            binding.tabSegment.addTab(new TabSegment.Tab(mTitles[i]));
        }
        int space = DensityUtils.dp2px(getContext(), 16);
        binding.tabSegment.setHasIndicator(true);
        binding.tabSegment.setMode(TabSegment.MODE_SCROLLABLE);
        binding.tabSegment.setItemSpaceInScrollMode(space);
        binding.tabSegment.setupWithViewPager(binding.contentViewPager, false);
        binding.tabSegment.setPadding(space, 0, space, 0);
        binding.tabSegment.addOnTabSelectedListener(new TabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                XToastUtils.toast("select " + mTitles[index]);
                binding.contentViewPager.setCurrentItem(index, false);
            }

            @Override
            public void onTabUnselected(int index) {
//                XToastUtils.toast("unSelect " + mTitles[index]);
            }

            @Override
            public void onTabReselected(int index) {
                XToastUtils.toast("reSelect " + mTitles[index]);
            }

            @Override
            public void onDoubleTap(int index) {
                XToastUtils.toast("double tap " + mTitles[index]);
            }
        });
    }
}
