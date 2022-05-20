package com.feue.ml.fragment.course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.feue.ml.R;
import com.feue.ml.adapter.SearchRecordTagAdapter;
import com.feue.ml.db.entity.SearchRecord;
import com.feue.ml.core.BaseFragment;
import com.feue.ml.databinding.FragmentSearchCourseBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.xuexiang.xaop.annotation.MemoryCache;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xormlite.InternalDataBaseRepository;
import com.xuexiang.xormlite.db.DBService;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.config.AppPageConfig;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.searchview.DefaultSearchFilter;
import com.xuexiang.xui.widget.searchview.MaterialSearchView;
import com.xuexiang.xutil.data.DateUtils;

import java.sql.SQLException;
import java.util.List;

@Page(name = "课程搜索")
public class SearchCourseFragment extends BaseFragment<FragmentSearchCourseBinding> {

    private DBService<SearchRecord> mDBService;
    private SearchRecordTagAdapter mAdapter;

    private View mAction;

    @NonNull
    @Override
    protected FragmentSearchCourseBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentSearchCourseBinding.inflate(inflater, container, false);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        mAction = titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_action_query) {
            @SingleClick
            @Override
            public void performAction(View view) {
                binding.searchView.showSearch();
            }
        });
        return titleBar;
    }

    @Override
    protected void initArgs() {
        mDBService = InternalDataBaseRepository.getInstance().getDataBase(SearchRecord.class);
    }

    @Override
    protected void initViews() {
        binding.searchView.setVoiceSearch(false);
        binding.searchView.setEllipsize(true);
        binding.searchView.setSuggestions(getPageSuggestions());
        binding.searchView.setSearchFilter(new DefaultSearchFilter() {
            @Override
            protected boolean filter(String suggestion, String input) {
                return suggestion.toLowerCase().contains(input.toLowerCase());
            }
        });
        binding.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onQueryResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        binding.searchView.setSubmitOnClick(true);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        binding.recyclerView.setLayoutManager(flexboxLayoutManager);
        binding.recyclerView.setAdapter(mAdapter = new SearchRecordTagAdapter());
        refreshRecord();
    }

    private void refreshRecord() {
        try {
            mAdapter.refresh(mDBService.queryAllOrderBy("time", false));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击查询结果之后
     *
     * @param query
     */
    private void onQueryResult(String query) {
        //直接跳转到指定页面
        openPage(query);
        try {
            SearchRecord record = mDBService.queryForColumnFirst("content", query);
            if (record == null) {
                record = new SearchRecord().setContent(query).setTime(DateUtils.getNowMills());
                mDBService.insert(record);
            } else {
                record.setTime(DateUtils.getNowMills());
                mDBService.updateData(record);
            }
            refreshRecord();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @MemoryCache
    public String[] getPageSuggestions() {
        List<PageInfo> pages = AppPageConfig.getInstance().getPages();
        String[] array = new String[pages.size()];
        for (int i = 0; i < pages.size(); i++) {
            array[i] = pages.get(i).getName();
        }
        return array;
    }
}
