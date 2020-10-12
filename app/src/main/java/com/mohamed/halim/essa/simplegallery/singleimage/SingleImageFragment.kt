package com.mohamed.halim.essa.simplegallery.singleimage

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mohamed.halim.essa.simplegallery.R
import com.mohamed.halim.essa.simplegallery.allimages.ImagesViewModel
import com.mohamed.halim.essa.simplegallery.databinding.SingleImageFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class SingleImageFragment : Fragment(), FullScreenControl {


    val viewModel: ImagesViewModel by activityViewModels()
    lateinit var binding: SingleImageFragmentBinding
    lateinit var adapter: ImagePagerAdapter
    var fullScreen = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            fullScreen = (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0).not()
        }
        requireActivity().findViewById<View>(R.id.bottom_nav).visibility = View.GONE


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.single_image_fragment, container, false)
        enterFullScreen()
        setupImageActions()
        setupViewPager()
        setupViewModelObservers()
        return binding.root
    }

    private fun setupImageActions() {
        setupShareAction()
    }


    private fun setupShareAction() {
        binding.shareAction.setOnClickListener {

            val image = adapter.currentList.get(binding.imagesViewPager.currentItem)
            val uri =
                ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image.id)
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "image/*"
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri)
            requireContext().startActivities(
                arrayOf(
                    Intent.createChooser(
                        sharingIntent,
                        "Share via"
                    )
                )
            )

        }
    }

    private fun setupViewModelObservers() {
        setupImagesObserver()
    }

    private fun setupImagesObserver() {
        viewModel.images.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            val pos = requireArguments().getInt("imagePosition", 0)
            binding.imagesViewPager.setCurrentItem(
                pos, false
            )
        })
    }

    private fun setupViewPager() {
        adapter = ImagePagerAdapter(this)
        binding.imagesViewPager.adapter = adapter

    }

    override fun enterFullScreen() {
        requireActivity().window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        binding.imageActions.visibility = View.GONE
    }

    override fun exitFullScreen() {
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        binding.imageActions.visibility = View.VISIBLE
    }

}