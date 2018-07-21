package com.padcmyanmar.myapplication.activities
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.padcmyanmar.myapplication.R
import com.padcmyanmar.myapplication.adapters.HealthsListAdapter
import com.padcmyanmar.myapplication.data.models.HealthCareModel
import com.padcmyanmar.myapplication.data.vos.HealthCareVO
import com.padcmyanmar.myapplication.deglates.HealthsDelegate
import com.padcmyanmar.myapplication.events.ErrorEvent
import com.padcmyanmar.myapplication.events.SuccessEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.view_holder_mm_health.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity(), HealthsDelegate {
    override fun onTapNews(news: HealthCareVO?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var rvHealthsListAdapter : HealthsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        rv_mm_health.layoutManager= LinearLayoutManager(applicationContext)
        rvHealthsListAdapter = HealthsListAdapter(applicationContext,this)
        rv_mm_health.adapter=rvHealthsListAdapter

        HealthCareModel.getInstance().loadHealths()
        swipeRL.isRefreshing = true

        swipeRL.setOnRefreshListener {
            HealthCareModel.getInstance().loadHealths()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthCare(healthsLoadedEvent: SuccessEvent.HealthsLoadedEvent){
        rvHealthsListAdapter!!.appendHealthsData(healthsLoadedEvent.loadedHealths as MutableList<HealthCareVO>)
        swipeRL.isRefreshing = false
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorNewsLoadedEvent(apiErrorEvent: ErrorEvent.ApiErrorEvent ) {
        swipeRL.isRefreshing = false
          var empty: View = vp_empty
        empty.visibility = View.VISIBLE
    }
}
