package com.feue.ml.fragment.learn;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.feue.ml.R;
import com.feue.ml.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SimpleDelegateAdapter;
import com.feue.ml.adapter.entity.CourseInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentLearnBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import me.samlss.broccoli.Broccoli;

@Page(anim = CoreAnim.none)
public class LearnFragment extends BaseFragment<FragmentLearnBinding> {

    private SimpleDelegateAdapter<CourseInfo> mCourseAdapter;

    @NonNull
    @Override
    protected FragmentLearnBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentLearnBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        mCourseAdapter = new BroccoliSimpleDelegateAdapter<CourseInfo>(R.layout.adapter_course_card_view_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoCourseInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, CourseInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.course_title, model.getName());
                    holder.text(R.id.course_about, model.getAbout());
                    holder.image(R.id.course_image, model.getImageUrl());

                    holder.click(R.id.card_view, v -> {
                        XToastUtils.toast(model.getName());
                        openNewPage(CourseFragment.class, CourseFragment.KEY_COURSE_INFO, model);
                    });
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.course_title),
                        holder.findView(R.id.course_about),
                        holder.findView(R.id.course_image)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(mCourseAdapter);

        binding.recyclerView.setAdapter(delegateAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mCourseAdapter.refresh(DemoDataProvider.getDemoCourseInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mCourseAdapter.loadMore(DemoDataProvider.getDemoCourseInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
}
