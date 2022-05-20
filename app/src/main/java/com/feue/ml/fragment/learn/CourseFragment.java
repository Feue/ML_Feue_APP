package com.feue.ml.fragment.learn;

import android.content.Intent;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.feue.ml.R;
import com.feue.ml.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SingleDelegateAdapter;
import com.feue.ml.adapter.entity.ChapterInfo;
import com.feue.ml.adapter.entity.CourseInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentCourseBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import me.samlss.broccoli.Broccoli;
import tcking.github.com.giraffeplayer2.VideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

@Page(name = "课程")
public class CourseFragment extends BaseFragment<FragmentCourseBinding> {

    public static final String KEY_COURSE_INFO = "course_info";

    @AutoWired(name = KEY_COURSE_INFO)
    CourseInfo courseInfo;

    private SimpleDelegateAdapter<ChapterInfo> mChapterAdapter;

    @NonNull
    @Override
    protected FragmentCourseBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentCourseBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.chapterList.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.chapterList.setRecycledViewPool(viewPool);

        SingleDelegateAdapter courseInfoAdapter = new SingleDelegateAdapter(R.layout.adapter_course_info) {
            @Override
            public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
                if (courseInfo != null) {
                    holder.text(R.id.course_name, courseInfo.getName());
                    holder.text(R.id.teacher_name, courseInfo.getTeacherName());
                    holder.text(R.id.category_name, courseInfo.getCategory());
                    ExpandableTextView courseAbout = holder.findViewById(R.id.expand_course_about);
                    courseAbout.setText(courseInfo.getAbout());
                }
            }
        };

        mChapterAdapter = new BroccoliSimpleDelegateAdapter<ChapterInfo>(R.layout.adapter_chapter_card_view_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoChapterInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, ChapterInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.chapter_name, model.getName());
                    holder.text(R.id.chapter_about, model.getAbout());

                    holder.click(R.id.chapter_card_view, v -> {
                        XToastUtils.toast("点击了"+position+"位置");
                        openNewPage(VideoFragment.class);
                    });
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.chapter_name),
                        holder.findView(R.id.chapter_about)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(courseInfoAdapter);
        delegateAdapter.addAdapter(mChapterAdapter);

        binding.chapterList.setAdapter(delegateAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mChapterAdapter.refresh(DemoDataProvider.getDemoChapterInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
//        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
//            // TODO: 2020-02-25 这里只是模拟了网络请求
//            refreshLayout.getLayout().postDelayed(() -> {
//                mChapterAdapter.loadMore(DemoDataProvider.getDemoChapterInfos());
//                refreshLayout.finishLoadMore();
//            }, 1000);
//        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
}
