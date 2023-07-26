package base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.example.kattabozortest.R
import java.net.UnknownHostException


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!
    val bindingSafe get() = _binding
    var isSavedState = true


//    private lateinit var mediaPermissionsRequester: PermissionsRequester
//    private lateinit var mediaPermissionsQRequester: PermissionsRequester

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (isSavedState && _binding != null)
            return binding.root
        _binding = inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    fun updateStatusBarColor(
//        @ColorRes colorId: Int,
//        isStatusBarFontDark: Boolean = true,
//        isTranslucent: Boolean = false
//    ) {
//
//        val window = requireActivity().window
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.statusBarColor = ContextCompat.getColor(requireContext(), colorId)
//            setSystemBarTheme(isStatusBarFontDark)
//        } else {
//            window.statusBarColor = ContextCompat.getColor(requireContext(), colorId)
//        }
//    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private fun setSystemBarTheme(isStatusBarFontDark: Boolean) {
//        val lFlags = requireActivity().window.decorView.systemUiVisibility
//        requireActivity().window.decorView.systemUiVisibility =
//            if (isStatusBarFontDark) lFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() else lFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//
//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//    }

//    open fun handleError(throwable: Throwable) {  //TODO should be handled
//        when (throwable) {
//            is ServerErrorException -> {
//                showAlertDialog(uz.padishah.data.R.string.UnexpectedError)
//            }
//            is UnknownHostException -> {
//                showAlertDialog(R.string.ExceptionNoConnectivity)
//            }
//            is FakeTimeException,
//            is BadRequestException -> {
//                showAlertDialog(R.string.ExceptionBadRequestTitle, throwable.message.toString())
//            }
//            is UnauthorizedException -> {
//
//            }
//            else -> {
//                showAlertDialog(R.string.ExceptionBadRequestTitle, throwable.message.toString())
//            }
//        }
//    }

//    open fun showAlertDialog(
//        @StringRes title: Int,
//        subTitle: String = "",
//        leftAction: (() -> Unit)? = null,
//        rightAction: (() -> Unit)? = null,
//        @StringRes leftTitle: Int = R.string.No,
//        @StringRes rightTitle: Int = R.string.Yes
//    ) {
//        val fm: FragmentManager = childFragmentManager
//        val fragment = fm.findFragmentByTag(PadishahAlertDialog::class.java.name)
//        if (fragment?.isAdded == true && fragment.isVisible) return
//
//        val ofbAlertDialogFragment =
//            PadishahAlertDialog.getInstance(
//                title,
//                subTitle,
//                rightAction,
//                leftAction,
//                leftTitle,
//                rightTitle
//            )
//        ofbAlertDialogFragment.show(fm, PadishahAlertDialog::class.java.name)
//    }


//    open fun showPadishahAlertDialog(
//        @StringRes title: Int,
//        subTitle: String = "",
//        leftAction: (() -> Unit)? = null,
//        rightAction: (() -> Unit)? = null,
//        @StringRes leftTitle: Int = R.string.No,
//        @StringRes rightTitle: Int = R.string.Yes
//    ) {
//        val fm: FragmentManager = childFragmentManager
//        val fragment = fm.findFragmentByTag(PadishahAlertDialog2::class.java.name)
//        if (fragment?.isAdded == true && fragment.isVisible) return
//
//        val padishahAlertDialogFragment =
//            PadishahAlertDialog2.getInstance(
//                title,
//                subTitle,
//                rightAction,
//                leftAction,
//                leftTitle,
//                rightTitle
//            )
//        padishahAlertDialogFragment.show(fm, PadishahAlertDialog2::class.java.name)
//    }

//    open fun showDialog(
//        dialogType: DialogTypes,
//        yes: (() -> Unit)? = null,
//        yesInfo: ((String) -> Unit)? = null,
//        no: (() -> Unit)? = null,
//        dismissed: (() -> Unit)? = null,
//    ) {
//        val fm: FragmentManager = childFragmentManager
//        val fragment = fm.findFragmentByTag(OfbDialogFragment::class.java.name)
//        if (fragment?.isAdded == true && fragment.isVisible) return
//
//        val ofbDialogFragment =
//            OfbDialogFragment.getInstance(dialogType, yes, yesInfo, no, dismissed)
//        ofbDialogFragment.show(fm, OfbDialogFragment::class.java.name)
//    }
//
//    open fun showAlertDialog(
//        @StringRes title: Int,
//        subTitle: String = "",
//        leftAction: (() -> Unit)? = null,
//        rightAction: (() -> Unit)? = null,
//        @StringRes leftTitle: Int = R.string.Cancel,
//        @StringRes rightTitle: Int = R.string.Yes
//    ) {
//        val fm: FragmentManager = childFragmentManager
//        val fragment = fm.findFragmentByTag(OfbAlertDialog::class.java.name)
//        if (fragment?.isAdded == true && fragment.isVisible) return
//
//        val ofbAlertDialogFragment =
//            OfbAlertDialog.getInstance(
//                title,
//                subTitle,
//                rightAction,
//                leftAction,
//                leftTitle,
//                rightTitle
//            )
//        ofbAlertDialogFragment.show(fm, OfbAlertDialog::class.java.name)
//    }

//    open fun showUnderDevelopmentDialog() {
//        showAlertDialog(
//            title = R.string.UnderDevelopmentTitle,
//            rightTitle = R.string.Continue
//        )
//    }

//    open fun replaceFragment(
//        containerId: Int,
//        destination: Fragment,
//        backStack: Boolean = false,
//        transition: Int = FragmentTransaction.TRANSIT_NONE
//    ) {
//        if (requireActivity() !is MainActivity)
//            return
//
//        val fragment = childFragmentManager.findFragmentByTag(destination::class.java.name)
//
//        if (fragment == null || !fragment.isAdded || !fragment.isVisible) {
//            val transaction = childFragmentManager.beginTransaction()
//            if (transition != FragmentTransaction.TRANSIT_NONE)
//                transaction.setTransition(transition)
//            transaction.replace(containerId, destination, tag)
//            if (backStack)
//                transaction.addToBackStack(null)
//            transaction.commit()
//        }
//    }

//    open fun removeFragment(fragmentTag: String) {
//        if (requireActivity() !is MainActivity)
//            return
//
//        val fragment =
//            requireActivity().supportFragmentManager.findFragmentByTag(fragmentTag)
//        if (fragment == null || !fragment.isAdded || !fragment.isVisible) {
//            (requireActivity() as MainActivity).removeFragment(
//                fragmentTag
//            )
//        }
//    }

//    open fun openSettings(code: Int) {
//        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//        val uri = Uri.fromParts("package", requireContext().applicationContext.packageName, null)
//        intent.data = uri
//        startActivityForResult(intent, code)
//    }


//    fun requestMediaPermission() {
//        if (!this::mediaPermissionsRequester.isInitialized || !this::mediaPermissionsQRequester.isInitialized) {
//            mediaPermissionsRequester = constructPermissionsRequest(
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                onShowRationale = ::onMediaShowRationale,
//                onPermissionDenied = ::onMediaDenied,
//                onNeverAskAgain = ::neverAskAgain,
//                requiresPermission = ::checkPermissionDownload
//            )
//            mediaPermissionsQRequester = constructPermissionsRequest(
//                Manifest.permission.ACCESS_MEDIA_LOCATION,
//                onShowRationale = ::onMediaShowRationale,
//                onPermissionDenied = ::onMediaDenied,
//                onNeverAskAgain = ::neverAskAgain,
//                requiresPermission = ::checkPermissionDownload
//            )
//        }
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
//            mediaPermissionsRequester.launch()
//        } else {
//            mediaPermissionsQRequester.launch()
//        }
//    }

//    private fun checkPermissionDownload() {
//        onMediaPermissionGranted()
//    }
//
//    open fun onMediaPermissionGranted() {
//
//    }

//    private fun neverAskAgain() {
////        showAlertDialog(R.string.DownloadPermission, rightTitle = R.string.Settings, rightAction = {
////            val intent =
////                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
////            val uri = Uri.fromParts("package", requireContext().packageName, null)
////            intent.data = uri
////            startActivity(intent)
////        })
//    }

//    private fun onMediaShowRationale(request: PermissionRequest) {
//        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
////        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
////        showAlertDialog(R.string.DownloadPermission, leftAction = {
////            request.cancel()
////        }, rightAction = {
////            request.proceed()
////        })
//    }

//    private fun onMediaDenied() {
////        showAlertDialog(R.string.DownloadPermission)
//    }
//
//    open fun isMediaPermissionGranted(): Boolean {
//        val permissions =
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//            } else {
//                arrayOf(Manifest.permission.ACCESS_MEDIA_LOCATION)
//            }
//
//        return PermissionUtils.hasSelfPermissions(
//            requireContext(), *permissions
//        )
//    }
//
//    open fun openPdfFile(file: File) {
//        val target = Intent(Intent.ACTION_VIEW)
//        val uri = FileProvider.getUriForFile(
//            requireContext(),
//            requireContext().applicationContext.packageName + ".provider",
//            file,
//        )
//        target.setDataAndType(uri, "application/pdf")
//        target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//
//        val intent = Intent.createChooser(target, "Choose")
//        try {
//            startActivity(intent)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }


}