package com.example.people;


import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobFragment extends Fragment {

//private FloatingActionButton floatingActionButton;
//private JobViewModel jobViewModel;
//private MyAdapter myAdapter;
//private RecyclerView recyclerView;
//private List<JobMessage>allJobs;
//private LiveData<List<JobMessage>>fileJobs;
//private DividerItemDecoration dividerItemDecoration;
//    public JobFragment() {
//        //默认菜单
//        setHasOptionsMenu(true);
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_job, container, false);
//
//
//
//    }
//
//
//    //选择菜单功能
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//
////switch (item.getItemId()){
////    case R.id.job:
//////        Intent intent =new Intent(getActivity(),AddFragment.class);
//////        startActivity(intent);
//////         break;
////
////
////        getFragmentManager()
////                .beginTransaction()
////                .addToBackStack(null)  //将当前fragment加入到返回栈中
////                .replace(R.id.addFragment, new AddFragment()).commit();
////
////}
//
//
//
//        return super.onOptionsItemSelected(item);
//
//
//
//
//    }
//
//
//    //加载menu
//
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.search_menu,menu);
//        SearchView searchView= (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
//       searchView.setMaxWidth(500);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//  String patten=newText.trim();
//
////fileJobs.removeObserver((Observer<? super List<JobMessage>>) requireActivity());//xiaochu
//
//  fileJobs=jobViewModel.findAllJobsLive(patten);
//
//  //观察刷新
//  fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
//      @Override
//      public void onChanged(List<JobMessage> jobMessages) {
//
//          myAdapter.setAllJobs(jobMessages);
//          myAdapter.notifyDataSetChanged();
//      }
//  });   recyclerView.setAdapter(myAdapter);//????
//                return true;
//            }
//        });
//    }
//
//    //隐藏键盘
//    @Override
//    public void onResume() {
//        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
//        super.onResume();
//    }
//
//    //加载卡片
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        jobViewModel= ViewModelProviders.of(requireActivity()).get(JobViewModel.class);
//        recyclerView =requireActivity().findViewById(R.id.recycler_view_job_show);
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        myAdapter=new MyAdapter();
//        //分割线
//        dividerItemDecoration=new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);
//        fileJobs=jobViewModel.getAllJobsLive();//+
//        fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
//            @Override
//            public void onChanged(List<JobMessage> jobMessages) {
//
//                recyclerView.addItemDecoration(dividerItemDecoration);
//               // int temp =myAdapter.getItemCount();
//                myAdapter.setAllJobs(jobMessages);
//                myAdapter.notifyDataSetChanged();
//
//            }
//        });
//        recyclerView.setAdapter(myAdapter);//????
//      //  floatingActionButton=requireActivity().findViewById(R.id.floatingActionButton);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NavController navController= Navigation.findNavController(v);
//                   navController.navigate(R.id.action_jobFragment_to_addFragment);
//            }
//        });
////滑动删除
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START|ItemTouchHelper.END) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//          //final JobMessage d=allJobs.get(viewHolder.getAdapterPosition());
//              jobViewModel.deleteAllJobs();
//                Snackbar.make(requireActivity().findViewById(R.id.addFragment),"删除了一个词汇",Snackbar.LENGTH_SHORT)
//                        .setAction("撤销", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                               // undoaction=true;
//                               // jobViewModel.insertJobs();
//                            }
//                        }).show();
//            }
//        }).attachToRecyclerView(recyclerView);




    }





