package com.github.wally.wcdbsample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.wally.wcdbsample.db.biz.impl.PersonBiz;
import com.github.wally.wcdbsample.enums.SexEnums;
import com.github.wally.wcdbsample.model.dto.PersonDTO;
import com.github.wally.wcdbsample.model.vo.PersonVO;
import com.github.wally.wcdbsample.util.RecyclerViewHelper;
import com.github.wally.wcdbsample.viewbinder.PersonViewBinderViewBinder;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends RxAppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerViewHelper mRecyclerViewHelper;
    private PersonBiz mPersonBiz;
    private boolean isEnsureCheckData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(PersonVO.class, new PersonViewBinderViewBinder());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);
        recyclerView.setItemAnimator(animator);
        //加载更多加载监听
        mRecyclerViewHelper = RecyclerViewHelper.create(swipeRefreshLayout, recyclerView, adapter, new RecyclerViewHelper.OnLoadListener() {
            @Override
            public void onSwipeRefresh(int page, boolean isFirst) {
                loadData(page, true);
            }

            @Override
            public void onLoadMore(int page, boolean isFirst) {
                loadData(page, false);
            }
        });
        //初始化默认数据
        mPersonBiz = new PersonBiz();
        mRecyclerViewHelper.startRefreshWithLoading();
    }

    private void loadData(final int page, final boolean isRefresh) {
        //默认每页的条数
        final int pageSize = 20;
        Observable.create(new ObservableOnSubscribe<List<PersonVO>>() {
            @Override
            public void subscribe(ObservableEmitter<List<PersonVO>> emitter) throws Exception {
                if (!isEnsureCheckData) {
                    boolean hasData = mPersonBiz.hasData();
                    if (!hasData) {
                        ArrayList<PersonDTO> dtos = new ArrayList<>();
                        int size = 1000;
                        for (int i = 0; i < size; i++) {
                            PersonDTO dto = new PersonDTO();
                            dto.setPersonName("王小二" + i);
                            dto.setSex(SexEnums.male.getCode());
                            dto.setAge(18);
                            dtos.add(dto);
                        }
                        Log.d(TAG, ("本次插入" + size + "条数据 " + "插入开始..."));
                        long start = System.currentTimeMillis();
                        mPersonBiz.addPersons(dtos);
                        long end = System.currentTimeMillis();
                        Log.d(TAG, ("本次插入" + size + "条数据 " + "插入结束...耗时：" + (end - start)));
                    }
                    isEnsureCheckData = true;
                }
                Log.d(TAG, ("查询开始..."));
                long start = System.currentTimeMillis();
                List<PersonVO> allPersonList = mPersonBiz.getAllPersonPageList(page, pageSize).getRecords();
                long end = System.currentTimeMillis();
                Log.d(TAG, ("查询结束...耗时：" + (end - start)));
                emitter.onNext(allPersonList);
                emitter.onComplete();
            }
        })
                //绑定生命周期进行切断
                .compose(this.<List<PersonVO>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PersonVO>>() {
                    @Override
                    public void accept(List<PersonVO> personVOList) throws Exception {
                        boolean hasNext = true;
                        if (personVOList.size() < pageSize) {
                            hasNext = false;
                        }
                        mRecyclerViewHelper.updateDataSource(isRefresh, personVOList, hasNext);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this.getApplicationContext(), "哎呀，出错啦...", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "异常：" + throwable.getMessage());
                    }
                });
    }
}