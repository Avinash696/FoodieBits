package com.example.zepto.ui.activity


import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zepto.R
import com.example.zepto.adapter.adapterCategories
import com.example.zepto.adapter.adapterTrending
import com.example.zepto.databinding.ActivityHomeBinding
import com.example.zepto.model.cardItemModel


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var rvTrending: RecyclerView
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
//    lateinit var rvCategories: RecyclerView
    lateinit var simpleCategories: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        init()
        showImg()
        tendingItem()
        catalogItem()
        catalogGridView()

        //
//        val drawer: DrawerLayout = ActivityHomeBinding.drawerLayout
//        val toggle = ActionBarDrawerToggle(
//            this,
//            drawer,
//            toolbar,
//            R.string.nav_open,
//            R.string.nav_close)
//        drawer.addDrawerListener(toggle)
//        toggle.syncState()
    }
    private fun init(){
        rvTrending = binding.rvTrending
//        rvCategories = binding.rvCategories
        simpleCategories = binding.simpleView
//        drawerLayout = binding.dlHome
    }
    private fun showImg() {

        val handler = Handler()
        val r = Runnable {
//            startActivity(Intent(this, HomeActivity::class.java))
        }
        handler.postDelayed(r, 5000)
    }

    private fun tendingItem() {

        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.onion, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.onion, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.onion, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.onion, "Pizza", 2, 3))

        rvTrending.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,
            false)
        val arrayAdapter = adapterTrending(arrayList,this)
        rvTrending.adapter = arrayAdapter
    }

    private fun catalogItem(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))

//        rvCategories.layoutManager = LinearLayoutManager(this)
//        val arrayAdapter = adapterTrending(arrayList)
//        rvCategories.adapter = arrayAdapter
    }
    private fun catalogGridView(){
        val arrayList = ArrayList<cardItemModel>()
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))
        arrayList.add(cardItemModel(1, R.drawable.fruit_vegitable, "Pizza", 2, 3))

        val adapter = adapterCategories(this, arrayList)
        simpleCategories.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_order -> Toast.makeText(this, "Order selected", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}