package com.feue.ml.fragment.learn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.feue.ml.R;
import com.feue.ml.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.feue.ml.adapter.base.delegate.SimpleDelegateAdapter;
import com.feue.ml.adapter.entity.VideoInfo;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentVideoBinding;
import com.feue.ml.utils.DemoDataProvider;
import com.feue.ml.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.List;

import me.samlss.broccoli.Broccoli;

@Page(name = "视频")
public class VideoFragment extends BaseFragment<FragmentVideoBinding> {

    public static final String KEY_VIDEO_INFOS = "video_infos";

    @AutoWired(name = KEY_VIDEO_INFOS)
    List<VideoInfo> videoInfos;

    private SimpleDelegateAdapter<VideoInfo> mVideoAdapter;

    @NonNull
    @Override
    protected FragmentVideoBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentVideoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.setTitle("第三章 C语言的数据类型");
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_course_comment) {
            @Override
            public void performAction(View view) {
//                XToastUtils.toast("评价");
                openNewPage(CommentFragment.class);
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {
        binding.videoView.setVideoPath(getActivity().getExternalFilesDir(null).getPath()+"/test.mp4").getPlayer().start();
//        binding.videoView.setVideoPath("rtmp://192.168.1.104:1935/oflaDemo/laya.mp4").getPlayer().start();

        binding.videoNameInfo.setText(DemoDataProvider.getDemoVideoInfos().get(0).getName());
        binding.expandVideoAbout.setText(DemoDataProvider.getDemoVideoInfos().get(0).getAbout());

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.videoList.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.videoList.setRecycledViewPool(viewPool);

        mVideoAdapter = new BroccoliSimpleDelegateAdapter<VideoInfo>(R.layout.adapter_video_list_item,
                new LinearLayoutHelper(), DemoDataProvider.getDemoVideoInfos()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, VideoInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.video_name, model.getName());

                    holder.click(R.id.video_item_view, v -> XToastUtils.toast("点击了"+position+"位置"));
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.video_name)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(mVideoAdapter);

        binding.videoList.setAdapter(delegateAdapter);
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
                mVideoAdapter.refresh(DemoDataProvider.getDemoVideoInfos());
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
