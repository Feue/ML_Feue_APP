package com.feue.ml.fragment.notice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.feue.ml.R;
import com.feue.ml.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SimpleDelegateAdapter;
import com.feue.ml.adapter.entity.NoticeInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentNoticeBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DrawableUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import me.samlss.broccoli.Broccoli;

@Page(name = "通知")
public class NoticeFragment extends BaseFragment<FragmentNoticeBinding> {

    private SimpleDelegateAdapter<NoticeInfo> mNoticeAdapter;

    @NonNull
    @Override
    protected FragmentNoticeBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentNoticeBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_notice_add) {
            @Override
            public void performAction(View view) {
                XToastUtils.toast("点击了发布公告");
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.noticeList.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.noticeList.setRecycledViewPool(viewPool);

        mNoticeAdapter = new BroccoliSimpleDelegateAdapter<NoticeInfo>(R.layout.adapter_notice_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoNoticeInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, NoticeInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.notice_name, model.getName());
                    holder.text(R.id.notice_content, model.getContent());
                    holder.text(R.id.notice_username, model.getUsername());
                    holder.text(R.id.notice_time, model.getTimeStr());
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.notice_name),
                        holder.findView(R.id.notice_content),
                        holder.findView(R.id.notice_username),
                        holder.findView(R.id.notice_time)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(mNoticeAdapter);

        binding.noticeList.setAdapter(delegateAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mNoticeAdapter.refresh(DemoDataProvider.getDemoNoticeInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mNoticeAdapter.loadMore(DemoDataProvider.getDemoNoticeInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
}
