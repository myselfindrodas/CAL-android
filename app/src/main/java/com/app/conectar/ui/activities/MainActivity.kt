package com.app.conectar.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.app.conectar.R
import com.app.conectar.databinding.ActivityMainBinding
import com.app.conectar.session.SessionManager
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    var mNavController: NavController? = null
    var sessionManager: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sessionManager = SessionManager(this)
        mNavController = findNavController(R.id.flFragment)

        mNavController?.navigate(R.id.nav_home)


        activityMainBinding.bottomnav.btnHome.background =
            ResourcesCompat.getDrawable(
                this.resources,
                R.drawable.rectengel3,
                this.resources.newTheme()
            )
        activityMainBinding.bottomnav.imgHome.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.yellow
            )
        )
        activityMainBinding.bottomnav.tvHome.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.yellow
            )
        )


        activityMainBinding.bottomnav.btnAccount.background =
            ResourcesCompat.getDrawable(
                this.resources,
                R.drawable.rectengel3,
                this.resources.newTheme()
            )
        activityMainBinding.bottomnav.btnAccount.setBackgroundColor(Color.BLACK)
        activityMainBinding.bottomnav.imgAccount.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        activityMainBinding.bottomnav.tvAccount.setTextColor(Color.WHITE)

        activityMainBinding.bottomnav.btnComminucation.background =
            ResourcesCompat.getDrawable(
                this.resources,
                R.drawable.rectengel3,
                this.resources.newTheme()
            )
        activityMainBinding.bottomnav.btnComminucation.setBackgroundColor(Color.BLACK)
        activityMainBinding.bottomnav.imgComminucation.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        activityMainBinding.bottomnav.tvCommunication.setTextColor(Color.WHITE)

        activityMainBinding.bottomnav.btnSetting.background =
            ResourcesCompat.getDrawable(
                this.resources,
                R.drawable.rectengel3,
                this.resources.newTheme()
            )
        activityMainBinding.bottomnav.btnSetting.setBackgroundColor(Color.BLACK)
        activityMainBinding.bottomnav.imgSetting.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        activityMainBinding.bottomnav.tvSetting.setTextColor(Color.WHITE)





        activityMainBinding.bottomnav.btnHome.setOnClickListener {


            mNavController?.navigate(R.id.nav_home)
            activityMainBinding.bottomnav.btnHome.background =
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.rectengel3,
                    this.resources.newTheme()
                )
            activityMainBinding.bottomnav.imgHome.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            activityMainBinding.bottomnav.tvHome.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )

            activityMainBinding.bottomnav.btnAccount.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgAccount.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvAccount.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnComminucation.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgComminucation.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvCommunication.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnSetting.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgSetting.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvSetting.setTextColor(Color.WHITE)


        }



        activityMainBinding.bottomnav.btnAccount.setOnClickListener {


            showAccountBottomSheetDialog()
            activityMainBinding.bottomnav.btnAccount.background = ResourcesCompat.getDrawable(
                this.resources,
                R.drawable.rectengel3,
                this.resources.newTheme()
            )
            activityMainBinding.bottomnav.imgAccount.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            activityMainBinding.bottomnav.tvAccount.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )

            activityMainBinding.bottomnav.btnHome.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgHome.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvHome.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnComminucation.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgComminucation.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvCommunication.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnSetting.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgSetting.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvSetting.setTextColor(Color.WHITE)


        }






        activityMainBinding.bottomnav.btnComminucation.setOnClickListener {

            showCommunicateBottomSheetDialog()
            activityMainBinding.bottomnav.btnComminucation.background =
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.rectengel3,
                    this.resources.newTheme()
                )
            activityMainBinding.bottomnav.imgComminucation.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            activityMainBinding.bottomnav.tvCommunication.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )

            activityMainBinding.bottomnav.btnHome.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgHome.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvHome.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnAccount.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgAccount.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvAccount.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnSetting.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgSetting.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvSetting.setTextColor(Color.WHITE)


        }





        activityMainBinding.bottomnav.btnSetting.setOnClickListener {
            showSettingsBottomSheetDialog()

            activityMainBinding.bottomnav.btnSetting.background =
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.rectengel3,
                    this.resources.newTheme()
                )
            activityMainBinding.bottomnav.imgSetting.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            activityMainBinding.bottomnav.tvSetting.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )

            activityMainBinding.bottomnav.btnHome.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgHome.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvHome.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnAccount.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgAccount.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvAccount.setTextColor(Color.WHITE)

            activityMainBinding.bottomnav.btnComminucation.setBackgroundColor(Color.BLACK)
            activityMainBinding.bottomnav.imgComminucation.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            activityMainBinding.bottomnav.tvCommunication.setTextColor(Color.WHITE)

        }

    }


    private fun showAccountBottomSheetDialog() {
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(this, R.style.SheetDialog)

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.account_bottom_sheet_layout, null)

        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val btnClose = view.findViewById<ImageView>(R.id.ivClose)
        val btnWalletMore = view.findViewById<ImageView>(R.id.ivWalletMore)
        val llWalletSubmenu = view.findViewById<LinearLayout>(R.id.llWalletSubmenu)
        val clWallet = view.findViewById<ConstraintLayout>(R.id.clWallet)

        llWalletSubmenu.visibility = View.GONE
        btnWalletMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))

        clWallet.setOnClickListener {
            if (llWalletSubmenu.isVisible) {
                llWalletSubmenu.visibility = View.GONE
                btnWalletMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))
                clWallet.setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        theme
                    )
                )

            } else {
                llWalletSubmenu.visibility = View.VISIBLE
                btnWalletMore.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.remove_icon
                    )
                )
                clWallet.setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.blue_light,
                        theme
                    )
                )
            }
        }
        // on below line we are adding on click listener
        // for our dismissing the dialog button.
        btnClose.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog.dismiss()
        }
        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(false)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }


    private fun showSettingsBottomSheetDialog() {
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(this, R.style.SheetDialog)

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.settings_bottom_sheet_layout, null)

        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val btnClose = view.findViewById<ImageView>(R.id.ivClose)
        val btnApperianceMore = view.findViewById<ImageView>(R.id.btnApperianceMore)
        val btnLegalMore = view.findViewById<ImageView>(R.id.btnLegalMore)
        val llApperanceSubMenu = view.findViewById<LinearLayout>(R.id.llApperanceSubMenu)
        val llLegalSubMenu = view.findViewById<LinearLayout>(R.id.llLegalSubMenu)
        val clApperance = view.findViewById<ConstraintLayout>(R.id.clApperance)
        val clLegal = view.findViewById<ConstraintLayout>(R.id.clLegal)
        val clProfile = view.findViewById<ConstraintLayout>(R.id.clProfile)

        llApperanceSubMenu.visibility = View.GONE
        llLegalSubMenu.visibility = View.GONE
        btnApperianceMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))
        btnLegalMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))


        clProfile.setOnClickListener {
            dialog.dismiss()
            mNavController?.navigate(R.id.nav_profile)
        }
        clApperance.setOnClickListener {
            if (llApperanceSubMenu.isVisible) {
                llApperanceSubMenu.visibility = View.GONE
                btnApperianceMore.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.add_icon
                    )
                )
                clApperance.setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        theme
                    )
                )

            } else {
                llApperanceSubMenu.visibility = View.VISIBLE
                btnApperianceMore.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.remove_icon
                    )
                )
                clApperance.setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.blue_light,
                        theme
                    )
                )
            }
        }


        clLegal.setOnClickListener {
            if (llLegalSubMenu.isVisible) {
                llLegalSubMenu.visibility = View.GONE
                btnLegalMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))
                clLegal.setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        theme
                    )
                )

            } else {
                llLegalSubMenu.visibility = View.VISIBLE
                btnLegalMore.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.remove_icon
                    )
                )
                clLegal.setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.blue_light,
                        theme
                    )
                )
            }
        }
        // on below line we are adding on click listener
        // for our dismissing the dialog button.
        btnClose.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog.dismiss()
        }
        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(false)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }

    private fun showCommunicateBottomSheetDialog() {
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(this, R.style.SheetDialog)

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.communicate_bottom_sheet_layout, null)

        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val btnClose = view.findViewById<ImageView>(R.id.ivClose)


        /*val btnWalletMore = view.findViewById<ImageView>(R.id.ivWalletMore)
        val llWalletSubmenu = view.findViewById<LinearLayout>(R.id.llWalletSubmenu)
        val clWallet = view.findViewById<ConstraintLayout>(R.id.clWallet)

        llWalletSubmenu.visibility=View.GONE
        btnWalletMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))

        btnWalletMore.setOnClickListener {
            if (llWalletSubmenu.isVisible){
                llWalletSubmenu.visibility=View.GONE
                btnWalletMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_icon))
                clWallet.setBackgroundColor(ResourcesCompat.getColor(this.resources,R.color.white,theme))

            }else{
                llWalletSubmenu.visibility=View.VISIBLE
                btnWalletMore.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.remove_icon))
                clWallet.setBackgroundColor(ResourcesCompat.getColor(this.resources,R.color.blue_light,theme))
            }
        }*/


        // on below line we are adding on click listener
        // for our dismissing the dialog button.
        btnClose.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog.dismiss()
        }
        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(false)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }


    fun showProgressDialog() {
        activityMainBinding.rlLoading.visibility = View.VISIBLE
    /*if (mProgressDialog == null) {        mProgressDialog = ProgressDialog(this)        mProgressDialog!!.setMessage("Loading...")        mProgressDialog!!.isIndeterminate = true    }    mProgressDialog!!.show()*/
    }

    fun hideProgressDialog() {
        activityMainBinding.rlLoading.visibility = View.GONE
    /*if (mProgressDialog != null && mProgressDialog!!.isShowing) {        mProgressDialog!!.dismiss()    }*/
    }


//    var mProgressDialog: ProgressDialog? = null
//
//    fun showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = ProgressDialog(this)
//            mProgressDialog!!.setMessage("Loading...")
//            mProgressDialog!!.isIndeterminate = true
//        }
//        mProgressDialog!!.show()
//    }
//
//    fun hideProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
//            mProgressDialog!!.dismiss()
//        }
//    }


}