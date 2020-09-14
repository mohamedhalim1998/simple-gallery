package com.mohamed.halim.essa.simplegallery.allimages

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamed.halim.essa.simplegallery.R
import com.mohamed.halim.essa.simplegallery.databinding.ImagesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    lateinit var binding: ImagesFragmentBinding
    lateinit var adapter: ImagesAdapter

    val viewModel: ImagesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.images_fragment, container, false)
        binding.lifecycleOwner = this
        setupImageRecycleView()
        setupViewModelObservers()
        return binding.root
    }

    private fun setupViewModelObservers() {
        viewModel.updateImagesCache()
        setupImagesObserver()
    }

    private fun setupImagesObserver() {
        viewModel.images.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupImageRecycleView() {
        adapter = ImagesAdapter()
        val manager = GridLayoutManager(requireContext(), 3)
        val imageRecycleView = binding.imagesRecycleView
        imageRecycleView.layoutManager = manager
        imageRecycleView.adapter = adapter
    }

}