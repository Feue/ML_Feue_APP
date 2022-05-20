package com.feue.ml.fragment.course;

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
import com.feue.ml.adapter.base.delegate.SingleDelegateAdapter;
import com.feue.ml.adapter.entity.CourseInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentCourseManageBinding;
import com.feue.ml.fragment.learn.CourseFragment;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import me.samlss.broccoli.Broccoli;

@Page(name = "课程管理")
public class CourseManageFragment extends BaseFragment<FragmentCourseManageBinding> {

    private SimpleDelegateAdapter<CourseInfo> mCourseAdapter;

    @NonNull
    @Override
    protected FragmentCourseManageBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentCourseManageBinding.inflate(inflater, container, false);
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

        SingleDelegateAdapter headerAdapter = new SingleDelegateAdapter(R.layout.adapter_course_manage_header) {
            @Override
            public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
                holder.text(R.id.title, "我的");

                holder.click(R.id.course_add, v -> {
                    openNewPage(CourseAddFragment.class);
                });
            }
        };

        mCourseAdapter = new BroccoliSimpleDelegateAdapter<CourseInfo>(R.layout.adapter_course_card_view_list_item2,
                new LinearLayoutHelper(), DemoDataProvider.getDemoCourseInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, CourseInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.course_title, model.getName());
                    holder.text(R.id.course_about, model.getAbout());
                    holder.image(R.id.course_image, model.getImageUrl());

                    holder.click(R.id.course_comment, v -> {
//                        XToastUtils.toast("评价管理");
                        openNewPage(CommentManageFragment.class);
                    });
                    holder.click(R.id.course_edit, v -> XToastUtils.toast("编辑"));
                    holder.click(R.id.course_delete, v -> {
                        DialogLoader.getInstance().showConfirmDialog(
                                getContext(),
                                "是否确定删除该课程，删除操作无法撤销！",
                                getString(R.string.lab_yes),
                                (dialog, which) -> {
                                    XToastUtils.toast("删除");
                                    dialog.dismiss();
                                },
                                getString(R.string.lab_no),
                                (dialog, which) -> {
                                    XToastUtils.toast("取消删除！");
                                    dialog.dismiss();
                                }
                        );
                    });
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
        delegateAdapter.addAdapter(headerAdapter);
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
