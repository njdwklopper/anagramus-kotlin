package tech.klopper.anagramus.app

import android.os.Bundle
import tech.klopper.anagramus.R
import tech.klopper.anagramus._base.BaseActivity

class MainActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_activity)
    }

    override fun onStart() {
        super.onStart()
        handleAppInitSession()
    }
}
