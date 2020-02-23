package com.tarek360.giphy.ui.search

import android.content.Context
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.tarek360.domain.model.Image
import com.tarek360.giphy.App
import com.tarek360.giphy.R
import com.tarek360.giphy.afterTextChanged
import com.tarek360.giphy.isLandscape
import com.tarek360.giphy.ui.details.GifDetailsFragment
import com.tarek360.giphy.viewmodel.GiphyViewModelProviders
import kotlinx.android.synthetic.main.search_result_frame1.*
import javax.inject.Inject


class SearchResultFragment : Fragment() {

    private lateinit var searchResultViewModel: SearchResultViewModel

    @Inject
    lateinit var giphyViewModelProviders: GiphyViewModelProviders

    private var isAnimated = false

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_result_frame1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultViewModel =
            giphyViewModelProviders.of(this).get(SearchResultViewModel::class.java)
        searchResultViewModel.viewState.observe(viewLifecycleOwner, Observer { render(it) })

        setupImagesRecyclerView()

        searchTextField.requestFocus()
        searchTextField.afterTextChanged {
            searchResultViewModel.handleAction(SearchResultAction.SearchAction(query = it))
        }

        setupAnimation()
        if (isAnimated) {
            animate(0)
        }
    }

    private fun setupImagesRecyclerView() {
        imagesRecyclerView.layoutManager = GridLayoutManager(context, getSpanCount())
        val adapter = ImagesRecyclerViewAdapter()
        adapter.onItemClickListener = { item -> displayGifDetailsFragment(item) }
        imagesRecyclerView.adapter = adapter
    }

    private fun getSpanCount() = if (resources.isLandscape()) {
        SPAN_COUNT_LANDSCAPE
    } else {
        SPAN_COUNT_PORTRAIT
    }

    private fun displayGifDetailsFragment(image: Image) {
        findNavController(this)
            .navigate(
                R.id.gifDetailsFragment,
                GifDetailsFragment.buildBundle(image.normalFramesUrl)
            )
    }

    private fun render(state: SearchResultState) {
        return when (state) {
            is SearchResultState.LoadingState -> renderLoadingState()
            is SearchResultState.DataState -> renderDataState(state)
            is SearchResultState.ErrorState -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        showProgressBar()
        hideErrorMessage()
    }

    private fun renderDataState(dataState: SearchResultState.DataState) {
        hideProgressBar()
        hideErrorMessage()
        renderImages(dataState.data.images)

        if (dataState.data.images.isEmpty()) {
            showErrorMessage(getString(R.string.try_another_search))
        }

        if (isAnimated && dataState.data.images.isEmpty()) {
            reverseAnimation()
        } else if (!isAnimated && dataState.data.images.isNotEmpty()) {
            animate()
        }
    }

    private fun renderImages(images: PagedList<Image>) {
        (imagesRecyclerView.adapter as ImagesRecyclerViewAdapter).submitList(images)
    }

    private fun renderErrorState() {
        hideProgressBar()
        showErrorMessage(getString(R.string.error_message))
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        progressBar.isIndeterminate = true
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        progressBar.isIndeterminate = false
    }

    private fun showErrorMessage(msg: String) {
        errorMessage.visibility = View.VISIBLE
        errorMessage.text = msg
    }

    private fun hideErrorMessage() {
        errorMessage.visibility = View.GONE
        errorMessage.text = ""
    }

    private val constraintSet1 = ConstraintSet()
    private val constraintSet2 = ConstraintSet()
    private val transition = AutoTransition()

    private fun setupAnimation() {
        constraintSet1.clone(constraintLayout)
        constraintSet2.clone(context, R.layout.search_result_frame2)
        transition.interpolator = AccelerateDecelerateInterpolator()
    }

    private fun animate(time: Long = 400) {
        transition.duration = time
        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet2.applyTo(constraintLayout)
        isAnimated = true
    }

    private fun reverseAnimation() {
        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet1.applyTo(constraintLayout)
        isAnimated = false
    }

    companion object {
        private const val SPAN_COUNT_PORTRAIT = 3
        private const val SPAN_COUNT_LANDSCAPE = 4
    }
}
