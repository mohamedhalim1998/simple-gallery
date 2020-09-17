package com.mohamed.halim.essa.simplegallery.singleimage

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mohamed.halim.essa.simplegallery.R
import com.mohamed.halim.essa.simplegallery.databinding.SingleImageFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleImageFragment : Fragment(), FullScreenControl {


    val viewModel: SingleImageViewModel by viewModels()
    lateinit var binding: SingleImageFragmentBinding
    lateinit var adapter: ImagePagerAdapter
    var fullScreen = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterFullScreen()
        requireActivity().window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            fullScreen = (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0).not()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.single_image_fragment, container, false)
        setupViewPager()
        setupViewModelObservers()
        return binding.root
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
    }

    override fun exitFullScreen() {
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}