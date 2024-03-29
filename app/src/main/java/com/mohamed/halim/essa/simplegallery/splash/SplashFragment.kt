package com.mohamed.halim.essa.simplegallery.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.fragment.findNavController
import com.mohamed.halim.essa.simplegallery.R
import com.mohamed.halim.essa.simplegallery.data.Repo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val READ_WRITE_PERMISSION = 1025

    @Inject
    lateinit var repo: Repo

    val appScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottom_nav).visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkReadWritePermission()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun checkReadWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
                        && checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED)
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    READ_WRITE_PERMISSION
                )
            } else {
                appScope.launch {
                    repo.updateImages()
                }
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAlbumsFragment())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == READ_WRITE_PERMISSION &&
            grantResults.isNotEmpty()
        ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                appScope.launch {
                    repo.updateImages()
                }
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAlbumsFragment())
            } else {
                checkReadWritePermission()
            }
        }
    }
}
