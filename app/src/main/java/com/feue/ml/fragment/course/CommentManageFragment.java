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
import com.feue.ml.adapter.entity.CommentInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentCommentManageBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup;
import com.xuexiang.xutil.display.DensityUtils;

import me.samlss.broccoli.Broccoli;

@Page(name = "评价管理")
public class CommentManageFragment extends BaseFragment<FragmentCommentManageBinding> {

    private SimpleDelegateAdapter<CommentInfo> mCommentAdapter;

    private XUISimplePopup mPopup;

    @NonNull
    @Override
    protected FragmentCommentManageBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentCommentManageBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.setTitle("C语言程序设计");
        return titleBar;
    }

    private void initPopup() {
        AdapterItem[] popupItem = new AdapterItem[] {
                new AdapterItem("举报")
        };
        mPopup = new XUISimplePopup(getContext(), popupItem)
                .create(DensityUtils.dip2px(getContext(), 100), DensityUtils.dip2px(getContext(), 200), (adapter, item, position) -> {});
    }

    @Override
    protected void initViews() {
        initPopup();

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        mCommentAdapter = new BroccoliSimpleDelegateAdapter<CommentInfo>(R.layout.adapter_comment_card_view_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoCommentInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, CommentInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.comment_user_name, model.getUsername());
                    holder.text(R.id.comment_content, model.getContent());
                    holder.text(R.id.comment_time, model.getTimeStr());
                    holder.text(R.id.comment_chapter_name, model.getChapterName());

                    holder.click(R.id.comment_action_more, v -> {
                        mPopup.show(holder.getView(R.id.comment_action_more));
                    });
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.comment_user_name),
                        holder.findView(R.id.comment_content),
                        holder.findView(R.id.comment_time),
                        holder.findView(R.id.comment_chapter_name)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(mCommentAdapter);

        binding.recyclerView.setAdapter(delegateAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mCommentAdapter.refresh(DemoDataProvider.getDemoCommentInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mCommentAdapter.loadMore(DemoDataProvider.getDemoCommentInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
}
