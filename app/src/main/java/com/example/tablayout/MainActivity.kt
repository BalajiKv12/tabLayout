package com.example.tablayout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    //creating an arrayList of fragments
    private var fragmentList1: ArrayList<Fragment> = ArrayList()
    //creating an adapter
    private lateinit var adapter : FragmentAdapter

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPage : ViewPager2
    private lateinit var floatButton : FloatingActionButton
    private lateinit var floatDeleteButton : FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mapping variables with ID's
        tabLayout = findViewById(R.id.tabLayout)
        viewPage = findViewById(R.id.viewPager)
        floatButton = findViewById(R.id.floatingActionButtonAdd)
        floatDeleteButton=findViewById(R.id.floatingActionButtonDelete)

        //adding fragments to arrayList
        fragmentList1.add(FragmentOne())
        fragmentList1.add(FragmentTwo())

        //initializing adapter
        adapter = FragmentAdapter(supportFragmentManager,lifecycle,fragmentList1)

        //creating tabs
        tabLayout.addTab(tabLayout.newTab().setText("1"))
        tabLayout.addTab(tabLayout.newTab().setText("2"))

        //setting adapter
        viewPage.adapter = adapter


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //displaying the current tab details
                if(tab!=null){
                    viewPage.currentItem=tab.position


                    floatDeleteButton.setOnClickListener {
                        //deleting selected tab
                        adapter.deleteFragment(tab.position)
                        tabLayout.removeTabAt(tab.position)
                        //notifying adapter
                        adapter.notifyItemRemoved(tab.position)
                        Toast.makeText(applicationContext, "Tab Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })


        floatButton.setOnClickListener{
            //adding instance of FragmentOne
            if(fragmentList1.size%2==0)
                fragmentList1.add(FragmentOne())
            else
                fragmentList1.add(FragmentTwo())
            Toast.makeText(applicationContext,"Tab Added",Toast.LENGTH_SHORT).show()
            val text = (fragmentList1.size).toString()
            //creating tab
            tabLayout.addTab(tabLayout.newTab().setText(text))
            //notifying adapter
            adapter.notifyItemInserted(fragmentList1.size-1)
        }


    }
}
